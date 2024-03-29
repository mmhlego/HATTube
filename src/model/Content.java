package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;

import database.DataSelector;
import database.DataUpdator;
import database.DataSelector.Table;
import javafx.scene.image.Image;
import tools.IDGenerator;
import tools.ImageDownloader;

public class Content extends ContentInheritance {
    String Name, Description;
    Rate ContentRate;
    ArrayList<Genre> Genres;
    double Score;
    String[][] Info;
    boolean Visibility;
    URL Poster;

    public Image getPosterImage() {
        try {
            return new Image(new FileInputStream(new File("resource/images/posters/" + this.getName()
                    + ImageDownloader.GetImageFormat(this.getPoster().toString()))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getID() {
        return ID;
    }

    public Long getViews() {
        return Views;
    }

    public Long getLikes() {
        return Likes;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Rate getContentRate() {
        return ContentRate;
    }

    public void setContentRate(Rate contentRate) {
        ContentRate = contentRate;
    }

    public ArrayList<Genre> getGenres() {
        return Genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        Genres = genres;
    }

    public double getScore() {
        return Score;
    }

    public void setScore(double score) {
        Score = score;
    }

    public String[][] getInfo() {
        return Info;
    }

    public void setInfo(String[][] info) {
        Info = info;
    }

    public boolean isVisibility() {
        return Visibility;
    }

    public void setVisibility(boolean visibility) {
        Visibility = visibility;
    }

    public URL getPoster() {
        return Poster;
    }

    public void setPoster(URL poster) {
        Poster = poster;
    }

    public Content(String id, String name, String description, Rate contentRate, ArrayList<Genre> genres, double score,
            long views, long likes, String[][] info, boolean visibility, URL poster) {
        ID = id;
        Name = name;
        Description = description;
        ContentRate = contentRate;
        Genres = genres;
        Score = score;

        Views = views;
        Likes = likes;
        Info = info;
        Visibility = visibility;
        Poster = poster;
    }

    public Content(String name, String description, Rate contentRate, ArrayList<Genre> genres, double score,
            String[][] info, boolean visibility, URL poster) {
        this(GenerateID(), name, description, contentRate, genres, score, 0, 0, info, visibility, poster);
    }

    public String GetID() {
        return ID;
    }

    public String GenerateInvitationLink() {
        String Text = "╔";
        String InvitaionText1 = "Hey Lets Watch " + getName() + " On HATTube !";
        String InvitaionText2 = "ID = " + getID();

        int len = InvitaionText1.length() + 10;
        int diff = len - InvitaionText2.length();

        for (int i = 0; i < len; i++)
            Text += "═";

        Text += "╗\n║";

        for (int i = 0; i < len; i++)
            Text += " ";

        Text += "║\n║     " + InvitaionText1 + "     ║" + "\n║";

        for (int i = 0; i < diff / 2; i++)
            Text += " ";

        Text += InvitaionText2;

        for (int i = 0; i < (diff + 1) / 2; i++)
            Text += " ";

        Text += "║\n║";
        for (int i = 0; i < len; i++)
            Text += " ";

        Text += "║\n╚";

        for (int i = 0; i < len; i++)
            Text += "═";

        Text += "╝";
        return Text;
    }

    public static String GenerateID() {
        return IDGenerator.RandomID("HCNT", 10);
    }

    @Override
    public void Like() {
        DataUpdator.Like(Table.Contents, ID);
    }

    @Override
    public void View() {
        DataUpdator.View(ID);
    }

    public static void CheckImages() {
        ImageDownloader.setDownloaded(false);

        ArrayList<String> Images = DataSelector.Select(Table.Contents).GetColumn("Poster");
        ArrayList<String> Names = DataSelector.Select(Table.Contents).GetColumn("Name");

        int index = 0;
        while (index < Images.size()) {
            if (new File("resource/images/posters/" + Names.get(index) + ".png").exists()
                    || new File("resource/images/posters/" + Names.get(index) + ".jpg").exists()
                    || new File("resource/images/posters/" + Names.get(index) + ".jpeg").exists()) {
                Images.remove(index);
                Names.remove(index);
            } else {
                index++;
            }
        }

        ImageDownloader.setDownloaded(Images.size() == 0);

        for (int i = 0; i < Images.size(); i++) {
            final String ImageUrl = Images.get(i);
            final String ImageName = Names.get(i);
            final boolean change = i == Images.size() - 1;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    ImageDownloader.DownloadImage(ImageUrl, ImageName, change, "resource/images/posters/");
                }
            }).start();
        }
    }

    public boolean BelongsTo(String channelID) {
        try {
            return (((Channel) DataSelector.Select(Table.Channels, new String[] { "ID='" + channelID + "'" })
                    .GetFirstResult()).getContents().contains(this.getID()));
        } catch (Exception e) {
            return false;
        }
    }
}

abstract class ContentInheritance extends Unique implements Viewable, Likable {
    long Views;
    long Likes;
}
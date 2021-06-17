package model;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import database.DataSelector;
import database.DataUpdator;
import database.DataSelector.Table;
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

    public ArrayList<Comment> getComments() {
        return Comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        Comments = comments;
    }

    ArrayList<Comment> Comments;

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

    public String GenerateLink() {
        // TODO "hey watch this : #...{ID}"
        return "";
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

        for (int i = 0; i < Images.size(); i++) {
            final String ImageUrl = Images.get(i);
            final String ImageName = Names.get(i);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    ImageDownloader.DownloadImage(ImageUrl, ImageName);
                }
            }).start();
        }
    }
}

abstract class ContentInheritance extends Unique implements Viewable, Likable {
    long Views;
    long Likes;
}
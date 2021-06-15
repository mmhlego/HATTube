package model;

import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;

import tools.IDGenerator;

public class Content extends ContentInheritance {
    String Name, Description;
    Rate ContentRate;
    ArrayList<Genre> Genres;
    double Score;
    Gson Info;
    boolean Visibility;
    URL Poster;
    ArrayList<Link> Resources;

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

    public Gson getInfo() {
        return Info;
    }

    public void setInfo(Gson info) {
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

    public ArrayList<Link> getResources() {
        return Resources;
    }

    public void setResources(ArrayList<Link> resources) {
        Resources = resources;
    }

    public ArrayList<Comment> getComments() {
        return Comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        Comments = comments;
    }

    ArrayList<Comment> Comments;

    public Content(String id, String name, String description, Rate contentRate, ArrayList<Genre> genres, double score,
            long views, long likes, Gson info, boolean visibility, URL poster) {
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

    public Content(String name, String description, Rate contentRate, ArrayList<Genre> genres, double score, Gson info,
            boolean visibility, URL poster) {
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
        // TODO
    }

    @Override
    public void View() {
        // TODO
    }

    public static void CheckImages() {
        // TODO
    }
}

abstract class ContentInheritance extends Unique implements Viewable, Likable {
    long Views;
    long Likes;
}
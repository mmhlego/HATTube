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
    ArrayList<Comment> Comments;

    //TODO Constructor

    public String GenerateLink() {
        // TODO "hey watch this : #...{ID}"
        return "";
    }

    @Override
    public void GenerateID() {
        String ID = IDGenerator.RandomID("HCNT", 10);
        // TODO
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
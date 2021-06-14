package model;

import java.util.ArrayList;

import com.google.gson.Gson;

import javafx.scene.image.Image;

public class Content extends ContentInheritance {
    String Name, Description;
    Rate ContentRate;
    ArrayList<Genre> Genres;
    double Score;
    Gson Info;
    boolean Visibility;
    Image Poster;
    ArrayList<Link> Resources;
    ArrayList<Comment> Comments;

    public String GenerateLink() {
        // TODO "hey watch this : #...{ID}"
        return "";
    }

    @Override
    public void GenerateID() {
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
}

abstract class ContentInheritance extends Unique implements Viewable, Likable {
    long Views;

    long Likes;
}
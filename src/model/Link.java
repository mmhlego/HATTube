package model;

import java.net.URL;

import tools.IDGenerator;

public class Link extends Unique {
    String ContentID, Name, Description;

    public String getID() {
        return ID;
    }

    public String getContentID() {
        return ContentID;
    }

    public void setContentID(String contentID) {
        ContentID = contentID;
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

    public URL getUrl() {
        return Url;
    }

    public void setUrl(URL url) {
        Url = url;
    }

    URL Url;

    public Link(String id, String contentID, String name, String description, URL url) {
        ID = id;
        ContentID = contentID;
        Name = name;
        Description = description;
        Url = url;
    }

    public Link(String contentID, String name, String description, URL url) {
        this(GenerateID(), contentID , name , description , url);
    }

    public static String GenerateID() {
        return IDGenerator.RandomID("HLNK", 16);
    }
}

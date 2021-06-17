package model;

import java.util.ArrayList;

import tools.IDGenerator;

public class Channel extends Unique {
    private String OwnerID, ChannelName;
    private ArrayList<String> Contents;

    public void setOwnerID(String ownerID) {
        OwnerID = ownerID;
    }

    public void setChannelName(String channelName) {
        ChannelName = channelName;
    }

    public ArrayList<String> getContents() {
        return Contents;
    }

    public void setContents(ArrayList<String> contents) {
        Contents = contents;
    }

    public Channel(String id, String ownerID, String channelName, ArrayList<String> contents) {
        ID = id;
        OwnerID = ownerID;
        ChannelName = channelName;
        Contents = contents;
    }

    public Channel(String id, String ownerID, String channelName) {
        this(id, ownerID, channelName, new ArrayList<String>());
    }

    public Channel(String ownerID, String channelName) {
        this(GenerateID(), ownerID, channelName, new ArrayList<String>());
    }

    public String GetID() {
        return ID;
    }

    public String getOwnerID() {
        return OwnerID;
    }

    public String getChannelName() {
        return ChannelName;
    }

    public static String GenerateID() {
        return IDGenerator.RandomID("HCNL", 8);
    }
}

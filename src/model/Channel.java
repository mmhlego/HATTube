package model;

import java.util.ArrayList;

import com.google.gson.Gson;

import tools.IDGenerator;

public class Channel extends Unique {
    private String OwnerID, ChannelName;
    private ArrayList<Content> Contents;

    public Channel(String id, String ownerID, String channelName, ArrayList<Content> contents) {
        ID = id;
        OwnerID = ownerID;
        ChannelName = channelName;
        Contents = contents;
    }

    public Channel(String id,String ownerID, String channelName) {
        this(id, ownerID, channelName, new ArrayList<Content>());
    }

    public Channel(String ownerID, String channelName) {
        this(GenerateID(), ownerID, channelName, new ArrayList<Content>());
    }

    public String GetID(){
        return ID;
    }

    public String getOwnerID() {
        return OwnerID;
    }

    public String getChannelName() {
        return ChannelName;
    }

    public ArrayList<Content> getContents() {
        return Contents;
    }

    public String getContentsString() {
        ArrayList<String> ans=new ArrayList<>();
        for (Content cont : Contents) {
            ans.add(cont.GetID());
        }

        return new Gson().toJson(ans);
    }

    public static String GenerateID() {
        return IDGenerator.RandomID("HCNL", 8);
    }
}

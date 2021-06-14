package model;

import java.util.ArrayList;

import tools.IDGenerator;

public class Channel extends Unique {
    private String OwnerID, ChannelName;
    private ArrayList<Content> Contents;

    public String getOwnerID() {
        return OwnerID;
    }

    public String getChannelName() {
        return ChannelName;
    }

    public ArrayList<Content> getContents() {
        return Contents;
    }

    public Channel(String ownerID, String channelName, String iD) {
        OwnerID = ownerID;
        ChannelName = channelName;
        //this.ID = iD;
        ID = "1";
    }

    @Override
    public void GenerateID() {
        String ID = IDGenerator.RandomID("HCNL", 8);
        // TODO 
    }
}

package model;

import java.net.URL;

import tools.IDGenerator;

public class Link extends Unique {
    String ContentID, Name, Description;
    URL Url;

    @Override
    public void GenerateID() {
        String ID = IDGenerator.RandomID("HLNK", 16);
        // TODO
    }
}

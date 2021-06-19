package samples;

import model.Content;

class ContentCreator {
    public static Content CreateContent(int number, String channelID) {
        String ID = Content.GenerateID();
        String Name = "Content #" + number + " for Channel " + channelID;

        // TODO

        //new Content(id, name, description, contentRate, genres, score, views, likes, info, visibility, poster)
        return null;
    }
}

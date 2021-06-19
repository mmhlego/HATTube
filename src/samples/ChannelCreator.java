package samples;

import java.util.ArrayList;
import java.util.Random;

import database.DataAdder;
import model.Channel;
import model.Comment;
import model.Content;
import model.Link;

class ChannelCreator {
    protected static Channel CreateChannel(String ID, String OwnerID) {
        ArrayList<String> Contents = new ArrayList<String>();

        Random random = new Random(System.currentTimeMillis());

        for (int i = 0; i < random.nextInt(10); i++) {
            Content content = ContentCreator.CreateContent(i + 1, ID);
            Contents.add(content.GetID());
            DataAdder.AddData(content);
            System.out.println("Content #" + i + " Added");

            for (int j = 0; j < random.nextInt(6); j++) {
                Link link = LinkCreator.CreateLink(i + 1, content.GetID());
                DataAdder.AddData(link);
                System.out.println("Link #" + j + " Added");
            }

            for (int j = 0; j < random.nextInt(20); j++) {
                Comment comment = CommentCreator.CreateComment(content.GetID());
                DataAdder.AddData(comment);
                System.out.println("Comment #" + j + " Added");
            }
        }

        String ChannelName = OwnerID + "-" + ID;

        return new Channel(ID, OwnerID, ChannelName, Contents);
    }
}

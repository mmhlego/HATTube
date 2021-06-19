package samples;

import java.util.ArrayList;
import java.util.Random;

import model.Channel;
import model.Content;

class ChannelCreator {
    public static Channel CreateChannel(String ID, String OwnerID) {
        ArrayList<String> Contents = new ArrayList<String>();

        Random random = new Random(System.currentTimeMillis());

        for (int i = 0; i < random.nextInt(10); i++) {
            Content content = ContentCreator.CreateContent(i + 1, ID);
            Contents.add(content.GetID());
        }

        String ChannelName = OwnerID + "-" + ID;

        return new Channel(ID, OwnerID, ChannelName, Contents);
    }
}

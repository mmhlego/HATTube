package samples;

import database.DataRemover;
import model.Channel;
import model.User;

public class FullDataCreator {
    public static void CreateData(int amount) {
        DataRemover.ClearAllTables();

        for (int i = 0; i < amount; i++) {
            User user = UserCreator.CreateUser(i + 1);
            if (!user.getChannelID().equals("")) {
                Channel channel = ChannelCreator.CreateChannel(user.getChannelID(), user.getID());
            }
        }
    }
}

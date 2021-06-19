package samples;

import database.DataAdder;
import database.DataRemover;
import model.Channel;
import model.User;

public class FullDataCreator {
    public static void CreateData(int amount) {
        database.DataBase.Connect();

        DataRemover.ClearAllTables();

        for (int i = 0; i < amount; i++) {
            User user = UserCreator.CreateUser(i + 1);
            DataAdder.AddData(user);
            System.out.println("User Added");

            if (!user.getChannelID().equals("")) {
                Channel channel = ChannelCreator.CreateChannel(user.getChannelID(), user.getID());
                DataAdder.AddData(channel);
                System.out.println("Channel Added");
            }
        }

        System.out.println("Finished");
    }
}

package samples;

import java.time.LocalDate;
import java.util.Random;

import model.Comment;
import model.User;

class CommentCreator {
    private static Random random = new Random(System.currentTimeMillis());

    protected static Comment CreateComment(String contentID) {
        String ID = Comment.GenerateID();

        String Text = "Comment with Id of " + ID;
        LocalDate Date = LocalDate.of(2010 + random.nextInt(11), random.nextInt(12) + 1, random.nextInt(29) + 1);
        long likes = random.nextInt(100000);

        return new Comment(ID, User.GenerateID(), contentID, Text, Date, likes);
    }
}

package model;

import java.time.LocalDate;

public class Comment extends CommentInheritance {
    String WriterID, ContentID, Text;
    LocalDate Date;

    @Override
    public void Like() {
        // TODO   
    }
}

abstract class CommentInheritance implements Likable {
    long Likes;
}

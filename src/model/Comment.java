package model;

import java.time.LocalDate;

import tools.IDGenerator;

public class Comment extends CommentInheritance {
    String WriterID, ContentID, Text;
    LocalDate Date;

    @Override
    public void Like() {
        // TODO   
    }

    @Override
    public void GenerateID() {
        String ID = IDGenerator.RandomID("HCMT", 16);
        // TODO Auto-generated method stub
    }
}

abstract class CommentInheritance extends Unique implements Likable {
    long Likes;
}

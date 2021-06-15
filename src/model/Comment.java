package model;

import java.time.LocalDate;

import tools.IDGenerator;

public class Comment extends CommentInheritance {
    String WriterID, ContentID, Text;

    public String getID(){
        return ID;
    }

    public long getLikes(){
        return Likes;
    }

    public String getWriterID() {
        return WriterID;
    }

    public void setWriterID(String writerID) {
        WriterID = writerID;
    }

    public String getContentID() {
        return ContentID;
    }

    public void setContentID(String contentID) {
        ContentID = contentID;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    LocalDate Date;

    public Comment(String id, String writerid, String contentid, String text, LocalDate date) {
        ID = id;
        WriterID = writerid;
        ContentID = contentid;
        Text = text;
        Date = date;
    }

    public Comment(String writerid, String contentid, String text) {
        this(GenerateID() , writerid, contentid, text , LocalDate.now());
    }

    @Override
    public void Like() {
        // TODO
    }

    public static String GenerateID() {
        return IDGenerator.RandomID("HCMT", 16);
    }
}

abstract class CommentInheritance extends Unique implements Likable {
    long Likes;
}

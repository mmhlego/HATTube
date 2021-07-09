package common.controller.component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.jfoenix.controls.JFXTextArea;

import database.DataSelector;
import database.DataUpdator;
import database.DataSelector.Table;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Comment;
import model.User;
import user.UserController;

public class CommentsComponent {
    @FXML
    private ImageView CommentsOwnerIMG;
    @FXML
    private Label CommentsOwnerLBL;
    @FXML
    private JFXTextArea CommentTextArea;
    @FXML
    private Label LikeLBL;
    @FXML
    private ImageView LikeBTN;

    public void SetComment(Comment comment) {
        CommentsOwnerIMG.setImage(User.RandomUserImage());

        ArrayList<String> DBUsername = DataSelector
                .Select(Table.Users, new String[] { "ID='" + comment.getWriterID() + "'" }).GetColumn("Username");
        String username = DBUsername.size() > 0 ? DBUsername.get(0) : comment.getWriterID();

        CommentsOwnerLBL
                .setText(username + " : (" + comment.getDate().format(DateTimeFormatter.ofPattern("YYYY/MM/dd")) + ")");
        CommentTextArea.setText(comment.getText());

        LikeBTN.setDisable(!UserController.LoggedIn());
        LikeLBL.setText(Long.toString(comment.getLikes()));
        LikeBTN.setOnMouseClicked(e -> {
            if (LikeLBL.getText().equals(Long.toString(comment.getLikes()))) {
                LikeLBL.setText(Long.toString(comment.getLikes() + 1));
                DataUpdator.Like(Table.Comments, comment.getID());
            }
        });
    }
}

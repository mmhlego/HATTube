package common.controller.component;

import java.time.format.DateTimeFormatter;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Comment;
import user.UserController;

public class CommentsComponent {
    @FXML
    private ImageView CommentsOwnerIMG;
    @FXML
    private Label CommentsOwnerLBL;
    @FXML
    private JFXTextArea CommentTextArea;
    @FXML
    private Label DislikeLBL;
    @FXML
    private ImageView DislikeBTN;
    @FXML
    private Label LikeLBL;
    @FXML
    private ImageView LikeBTN;

    public void SetComment(Comment comment) {
        //CommentsOwnerIMG //TODO
        CommentsOwnerLBL.setText("User #" + comment.getWriterID() + " : ("
                + comment.getDate().format(DateTimeFormatter.ofPattern("YYYY/MM/dd")) + ")"); //TODO
        CommentTextArea.setText(comment.getText());

        DislikeBTN.setOnMouseClicked(e -> System.out.println("Dislike")); //TODO
        DislikeBTN.setVisible(UserController.LoggedIn());
        DislikeLBL.setVisible(UserController.LoggedIn());
        DislikeLBL.setText("-----"); //TODO

        LikeBTN.setOnMouseClicked(e -> System.out.println("Like")); //TODO
        LikeBTN.setVisible(UserController.LoggedIn());
        LikeLBL.setVisible(UserController.LoggedIn());
        LikeLBL.setText(Long.toString(comment.getLikes()));
    }
}

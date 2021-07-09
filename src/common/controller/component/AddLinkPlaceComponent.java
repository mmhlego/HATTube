package common.controller.component;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Link;

public class AddLinkPlaceComponent {
    @FXML
    private JFXTextField NameTXF;
    @FXML
    private JFXTextField DescriptionTXF;
    @FXML
    private JFXTextField URLTXF;
    @FXML
    private Label LinkID;

    public void setLink(Link link) {
        NameTXF.setText(link.getName());
        DescriptionTXF.setText(link.getDescription());
        URLTXF.setText(link.getUrl().toString());
        LinkID.setText(link.getID());
    }
}

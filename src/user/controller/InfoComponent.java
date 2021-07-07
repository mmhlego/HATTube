package user.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class InfoComponent {

    @FXML
    private TextField NameTXF;

    @FXML
    private TextField DetailTXF;

    public TextField getNameTXF() {
        return NameTXF;
    }

    public void setNameTXF(TextField nameTXF) {
        NameTXF = nameTXF;
    }

    public TextField getDetailTXF() {
        return DetailTXF;
    }

    public void setDetailTXF(TextField detailTXF) {
        DetailTXF = detailTXF;
    }
}

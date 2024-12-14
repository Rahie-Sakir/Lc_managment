package com.example.lc_management_software;

import com.example.lc_management_software.model_classes.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ClientRequestingCancellations {
    @javafx.fxml.FXML
    private Label errorDisplay;
    @javafx.fxml.FXML
    private TextArea explanationField;

    @javafx.fxml.FXML
    public void initialize() {
    }

    private User user;

    public void initData(User user) {
        this.user = user;
    }

    @javafx.fxml.FXML
    public void handleProcessed(ActionEvent actionEvent) {
    }
}
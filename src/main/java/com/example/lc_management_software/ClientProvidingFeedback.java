package com.example.lc_management_software;

import com.example.lc_management_software.model_classes.User;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

public class ClientProvidingFeedback {
    @javafx.fxml.FXML
    private TextArea feedbackField;

    @javafx.fxml.FXML
    public void initialize() {
    }

    private User user;

    public void initData(User user) {
        this.user = user;
    }

    @javafx.fxml.FXML
    public void handleSubmit(ActionEvent actionEvent) {
    }
}
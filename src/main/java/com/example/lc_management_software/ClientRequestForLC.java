package com.example.lc_management_software;

import com.example.lc_management_software.model_classes.User;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientRequestForLC {
    @javafx.fxml.FXML
    private TextField purposeCol;
    @javafx.fxml.FXML
    private TextField ammountField;
    @javafx.fxml.FXML
    private ComboBox currencyCombo;
    @javafx.fxml.FXML
    private ComboBox typeCOmbo;
    @javafx.fxml.FXML
    private TextArea detailsCombo;

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
package com.example.lc_management_software;

import com.example.lc_management_software.model_classes.User;
import javafx.scene.control.ComboBox;

public class ClientMakingPayments {
    @javafx.fxml.FXML
    private ComboBox methodCombo;

    @javafx.fxml.FXML
    public void initialize() {
    }

    private User user;

    public void initData(User user) {
        this.user = user;
    }
}
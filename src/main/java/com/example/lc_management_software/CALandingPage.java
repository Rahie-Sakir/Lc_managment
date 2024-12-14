package com.example.lc_management_software;

import com.example.lc_management_software.model_classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class CALandingPage {
    @javafx.fxml.FXML
    private BorderPane borderpane;
    @javafx.fxml.FXML
    private Label name;

    @javafx.fxml.FXML
    public void initialize() {
    }

    private User user;

    public void initData(User user) {
        this.user = user;
        name.setText(user.getName());
    }

    @javafx.fxml.FXML
    public void handleAnalyticsData(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CA_AnalyticsData.fxml"));
        Parent root = loader.load();

        CAAnalyticsData controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);
    }

    @javafx.fxml.FXML
    public void handleFienncialReport(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_create_a_new_account.fxml"));
        Parent root = loader.load();

        AdminCreateANewAccount controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);

    }

    @javafx.fxml.FXML
    public void handleFinentialState(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_create_a_new_account.fxml"));
        Parent root = loader.load();

        AdminCreateANewAccount controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);

    }

    @javafx.fxml.FXML
    public void handleBandCreditdata(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_create_a_new_account.fxml"));
        Parent root = loader.load();

        AdminCreateANewAccount controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);

    }

    @javafx.fxml.FXML
    public void handleClientInformaiton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_create_a_new_account.fxml"));
        Parent root = loader.load();

        AdminCreateANewAccount controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);

    }

    @javafx.fxml.FXML
    public void handlePerformInvoice(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_create_a_new_account.fxml"));
        Parent root = loader.load();

        AdminCreateANewAccount controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);

    }

    @javafx.fxml.FXML
    public void handleMarchentInfo(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_create_a_new_account.fxml"));
        Parent root = loader.load();

        AdminCreateANewAccount controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);

    }

    @javafx.fxml.FXML
    public void handleLogout(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_create_a_new_account.fxml"));
        Parent root = loader.load();

        AdminCreateANewAccount controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);

    }
}
package com.example.lc_management_software;

import com.example.lc_management_software.model_classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLandingPage {
    @javafx.fxml.FXML
    private BorderPane borderpane;
    @javafx.fxml.FXML
    private Label name;

    @javafx.fxml.FXML
    public void initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_Retrieve_all_users.fxml"));
        Parent root = loader.load();

        AdminRetrieveAllUsers controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);
    }

    private User user;

    public void initData(User user) {
        this.user = user;
    }

    @javafx.fxml.FXML
    public void handleCreateAccount(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_create_a_new_account.fxml"));
        Parent root = loader.load();

        AdminCreateANewAccount controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);
    }

    @javafx.fxml.FXML
    public void handleAccessLog(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_checking_access_logs.fxml"));
        Parent root = loader.load();

        AdminCheckingAccessLogs controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);
    }

    @javafx.fxml.FXML
    public void handleAllUser(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_Retrieve_all_users.fxml"));
        Parent root = loader.load();

        AdminRetrieveAllUsers controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);
    }

    @javafx.fxml.FXML
    public void handleChangeUserInfo(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_changeUserInformation.fxml"));
        Parent root = loader.load();

        AdminChangeUserInformation controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);
    }

    @Deprecated
    public void handleCheckingRegister(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_checking_register_data.fxml"));
        Parent root = loader.load();

        AdminCheckingRegisterData controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);
    }

    @javafx.fxml.FXML
    public void handleLogout(ActionEvent actionEvent) throws IOException {
        Parent mainSceneParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene1 = new Scene(mainSceneParent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }
}
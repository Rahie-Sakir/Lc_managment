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

public class COLandingPage {
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
    public void handleReviewLCReq(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_checking_register_data.fxml"));
        Parent root = loader.load();

        AdminCheckingRegisterData controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);
    }

    @javafx.fxml.FXML
    public void handleVerifyDocs(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CO_verifying_document.fxml"));
        Parent root = loader.load();

        AdminCheckingRegisterData controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);
    }

    @javafx.fxml.FXML
    public void handleCheckMerchents(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CO_checking_merchants.fxml"));
        Parent root = loader.load();

        COCheckingMerchants controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);
    }

    @javafx.fxml.FXML
    public void handleCleckPolicis(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CO_Check_policies.fxml"));
        Parent root = loader.load();

        COCheckPolicies controller = loader.getController();
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

    @javafx.fxml.FXML
    public void handleRiskAssessments(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CO_conducting_risk_assessment.fxml"));
        Parent root = loader.load();

        COConductingRiskAssessment controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);
    }
}
package com.example.lc_management_software;

import com.example.lc_management_software.model_classes.AccessLog;
import com.example.lc_management_software.model_classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login {
    @javafx.fxml.FXML
    private PasswordField passField;
    @javafx.fxml.FXML
    private TextField emailField;
    @javafx.fxml.FXML
    private Label error;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void handleSubmit(ActionEvent actionEvent) throws IOException {
        ArrayList<User> users = new ArrayList<>();

        String email = emailField.getText();
        String password = passField.getText();

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            System.out.println("Error: Invalid email format.");
            error.setText("Error: Invalid email format.");
            return;
        }
        if (password.length() < 6) {
            error.setText("Error: Passwords must contain at least 6 characters.");
            return;
        }

        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            f = new File("Users.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            User u;
            try {
                while (true) {
                    u = (User) ois.readObject();
                    users.add(u);
                }
            } catch (Exception e) {
            }
        } catch (IOException ex) {
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
            }
        }

        for (User u : users) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                String role = u.getRole();
                FXMLLoader loader = new FXMLLoader();
                Parent personViewParent = null;
                Scene personViewScene = null;
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                switch (role) {
                    case "Admin":
                        loader.setLocation(getClass().getResource("Admin_landingPage.fxml"));
                        personViewParent = loader.load();
                        personViewScene = new Scene(personViewParent);
                        AdminLandingPage controller = loader.getController();
                        controller.initData(u);
                        break;
                    case "Credit Analyst":
                        loader.setLocation(getClass().getResource("CA_LandingPage.fxml"));
                        personViewParent = loader.load();
                        personViewScene = new Scene(personViewParent);
                        CALandingPage controllerCreditAnalyst = loader.getController();
                        controllerCreditAnalyst.initData(u);
                        break;
                    case "IT Officer":
                        loader.setLocation(getClass().getResource("ITOfficer_landingPage.fxml"));
                        personViewParent = loader.load();
                        personViewScene = new Scene(personViewParent);
//                        ITOfficerLandingPage controllerITOfficer = loader.getController();
//                        controllerITOfficer.initData(u);
                        break;
                    case "Manager":
                        loader.setLocation(getClass().getResource("Manager_landingPage.fxml"));
                        personViewParent = loader.load();
                        personViewScene = new Scene(personViewParent);
//                        ManagerLandingPage controllerManager = loader.getController();
//                        controllerManager.initData(u);
                        break;
                    case "Client":
                        loader.setLocation(getClass().getResource("Client_landingPage.fxml"));
                        personViewParent = loader.load();
                        personViewScene = new Scene(personViewParent);
//                        ClientLandingPage controllerClient = loader.getController();
//                        controllerClient.initData(u);
                        break;
                    case "Compliance officer":
                        loader.setLocation(getClass().getResource("CO_LandingPage.fxml"));
                        personViewParent = loader.load();
                        personViewScene = new Scene(personViewParent);
                        COLandingPage controllerComplianceOfficer = loader.getController();
                        controllerComplianceOfficer.initData(u);
                        break;
                    default:
                        error.setText("Error: Invalid role.");
                        return;

                }

                if (personViewParent != null && personViewScene != null) {
                    window.setScene(personViewScene);
                    window.show();
                }


//                saving the access log
                FileOutputStream fos = null;
                ObjectOutputStream oos = null;
                try {
                    f = new File("AccessLogs.bin");
                    if (f.exists()) {
                        fos = new FileOutputStream(f, true);
                        oos = new AppendableObjectOutputStream(fos);
                    } else {
                        fos = new FileOutputStream(f);
                        oos = new ObjectOutputStream(fos);
                    }
                    AccessLog al = new AccessLog(email, "Logged din" + role, LocalDate.now());
                    oos.writeObject(al);

                    System.out.println("User successfully registered.");
                } catch (IOException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        if (oos != null) {
                            oos.close();
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                return;
            }
        }
        error.setText("Error: Invalid email or password.");

    }

    @javafx.fxml.FXML
    public void goToSignup(ActionEvent actionEvent) throws IOException {
        Parent mainSceneParent = FXMLLoader.load(getClass().getResource("Signup.fxml"));
        Scene scene1 = new Scene(mainSceneParent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();

    }
}
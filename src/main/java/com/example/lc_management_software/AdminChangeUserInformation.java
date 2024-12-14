package com.example.lc_management_software;

import com.example.lc_management_software.model_classes.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminChangeUserInformation {
    @javafx.fxml.FXML
    private TableColumn<User, String> addressColumn;
    @javafx.fxml.FXML
    private TableColumn<User, String> nameColumn;
    @javafx.fxml.FXML
    private TableColumn<User, String> roleColumn;
    @javafx.fxml.FXML
    private TableColumn<User, String> statusColumn;
    @javafx.fxml.FXML
    private TableColumn editColumn;
    @javafx.fxml.FXML
    private TableColumn<User, LocalDate> dobColumn;
    @javafx.fxml.FXML
    private TableColumn<User, LocalDate> regDateColumn;
    @javafx.fxml.FXML
    private TableColumn<User, String> phoneColumn;
    @javafx.fxml.FXML
    private TableColumn<User, String> emailColumn;
    @javafx.fxml.FXML
    private TextField addressTextField;
    @javafx.fxml.FXML
    private TextField phoneTextField;
    @javafx.fxml.FXML
    private TextField emailTextField;
    @javafx.fxml.FXML
    private TextField nameTextField;
    @javafx.fxml.FXML
    private TextField statusTextField;
    @javafx.fxml.FXML
    private TextField roleTextField;
    @javafx.fxml.FXML
    private DatePicker dobPicker;

    ObservableList<User> userList = FXCollections.observableArrayList();
    @javafx.fxml.FXML
    private TableView<User> table;

    @javafx.fxml.FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<User, String>("address"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<User, String>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<User, String>("status"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<User, LocalDate>("dob"));
        regDateColumn.setCellValueFactory(new PropertyValueFactory<User, LocalDate>("regDate"));

        editColumn.setCellFactory(col -> {
            return new TableCell<User, String>() {
                private final Button editButton = new Button("Edit");

                {
                    editButton.setOnAction(event -> {
                        User selectedUser = getTableView().getItems().get(getIndex());
                        populateTextFields(selectedUser);
                    });
                    setGraphic(editButton);
                }

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || getIndex() < 0 || getIndex() >= getTableView().getItems().size()) {
                        setGraphic(null); // Hide button if the row is empty
                    } else {
                        setGraphic(editButton); // Show button for non-empty rows
                    }
                }
            };
        });

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
                    userList.add(u);
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

        table.setItems(userList);
    }

    private User user;

    public void initData(User user) {
        this.user = user;
    }

    @javafx.fxml.FXML
    public void handleUpdate(ActionEvent actionEvent) {
        if (user != null) {
            // Update the user object with values from the text fields
            user.setName(nameTextField.getText());
            user.setAddress(addressTextField.getText());
            user.setPhone(phoneTextField.getText());
            user.setEmail(emailTextField.getText());
            user.setRole(roleTextField.getText());
            user.setStatus(statusTextField.getText());
            user.setDob(dobPicker.getValue());

            File f = new File("Users.bin");
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;

            try {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);

                // Clear the file and rewrite the entire list of users including the updated user
                for (User u : userList) {
                    if (u.getEmail().equals(user.getEmail())) {
                        oos.writeObject(user);
                    } else {
                        oos.writeObject(u);
                    }
                }
                System.out.println("User successfully updated and file rewritten.");
            } catch (IOException ex) {
                Logger.getLogger(AdminChangeUserInformation.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (oos != null) {
                        oos.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(AdminChangeUserInformation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void populateTextFields(User user) {
        nameTextField.setText(user.getName());
        addressTextField.setText(user.getAddress());
        phoneTextField.setText(user.getPhone());
        emailTextField.setText(user.getEmail());
        roleTextField.setText(user.getRole());
        statusTextField.setText(user.getStatus());
        dobPicker.setValue(user.getDob());
    }
}
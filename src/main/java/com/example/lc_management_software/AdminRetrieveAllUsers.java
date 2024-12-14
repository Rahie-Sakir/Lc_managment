package com.example.lc_management_software;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import com.example.lc_management_software.model_classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminRetrieveAllUsers {
    @javafx.fxml.FXML
    private TableColumn<User, String> addressColumn;
    @javafx.fxml.FXML
    private TableColumn<User, String> nameColumn;
    @javafx.fxml.FXML
    private TableColumn<User, String> roleColumn;
    @javafx.fxml.FXML
    private TableColumn<User, LocalDate> registrationDateColumn;
    @javafx.fxml.FXML
    private TableColumn<User, Void> restrictColumn;
    @javafx.fxml.FXML
    private TableColumn<User, Void> banColumn;
    @javafx.fxml.FXML
    private TableColumn<User, Void> deleteColumn;
    @javafx.fxml.FXML
    private TableColumn<User, LocalDate> dobColumn;
    @javafx.fxml.FXML
    private TableColumn<User, String> phoneColumn;
    @javafx.fxml.FXML
    private TableColumn<User, String> emailColumn;
    @javafx.fxml.FXML
    private TableView<User> table;


    ObservableList<User> users = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {
        emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<User, String>("address"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<User, String>("phone"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<User, LocalDate>("dob"));
        registrationDateColumn.setCellValueFactory(new PropertyValueFactory<User, LocalDate>("regDate"));

        restrictColumn.setCellFactory(col -> new TableCell<User, Void>() {
            private final Button btn = new Button("Restrict");

            {
                btn.setOnAction(e -> {
                    User user = getTableView().getItems().get(getIndex());
                    if (user != null) {
                        user.setStatus("restricted");
                        updateButtonStates();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableView().getItems().get(getIndex()) == null) {
                    setGraphic(null);
                } else if (getTableView().getItems().get(getIndex()).getStatus().equals("restricted")) {
                    Label label = new Label("Restricted");
                    setGraphic(label);
                } else {
                    setGraphic(btn);
                }
            }
        });

        banColumn.setCellFactory(col -> new TableCell<User, Void>() {
            private final Button btn = new Button("Ban");

            {
                btn.setOnAction(e -> {
                    User user = getTableView().getItems().get(getIndex());
                    if (user != null) {
                        user.setStatus("banned");
                        updateButtonStates();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableView().getItems().get(getIndex()) == null) {
                    setGraphic(null);
                } else if (getTableView().getItems().get(getIndex()).getStatus().equals("banned")) {
                    Label label = new Label("Banned");
                    setGraphic(label);
                } else {
                    setGraphic(btn);
                }
            }
        });

        deleteColumn.setCellFactory(col -> new TableCell<User, Void>() {
            private final Button btn = new Button("Delete");

            {
                btn.setOnAction(e -> {
                    User user = getTableView().getItems().get(getIndex());
                    users.remove(user);
                    rewriteUserFile();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
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

        table.setItems(users);

    }

    private User user;

    public void initData(User user) {
        this.user = user;
    }

    private void rewriteUserFile() {
        File f = new File("Users.bin");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            for (User u : users) {
                oos.writeObject(u);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void updateButtonStates() {
        table.refresh();
    }
}
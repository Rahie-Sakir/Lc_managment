package com.example.lc_management_software;

import com.example.lc_management_software.model_classes.AccessLog;
import com.example.lc_management_software.model_classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableArrayBase;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;

public class AdminCheckingAccessLogs {

    @javafx.fxml.FXML
    private TableColumn<AccessLog, LocalDate> dateCol;
    @javafx.fxml.FXML
    private TableColumn<AccessLog, String> emailCol;
    @javafx.fxml.FXML
    private TableColumn<AccessLog, String> logCol;
    @javafx.fxml.FXML
    private TableView<AccessLog> table;

    ObservableList<AccessLog> logs = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {
        dateCol.setCellValueFactory(new PropertyValueFactory<AccessLog, LocalDate>("date"));
        emailCol.setCellValueFactory(new PropertyValueFactory<AccessLog, String>("useremail"));
        logCol.setCellValueFactory(new PropertyValueFactory<AccessLog, String>("log"));


        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            f = new File("AccessLogs.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            AccessLog al;
            try {
                while (true) {
                    al = (AccessLog) ois.readObject();
                    logs.add(al);
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

        table.setItems(logs);
    }

    private User user;

    public void initData(User user) {
        this.user = user;
    }
}


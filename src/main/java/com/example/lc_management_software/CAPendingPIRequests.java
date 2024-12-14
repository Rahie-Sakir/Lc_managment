package com.example.lc_management_software;

import com.example.lc_management_software.model_classes.PIRequest;
import com.example.lc_management_software.model_classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class CAPendingPIRequests {
    @javafx.fxml.FXML
    private TableColumn<PIRequest, String> idCol;
    @javafx.fxml.FXML
    private TableColumn<PIRequest, String> Email;
    @javafx.fxml.FXML
    private TableColumn<PIRequest, LocalDate> dateCol;
    @javafx.fxml.FXML
    private TableColumn<PIRequest, String> statusCol;
    @javafx.fxml.FXML
    private TableView<PIRequest> table;


    ObservableList<PIRequest> pis = FXCollections.observableArrayList();
    @javafx.fxml.FXML
    private TableColumn<PIRequest, String> rejectCol;
    @javafx.fxml.FXML
    private TableColumn<PIRequest, String> acceptCol;

    @javafx.fxml.FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<PIRequest, String>("ID"));
        Email.setCellValueFactory(new PropertyValueFactory<PIRequest, String>("clientEmail"));
        dateCol.setCellValueFactory(new PropertyValueFactory<PIRequest, LocalDate>("date"));
        statusCol.setCellValueFactory(new PropertyValueFactory<PIRequest, String>("status"));

        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            f = new File("PIRequests.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            PIRequest pi;
            try {
                while (true) {
                    pi = (PIRequest) ois.readObject();
                    pis.add(pi);
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

        table.setItems(pis);

        acceptCol.setCellFactory(col -> new TableCell<PIRequest, String>() {
            private final Button acceptButton = new Button("Accept");

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    setGraphic(acceptButton);
                    acceptButton.setOnAction(event -> {
                        PIRequest request = getTableView().getItems().get(getIndex());
                        request.setStatus("Accepted");
                        updatePIRequestsFile();
                        table.refresh();
                    });
                }
            }
        });

        rejectCol.setCellFactory(col -> new TableCell<PIRequest, String>() {
            private final Button rejectButton = new Button("Reject");

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    setGraphic(rejectButton);
                    rejectButton.setOnAction(event -> {
                        PIRequest request = getTableView().getItems().get(getIndex());
                        request.setStatus("Rejected");
                        updatePIRequestsFile();
                        table.refresh();
                    });
                }
            }
        });
    }

    private User user;

    public void initData(User user) {
        this.user = user;
    }

    private void updatePIRequestsFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("PIRequests.bin"))) {
            for (PIRequest pi : pis) {
                oos.writeObject(pi);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
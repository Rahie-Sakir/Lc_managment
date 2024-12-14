package com.example.lc_management_software;

import com.example.lc_management_software.model_classes.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.List;

public class ClientUploadedRequiredDocumentation {
    @FXML
    private AnchorPane uploads;

    @FXML
    private ListView<HBox> fileListView;

    private ObservableList<File> uploadedFiles = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        fileListView.setItems(FXCollections.observableArrayList());
    }

    private User user;

    public void initData(User user) {
        this.user = user;
    }

    @FXML
    private void handleFileChoose() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Allowed Files", "*.pdf", "*.png", "*.jpg", "*.jpeg")
        );

        List<File> files = fileChooser.showOpenMultipleDialog(uploads.getScene().getWindow());

        if (files != null) {
            for (File file : files) {
                if (isValidFile(file)) {
                    addFileToList(file);
                } else {
                    showAlert("Invalid file type: " + file.getName());
                }
            }
        }
    }

    private boolean isValidFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".pdf") || name.endsWith(".png") ||
                name.endsWith(".jpg") || name.endsWith(".jpeg");
    }

    private void addFileToList(File file) {
        HBox fileRow = new HBox(10); // 10 is spacing between elements
        Label fileNameLabel = new Label(file.getName());
        Button deleteButton = new Button("X");

        deleteButton.setOnAction(e -> {
            fileListView.getItems().remove(fileRow);
            uploadedFiles.remove(file);
        });

        fileRow.getChildren().addAll(fileNameLabel, deleteButton);
        fileListView.getItems().add(fileRow);
        uploadedFiles.add(file);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleSubmit() {
        if (uploadedFiles.isEmpty()) {
            showAlert("Please upload at least one file");
            return;
        }
        // Handle the submission of files here
        System.out.println("Files ready for submission: " + uploadedFiles.size());
    }
}
package com.example.lc_management_software;

import com.example.lc_management_software.model_classes.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ClientConfirmingSubmissionAndReviewTerms {
    @FXML
    private TextArea termsTextArea;

    @FXML
    private Button confirmButton;

    @FXML
    private Label confirmationMessage;
    @FXML
    private Label summaryLabel;

    @FXML
    public void initialize() {
        // Initialization logic if needed
    }

    private User user;

    public void initData(User user) {
        this.user = user;
    }

    // Event 3: Handle confirmation
    @FXML
    public void handleConfirm() {
        // Logic to change request status to 'submitted'
        confirmationMessage.setText("Request has been submitted.");
        // Additional logic to send confirmation message or email
    }
}
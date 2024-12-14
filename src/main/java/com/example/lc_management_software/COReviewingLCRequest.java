package com.example.lc_management_software;

import com.example.lc_management_software.model_classes.LCRequest;
import com.example.lc_management_software.model_classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.time.LocalDate;

public class COReviewingLCRequest {
    @javafx.fxml.FXML
    private TableColumn<LCRequest, Integer> idCol;
    @javafx.fxml.FXML
    private TableColumn<LCRequest, String> statusCol;
    @javafx.fxml.FXML
    private TableColumn<LCRequest, LocalDate> requestDateCol;
    @javafx.fxml.FXML
    private TableColumn<LCRequest, Void> actionCol;
    @javafx.fxml.FXML
    private TableColumn<LCRequest, String> requesterIdCol;
    @javafx.fxml.FXML
    private TableView<LCRequest> table;

    // Add an ObservableList to hold LCRequest objects
    private ObservableList<LCRequest> lcRequestList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {

        idCol.setCellValueFactory(new PropertyValueFactory<LCRequest, Integer>("id"));
        requesterIdCol.setCellValueFactory(new PropertyValueFactory<LCRequest, String>("requesterID"));
        requestDateCol.setCellValueFactory(new PropertyValueFactory<LCRequest, LocalDate>("requestDate"));
        statusCol.setCellValueFactory(new PropertyValueFactory<LCRequest, String>("status"));

        // Define a cell factory for the action column
        Callback<TableColumn<LCRequest, Void>, TableCell<LCRequest, Void>> cellFactory = new Callback<TableColumn<LCRequest, Void>, TableCell<LCRequest, Void>>() {
            @Override
            public TableCell<LCRequest, Void> call(final TableColumn<LCRequest, Void> param) {
                final TableCell<LCRequest, Void> cell = new TableCell<LCRequest, Void>() {
                    final Button acceptButton = new Button("Accept");
                    final Button rejectButton = new Button("Reject");

                    {
                        acceptButton.setOnAction((ActionEvent e) -> {
                            LCRequest request = getTableView().getItems().get(getIndex());
                            // Update the request status
                            request.setStatus("Accepted");
                            // Remove action buttons
                            setGraphic(null);
                            getTableView().refresh(); // Optional: refresh the table if needed
                        });
                        rejectButton.setOnAction((ActionEvent e) -> {
                            LCRequest request = getTableView().getItems().get(getIndex());
                            // Update the request status
                            request.setStatus("Rejected");
                            // Remove action buttons
                            setGraphic(null);
                            getTableView().refresh(); // Optional: refresh the table if needed
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            LCRequest request = getTableView().getItems().get(getIndex());
                            HBox pane = new HBox(acceptButton, rejectButton);
                            pane.setSpacing(4);

                            // Enable or disable buttons based on the request status
                            if ("accepted".equals(request.getStatus())) {
                                acceptButton.setDisable(true);
                                rejectButton.setDisable(false);
                            } else if ("rejected".equals(request.getStatus())) {
                                acceptButton.setDisable(false);
                                rejectButton.setDisable(true);
                            } else {
                                acceptButton.setDisable(false);
                                rejectButton.setDisable(false);
                            }

                            setGraphic(pane);
                        }
                    }
                };
                return cell;
            }
        };

        actionCol.setCellFactory(cellFactory);

        // Populate the ObservableList with dummy LCRequest objects
        lcRequestList.add(new LCRequest(1, "maha1", LocalDate.now(), "accepted"));
        lcRequestList.add(new LCRequest(2, "repin", LocalDate.now().minusDays(1), "requested"));
        lcRequestList.add(new LCRequest(3, "riya", LocalDate.now().minusDays(2), "rejected"));
        lcRequestList.add(new LCRequest(4, "riya", LocalDate.now().minusDays(2), "accepted"));

        // Set the items of the table to the ObservableList
        table.setItems(lcRequestList);
    }

    private User user;

    public void initData(User user) {
        this.user = user;
    }
}
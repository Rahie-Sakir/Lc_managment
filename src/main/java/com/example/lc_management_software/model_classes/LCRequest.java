package com.example.lc_management_software.model_classes;

import java.time.LocalDate;

public class LCRequest {
    private int id; // Unique identifier for the request
    private String requesterID; // Name of the person making the request
    private LocalDate requestDate; // Date when the request was made
    private String status; // Current status of the request

    public LCRequest(int id, String requesterID, LocalDate requestDate, String status) {
        this.id = id;
        this.requesterID = requesterID;
        this.requestDate = requestDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequesterID() {
        return requesterID;
    }

    public void setRequesterID(String requesterID) {
        this.requesterID = requesterID;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

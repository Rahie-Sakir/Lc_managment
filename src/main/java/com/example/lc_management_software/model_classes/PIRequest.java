package com.example.lc_management_software.model_classes;

import java.io.Serializable;

public class PIRequest implements Serializable {
    private String ID, clientEmail, date, status;

    public PIRequest(String ID, String clientEmail, String date, String status) {
        this.ID = ID;
        this.clientEmail = clientEmail;
        this.date = date;
        this.status = status;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

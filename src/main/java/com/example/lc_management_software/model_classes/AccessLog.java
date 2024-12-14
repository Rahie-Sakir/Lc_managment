package com.example.lc_management_software.model_classes;

import java.io.Serializable;
import java.time.LocalDate;

public class AccessLog implements Serializable {
    private String useremail, log;
    LocalDate date;

    public AccessLog(String useremail, String log, LocalDate date) {
        this.useremail = useremail;
        this.log = log;
        this.date = date;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

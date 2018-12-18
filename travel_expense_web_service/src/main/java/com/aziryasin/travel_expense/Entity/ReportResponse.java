package com.aziryasin.travel_expense.Entity;

public class ReportResponse {
    private String status,description,path;

    public ReportResponse() {
    }

    public ReportResponse(String status, String description, String path) {
        this.status = status;
        this.description = description;
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

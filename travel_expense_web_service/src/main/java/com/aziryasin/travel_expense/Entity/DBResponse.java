package com.aziryasin.travel_expense.Entity;

public class DBResponse {
    private String status,description;
    private Object object;

    public DBResponse(String status, String description, Object object) {
        this.status = status;
        this.description = description;
        this.object = object;
    }

    public DBResponse() {
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

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}

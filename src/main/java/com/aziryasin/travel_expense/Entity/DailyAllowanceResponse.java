package com.aziryasin.travel_expense.Entity;

public class DailyAllowanceResponse {
    private String status,description;
    private float allowance;

    public DailyAllowanceResponse() {
    }

    public DailyAllowanceResponse(String status, String description, float allowance) {
        this.status = status;
        this.description = description;
        this.allowance = allowance;
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

    public float getAllowance() {
        return allowance;
    }

    public void setAllowance(float allowance) {
        this.allowance = allowance;
    }
}

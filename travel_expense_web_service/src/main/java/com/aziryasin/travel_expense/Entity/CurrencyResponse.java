package com.aziryasin.travel_expense.Entity;

public class CurrencyResponse {
    private String status,description;
    private float rate;

    public CurrencyResponse(String status, String description, float rate) {
        this.status = status;
        this.description = description;
        this.rate = rate;
    }

    public CurrencyResponse() {
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

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}

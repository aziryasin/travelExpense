package com.aziryasin.travel_expense.Entity;

import java.util.Date;
import java.util.UUID;

public class ExpenseReimbursement {
    String description,transactCurrency,id;
    Date transactDate;
    float transactAmount,currentRateLKR,currentRateSEK;

    public ExpenseReimbursement() {
        this.id= UUID.randomUUID().toString();
    }

    public ExpenseReimbursement(String description, String transactCurrency, Date transactDate, float transactAmount, float currentRateLKR, float currentRateSEK) {
        this.id= UUID.randomUUID().toString();
        this.description = description;
        this.transactCurrency = transactCurrency;
        this.transactDate = transactDate;
        this.transactAmount = transactAmount;
        this.currentRateLKR = currentRateLKR;
        this.currentRateSEK = currentRateSEK;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransactCurrency() {
        return transactCurrency;
    }

    public void setTransactCurrency(String transactCurrency) {
        this.transactCurrency = transactCurrency;
    }

    public Date getTransactDate() {
        return transactDate;
    }

    public void setTransactDate(Date transactDate) {
        this.transactDate = transactDate;
    }

    public float getTransactAmount() {
        return transactAmount;
    }

    public void setTransactAmount(float transactAmount) {
        this.transactAmount = transactAmount;
    }

    public float getCurrentRateLKR() {
        return currentRateLKR;
    }

    public void setCurrentRateLKR(float currentRateLKR) {
        this.currentRateLKR = currentRateLKR;
    }

    public float getCurrentRateSEK() {
        return currentRateSEK;
    }

    public void setCurrentRateSEK(float currentRateSEK) {
        this.currentRateSEK = currentRateSEK;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

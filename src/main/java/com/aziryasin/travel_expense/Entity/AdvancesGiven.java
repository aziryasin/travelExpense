package com.aziryasin.travel_expense.Entity;

import java.util.Date;
import java.util.UUID;

public class AdvancesGiven {
    String transactCurrency,id;
    Date transactDate;
    float transactAmount,currentRateLKR;

    public AdvancesGiven() {
        this.id= UUID.randomUUID().toString();
    }

    public AdvancesGiven(String transactCurrency, Date transactDate, float transactAmount, float currentRateLKR) {
        this.id= UUID.randomUUID().toString();
        this.transactCurrency = transactCurrency;
        this.transactDate = transactDate;
        this.transactAmount = transactAmount;
        this.currentRateLKR = currentRateLKR;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

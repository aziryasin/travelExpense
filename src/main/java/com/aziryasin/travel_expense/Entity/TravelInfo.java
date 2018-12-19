package com.aziryasin.travel_expense.Entity;

import java.util.UUID;

public class TravelInfo {
    String purpose,country,currencyCode,id;
    float days,dailyAllowance,currentRateLKR,currentRateSEK;

    public TravelInfo() {
        this.id= UUID.randomUUID().toString();
    }

    public TravelInfo(String purpose, String country, String currencyCode, float days, float dailyAllowance, float currentRateLKR, float currentRateSEK) {
        this.id= UUID.randomUUID().toString();
        this.purpose = purpose;
        this.country = country;
        this.currencyCode = currencyCode;
        this.days = days;
        this.dailyAllowance = dailyAllowance;
        this.currentRateLKR = currentRateLKR;
        this.currentRateSEK = currentRateSEK;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public float getDays() {
        return days;
    }

    public void setDays(float days) {
        this.days = days;
    }

    public float getDailyAllowance() {
        return dailyAllowance;
    }

    public void setDailyAllowance(float dailyAllowance) {
        this.dailyAllowance = dailyAllowance;
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

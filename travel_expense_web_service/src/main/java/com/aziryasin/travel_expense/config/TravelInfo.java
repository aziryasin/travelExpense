package com.aziryasin.travel_expense.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "purposeCell",
        "countryCell",
        "daysCell",
        "currencyCodeCell",
        "dailyAllowanceCell",
        "currentRateLKRCell",
        "currentRateSEKCell"
})
public class TravelInfo {

    @JsonProperty("purposeCell")
    private List<Integer> purposeCell = null;
    @JsonProperty("countryCell")
    private List<Integer> countryCell = null;
    @JsonProperty("daysCell")
    private List<Integer> daysCell = null;
    @JsonProperty("currencyCodeCell")
    private List<Integer> currencyCodeCell = null;
    @JsonProperty("dailyAllowanceCell")
    private List<Integer> dailyAllowanceCell = null;
    @JsonProperty("currentRateLKRCell")
    private List<Integer> currentRateLKRCell = null;
    @JsonProperty("currentRateSEKCell")
    private List<Integer> currentRateSEKCell = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("purposeCell")
    public List<Integer> getPurposeCell() {
        return purposeCell;
    }

    @JsonProperty("purposeCell")
    public void setPurposeCell(List<Integer> purposeCell) {
        this.purposeCell = purposeCell;
    }

    @JsonProperty("countryCell")
    public List<Integer> getCountryCell() {
        return countryCell;
    }

    @JsonProperty("countryCell")
    public void setCountryCell(List<Integer> countryCell) {
        this.countryCell = countryCell;
    }

    @JsonProperty("daysCell")
    public List<Integer> getDaysCell() {
        return daysCell;
    }

    @JsonProperty("daysCell")
    public void setDaysCell(List<Integer> daysCell) {
        this.daysCell = daysCell;
    }

    @JsonProperty("currencyCodeCell")
    public List<Integer> getCurrencyCodeCell() {
        return currencyCodeCell;
    }

    @JsonProperty("currencyCodeCell")
    public void setCurrencyCodeCell(List<Integer> currencyCodeCell) {
        this.currencyCodeCell = currencyCodeCell;
    }

    @JsonProperty("dailyAllowanceCell")
    public List<Integer> getDailyAllowanceCell() {
        return dailyAllowanceCell;
    }

    @JsonProperty("dailyAllowanceCell")
    public void setDailyAllowanceCell(List<Integer> dailyAllowanceCell) {
        this.dailyAllowanceCell = dailyAllowanceCell;
    }

    @JsonProperty("currentRateLKRCell")
    public List<Integer> getCurrentRateLKRCell() {
        return currentRateLKRCell;
    }

    @JsonProperty("currentRateLKRCell")
    public void setCurrentRateLKRCell(List<Integer> currentRateLKRCell) {
        this.currentRateLKRCell = currentRateLKRCell;
    }

    @JsonProperty("currentRateSEKCell")
    public List<Integer> getCurrentRateSEKCell() {
        return currentRateSEKCell;
    }

    @JsonProperty("currentRateSEKCell")
    public void setCurrentRateSEKCell(List<Integer> currentRateSEKCell) {
        this.currentRateSEKCell = currentRateSEKCell;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
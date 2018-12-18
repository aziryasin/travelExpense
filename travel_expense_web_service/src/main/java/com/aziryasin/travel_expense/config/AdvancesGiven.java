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
        "transactDateCell",
        "transactCurrencyCell",
        "transactAmountCell",
        "currentRateLKRCell"
})
public class AdvancesGiven {

    @JsonProperty("transactDateCell")
    private List<Integer> transactDateCell = null;
    @JsonProperty("transactCurrencyCell")
    private List<Integer> transactCurrencyCell = null;
    @JsonProperty("transactAmountCell")
    private List<Integer> transactAmountCell = null;
    @JsonProperty("currentRateLKRCell")
    private List<Integer> currentRateLKRCell = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("transactDateCell")
    public List<Integer> getTransactDateCell() {
        return transactDateCell;
    }

    @JsonProperty("transactDateCell")
    public void setTransactDateCell(List<Integer> transactDateCell) {
        this.transactDateCell = transactDateCell;
    }

    @JsonProperty("transactCurrencyCell")
    public List<Integer> getTransactCurrencyCell() {
        return transactCurrencyCell;
    }

    @JsonProperty("transactCurrencyCell")
    public void setTransactCurrencyCell(List<Integer> transactCurrencyCell) {
        this.transactCurrencyCell = transactCurrencyCell;
    }

    @JsonProperty("transactAmountCell")
    public List<Integer> getTransactAmountCell() {
        return transactAmountCell;
    }

    @JsonProperty("transactAmountCell")
    public void setTransactAmountCell(List<Integer> transactAmountCell) {
        this.transactAmountCell = transactAmountCell;
    }

    @JsonProperty("currentRateLKRCell")
    public List<Integer> getCurrentRateLKRCell() {
        return currentRateLKRCell;
    }

    @JsonProperty("currentRateLKRCell")
    public void setCurrentRateLKRCell(List<Integer> currentRateLKRCell) {
        this.currentRateLKRCell = currentRateLKRCell;
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
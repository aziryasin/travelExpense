package com.aziryasin.travel_expense.Entity;

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
        "askRates",
        "bidRates",
        "pairs"
})
public class RateData {

    @JsonProperty("askRates")
    private List<String> askRates = null;
    @JsonProperty("bidRates")
    private List<String> bidRates = null;
    @JsonProperty("pairs")
    private List<String> pairs = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("askRates")
    public List<String> getAskRates() {
        return askRates;
    }

    @JsonProperty("askRates")
    public void setAskRates(List<String> askRates) {
        this.askRates = askRates;
    }

    @JsonProperty("bidRates")
    public List<String> getBidRates() {
        return bidRates;
    }

    @JsonProperty("bidRates")
    public void setBidRates(List<String> bidRates) {
        this.bidRates = bidRates;
    }

    @JsonProperty("pairs")
    public List<String> getPairs() {
        return pairs;
    }

    @JsonProperty("pairs")
    public void setPairs(List<String> pairs) {
        this.pairs = pairs;
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
package com.aziryasin.travel_expense.Entity;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "bid",
        "max_ask",
        "max_bid",
        "min_bid",
        "min_ask",
        "ask"
})
public class BidAskData {

    @JsonProperty("bid")
    private String bid;
    @JsonProperty("max_ask")
    private String maxAsk;
    @JsonProperty("max_bid")
    private String maxBid;
    @JsonProperty("min_bid")
    private String minBid;
    @JsonProperty("min_ask")
    private String minAsk;
    @JsonProperty("ask")
    private String ask;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bid")
    public String getBid() {
        return bid;
    }

    @JsonProperty("bid")
    public void setBid(String bid) {
        this.bid = bid;
    }

    @JsonProperty("max_ask")
    public String getMaxAsk() {
        return maxAsk;
    }

    @JsonProperty("max_ask")
    public void setMaxAsk(String maxAsk) {
        this.maxAsk = maxAsk;
    }

    @JsonProperty("max_bid")
    public String getMaxBid() {
        return maxBid;
    }

    @JsonProperty("max_bid")
    public void setMaxBid(String maxBid) {
        this.maxBid = maxBid;
    }

    @JsonProperty("min_bid")
    public String getMinBid() {
        return minBid;
    }

    @JsonProperty("min_bid")
    public void setMinBid(String minBid) {
        this.minBid = minBid;
    }

    @JsonProperty("min_ask")
    public String getMinAsk() {
        return minAsk;
    }

    @JsonProperty("min_ask")
    public void setMinAsk(String minAsk) {
        this.minAsk = minAsk;
    }

    @JsonProperty("ask")
    public String getAsk() {
        return ask;
    }

    @JsonProperty("ask")
    public void setAsk(String ask) {
        this.ask = ask;
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
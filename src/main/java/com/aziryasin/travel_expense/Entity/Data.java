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
        "bid_ask_data",
        "rate_data",
        "chart_data"
})
public class Data {

    @JsonProperty("bid_ask_data")
    private BidAskData bidAskData;
    @JsonProperty("rate_data")
    private RateData rateData;
    @JsonProperty("chart_data")
    private List<List<Float>> chartData = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bid_ask_data")
    public BidAskData getBidAskData() {
        return bidAskData;
    }

    @JsonProperty("bid_ask_data")
    public void setBidAskData(BidAskData bidAskData) {
        this.bidAskData = bidAskData;
    }

    @JsonProperty("rate_data")
    public RateData getRateData() {
        return rateData;
    }

    @JsonProperty("rate_data")
    public void setRateData(RateData rateData) {
        this.rateData = rateData;
    }

    @JsonProperty("chart_data")
    public List<List<Float>> getChartData() {
        return chartData;
    }

    @JsonProperty("chart_data")
    public void setChartData(List<List<Float>> chartData) {
        this.chartData = chartData;
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
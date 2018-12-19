package com.aziryasin.travel_expense.config;

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
        "generalInfo",
        "tripInfo",
        "travelInfo",
        "advancesGiven",
        "travelCosts",
        "reimbursements"
})
public class CellsConfig {

    @JsonProperty("generalInfo")
    private GeneralInfo generalInfo;
    @JsonProperty("tripInfo")
    private TripInfo tripInfo;
    @JsonProperty("travelInfo")
    private TravelInfo travelInfo;
    @JsonProperty("advancesGiven")
    private AdvancesGiven advancesGiven;
    @JsonProperty("travelCosts")
    private TravelCosts travelCosts;
    @JsonProperty("reimbursements")
    private Reimbursements reimbursements;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("generalInfo")
    public GeneralInfo getGeneralInfo() {
        return generalInfo;
    }

    @JsonProperty("generalInfo")
    public void setGeneralInfo(GeneralInfo generalInfo) {
        this.generalInfo = generalInfo;
    }

    @JsonProperty("tripInfo")
    public TripInfo getTripInfo() {
        return tripInfo;
    }

    @JsonProperty("tripInfo")
    public void setTripInfo(TripInfo tripInfo) {
        this.tripInfo = tripInfo;
    }

    @JsonProperty("travelInfo")
    public TravelInfo getTravelInfo() {
        return travelInfo;
    }

    @JsonProperty("travelInfo")
    public void setTravelInfo(TravelInfo travelInfo) {
        this.travelInfo = travelInfo;
    }

    @JsonProperty("advancesGiven")
    public AdvancesGiven getAdvancesGiven() {
        return advancesGiven;
    }

    @JsonProperty("advancesGiven")
    public void setAdvancesGiven(AdvancesGiven advancesGiven) {
        this.advancesGiven = advancesGiven;
    }

    @JsonProperty("travelCosts")
    public TravelCosts getTravelCosts() {
        return travelCosts;
    }

    @JsonProperty("travelCosts")
    public void setTravelCosts(TravelCosts travelCosts) {
        this.travelCosts = travelCosts;
    }

    @JsonProperty("reimbursements")
    public Reimbursements getReimbursements() {
        return reimbursements;
    }

    @JsonProperty("reimbursements")
    public void setReimbursements(Reimbursements reimbursements) {
        this.reimbursements = reimbursements;
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
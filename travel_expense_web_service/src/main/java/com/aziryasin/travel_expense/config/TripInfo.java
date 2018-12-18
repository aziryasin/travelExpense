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
        "projectNamesCell",
        "projectIdsCell",
        "costCenterCell",
        "startDateCell",
        "endDateCell"
})
public class TripInfo {

    @JsonProperty("projectNamesCell")
    private List<Integer> projectNamesCell = null;
    @JsonProperty("projectIdsCell")
    private List<Integer> projectIdsCell = null;
    @JsonProperty("costCenterCell")
    private List<Integer> costCenterCell = null;
    @JsonProperty("startDateCell")
    private List<Integer> startDateCell = null;
    @JsonProperty("endDateCell")
    private List<Integer> endDateCell = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("projectNamesCell")
    public List<Integer> getProjectNamesCell() {
        return projectNamesCell;
    }

    @JsonProperty("projectNamesCell")
    public void setProjectNamesCell(List<Integer> projectNamesCell) {
        this.projectNamesCell = projectNamesCell;
    }

    @JsonProperty("projectIdsCell")
    public List<Integer> getProjectIdsCell() {
        return projectIdsCell;
    }

    @JsonProperty("projectIdsCell")
    public void setProjectIdsCell(List<Integer> projectIdsCell) {
        this.projectIdsCell = projectIdsCell;
    }

    @JsonProperty("costCenterCell")
    public List<Integer> getCostCenterCell() {
        return costCenterCell;
    }

    @JsonProperty("costCenterCell")
    public void setCostCenterCell(List<Integer> costCenterCell) {
        this.costCenterCell = costCenterCell;
    }

    @JsonProperty("startDateCell")
    public List<Integer> getStartDateCell() {
        return startDateCell;
    }

    @JsonProperty("startDateCell")
    public void setStartDateCell(List<Integer> startDateCell) {
        this.startDateCell = startDateCell;
    }

    @JsonProperty("endDateCell")
    public List<Integer> getEndDateCell() {
        return endDateCell;
    }

    @JsonProperty("endDateCell")
    public void setEndDateCell(List<Integer> endDateCell) {
        this.endDateCell = endDateCell;
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
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
        "nameCell",
        "empNoCell",
        "epfNoCell",
        "orgCell"
})
public class GeneralInfo {

    @JsonProperty("nameCell")
    private List<Integer> nameCell = null;
    @JsonProperty("empNoCell")
    private List<Integer> empNoCell = null;
    @JsonProperty("epfNoCell")
    private List<Integer> epfNoCell = null;
    @JsonProperty("orgCell")
    private List<Integer> orgCell = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("nameCell")
    public List<Integer> getNameCell() {
        return nameCell;
    }

    @JsonProperty("nameCell")
    public void setNameCell(List<Integer> nameCell) {
        this.nameCell = nameCell;
    }

    @JsonProperty("empNoCell")
    public List<Integer> getEmpNoCell() {
        return empNoCell;
    }

    @JsonProperty("empNoCell")
    public void setEmpNoCell(List<Integer> empNoCell) {
        this.empNoCell = empNoCell;
    }

    @JsonProperty("epfNoCell")
    public List<Integer> getEpfNoCell() {
        return epfNoCell;
    }

    @JsonProperty("epfNoCell")
    public void setEpfNoCell(List<Integer> epfNoCell) {
        this.epfNoCell = epfNoCell;
    }

    @JsonProperty("orgCell")
    public List<Integer> getOrgCell() {
        return orgCell;
    }

    @JsonProperty("orgCell")
    public void setOrgCell(List<Integer> orgCell) {
        this.orgCell = orgCell;
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
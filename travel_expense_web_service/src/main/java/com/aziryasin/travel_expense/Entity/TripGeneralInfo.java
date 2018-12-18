package com.aziryasin.travel_expense.Entity;

import java.util.Date;
import java.util.UUID;

public class TripGeneralInfo {
    String projectNames,projectIDs,costCenter,id;
    Date startDate,endDate;


    public TripGeneralInfo() {
        this.id= UUID.randomUUID().toString();
    }

    public TripGeneralInfo(String projectNames, String projectIDs, String costCenter, Date startDate, Date endDate) {
        this.id= UUID.randomUUID().toString();
        this.projectNames = projectNames;
        this.projectIDs = projectIDs;
        this.costCenter = costCenter;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getProjectNames() {
        return projectNames;
    }

    public void setProjectNames(String projectNames) {
        this.projectNames = projectNames;
    }

    public String getProjectIDs() {
        return projectIDs;
    }

    public void setProjectIDs(String projectIDs) {
        this.projectIDs = projectIDs;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

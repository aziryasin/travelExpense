package com.aziryasin.travel_expense.Model;

import com.aziryasin.travel_expense.Entity.*;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Trip {
    @Id
    private String _id;
    GeneralInfo generalInfo;
    TripGeneralInfo tripGeneralInfo;
    List<TravelInfo> travelInfoList;
    List<AdvancesGiven> advancesGivenList;
    List<TravelCosts> travelCostsList;
    List<ExpenseReimbursement> expenseReimbursementList;

    public Trip() {
    }

    public Trip(String _id, GeneralInfo generalInfo, TripGeneralInfo tripGeneralInfo, List<TravelInfo> travelInfoList, List<AdvancesGiven> advancesGivenList, List<TravelCosts> travelCostsList, List<ExpenseReimbursement> expenseReimbursementList) {
        this._id = _id;
        this.generalInfo = generalInfo;
        this.tripGeneralInfo = tripGeneralInfo;
        this.travelInfoList = travelInfoList;
        this.advancesGivenList = advancesGivenList;
        this.travelCostsList = travelCostsList;
        this.expenseReimbursementList = expenseReimbursementList;
    }

    public Trip(String _id, GeneralInfo generalInfo, TripGeneralInfo tripGeneralInfo, List<TravelInfo> travelInfoList, List<AdvancesGiven> advancesGivenList, List<TravelCosts> travelCostsList) {
        this._id = _id;
        this.generalInfo = generalInfo;
        this.tripGeneralInfo = tripGeneralInfo;
        this.travelInfoList = travelInfoList;
        this.advancesGivenList = advancesGivenList;
        this.travelCostsList = travelCostsList;
    }

    public Trip(GeneralInfo generalInfo, TripGeneralInfo tripGeneralInfo, List<TravelInfo> travelInfoList, List<AdvancesGiven> advancesGivenList, List<TravelCosts> travelCostsList) {
        this.generalInfo = generalInfo;
        this.tripGeneralInfo = tripGeneralInfo;
        this.travelInfoList = travelInfoList;
        this.advancesGivenList = advancesGivenList;
        this.travelCostsList = travelCostsList;
    }

    public Trip(GeneralInfo generalInfo, TripGeneralInfo tripGeneralInfo, List<TravelInfo> travelInfoList, List<AdvancesGiven> advancesGivenList, List<TravelCosts> travelCostsList, List<ExpenseReimbursement> expenseReimbursementList) {
        this.generalInfo = generalInfo;
        this.tripGeneralInfo = tripGeneralInfo;
        this.travelInfoList = travelInfoList;
        this.advancesGivenList = advancesGivenList;
        this.travelCostsList = travelCostsList;
        this.expenseReimbursementList = expenseReimbursementList;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public GeneralInfo getGeneralInfo() {
        return generalInfo;
    }

    public void setGeneralInfo(GeneralInfo generalInfo) {
        this.generalInfo = generalInfo;
    }

    public TripGeneralInfo getTripGeneralInfo() {
        return tripGeneralInfo;
    }

    public void setTripGeneralInfo(TripGeneralInfo tripGeneralInfo) {
        this.tripGeneralInfo = tripGeneralInfo;
    }

    public List<TravelInfo> getTravelInfoList() {
        return travelInfoList;
    }

    public void setTravelInfoList(List<TravelInfo> travelInfoList) {
        this.travelInfoList = travelInfoList;
    }

    public List<AdvancesGiven> getAdvancesGivenList() {
        return advancesGivenList;
    }

    public void setAdvancesGivenList(List<AdvancesGiven> advancesGivenList) {
        this.advancesGivenList = advancesGivenList;
    }

    public List<TravelCosts> getTravelCostsList() {
        return travelCostsList;
    }

    public void setTravelCostsList(List<TravelCosts> travelCostsList) {
        this.travelCostsList = travelCostsList;
    }

    public List<ExpenseReimbursement> getExpenseReimbursementList() {
        return expenseReimbursementList;
    }

    public void setExpenseReimbursementList(List<ExpenseReimbursement> expenseReimbursementList) {
        this.expenseReimbursementList = expenseReimbursementList;
    }
}

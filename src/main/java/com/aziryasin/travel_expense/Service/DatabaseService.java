package com.aziryasin.travel_expense.Service;

import com.aziryasin.travel_expense.DAO.DatabaseDao;
import com.aziryasin.travel_expense.Entity.*;
import com.aziryasin.travel_expense.Model.Employee;
import com.aziryasin.travel_expense.Model.Trip;
import com.aziryasin.travel_expense.Repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DatabaseService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    DatabaseDao databaseDao;


    public DBResponse startTrip(){
        return databaseDao.startTrip();
    }

    public DBResponse saveTrip(Trip trip){
        return databaseDao.saveTrip(trip);
    }


    public DBResponse updateReimbursement(Trip trip){
        return databaseDao.updateReimbursement(trip);
    }



    public DBResponse getAllTrips(){
        return databaseDao.getAllTrips();
    }


    public DBResponse getTrip(String id){
        return databaseDao.getTrip(id);
    }


    public DBResponse getTripByEmp(int empNo){
        return databaseDao.getTripByEmp(empNo);
    }


    public DBResponse deleteTrip(String id){
        return databaseDao.deleteTrip(id);
    }

    public DBResponse duplicateTrip(String id){
        return databaseDao.duplicateTrip(id);
    }

    public DBResponse getTravelInfoList(String id){
        return databaseDao.getTravelInfoList(id);
    }


    public DBResponse getTravelCostsList(String id){
        return databaseDao.getTravelCostsList(id);
    }


    public DBResponse getAdvancesGiven(String id){
        return databaseDao.getAdvancesGiven(id);
    }

    public DBResponse getExpenseReimbursements(String id){
        return databaseDao.getExpenseReimbursements(id);
    }

    public DBResponse deleteTravelInfo(String id,String recordId){
        return databaseDao.deleteTravelInfo(id,recordId);
    }

    public DBResponse addTravelInfo(String id, TravelInfo travelInfo){
        return  databaseDao.addTravelInfo(id,travelInfo);
    }

    public DBResponse editTravelInfo(String id,String recordId,TravelInfo travelInfo){
        return databaseDao.editTravelInfo(id, recordId, travelInfo);
    }

    public DBResponse addAdvancesGiven(String id, AdvancesGiven advancesGiven){
        return databaseDao.addAdvancesGiven(id,advancesGiven);
    }

    public DBResponse deleteAdvancesGiven(String id, String recordId){
        return databaseDao.deleteAdvancesGiven(id,recordId);
    }

    public DBResponse editAdvancesGiven(String id,String recordId,AdvancesGiven advancesGiven){
        return databaseDao.editAdvancesGiven(id, recordId, advancesGiven);
    }

    public DBResponse addTravelCost(String id, TravelCosts travelCosts){
        return databaseDao.addTravelCost(id, travelCosts);
    }

    public DBResponse deleteTravelCost(String id,String recordId){
        return databaseDao.deleteTravelCost(id, recordId);
    }

    public DBResponse editTravelCost(String id,String recordId,TravelCosts travelCosts){
        return databaseDao.editTravelCost(id, recordId, travelCosts);
    }

    public DBResponse addTravelExpense(String id,ExpenseReimbursement expenseReimbursement){
        return databaseDao.addTravelExpense(id, expenseReimbursement);
    }

    public DBResponse deleteTravelExpense(String id,String expenseId){
        return databaseDao.deleteTravelExpense(id, expenseId);
    }

    public DBResponse editTravelExpense(String id,String recordId,ExpenseReimbursement expenseReimbursement){
        return databaseDao.editTravelExpense(id, recordId, expenseReimbursement);
    }

    public DBResponse editProfile(String id,GeneralInfo generalInfo){
        return databaseDao.editProfile(id, generalInfo);
    }

    public DBResponse getEmployeeInfo(int id){
        return databaseDao.getEmployeeInfo(id);
    }

    public DBResponse editGeneralInfo(String id,TripGeneralInfo tripGeneralInfo,int empNo){
        return databaseDao.editGeneralInfo(id, tripGeneralInfo,empNo);
    }

    public DBResponse login(String username,String password){
        return databaseDao.login(username, password);
    }

    public DBResponse register(Employee employee){
        return databaseDao.register(employee);
    }
}

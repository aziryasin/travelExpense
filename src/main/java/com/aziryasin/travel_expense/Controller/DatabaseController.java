package com.aziryasin.travel_expense.Controller;

import com.aziryasin.travel_expense.Entity.*;
import com.aziryasin.travel_expense.Model.Employee;
import com.aziryasin.travel_expense.Model.Trip;
import com.aziryasin.travel_expense.Repository.ReportRepository;
import com.aziryasin.travel_expense.Service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/db")
public class DatabaseController {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/startTrip" , method = RequestMethod.GET)
    public DBResponse saveTrip(){
        return databaseService.startTrip();
    }

    @RequestMapping(value = "/saveTrip" , method = RequestMethod.POST)
    public DBResponse saveTrip(@RequestBody Trip trip){
        return databaseService.saveTrip(trip);
    }

    @RequestMapping(value = "/updateReimbursement" , method = RequestMethod.POST)
    public DBResponse updateReimbursement(@RequestBody Trip trip){
        return databaseService.updateReimbursement(trip);
    }


    @RequestMapping(value = "/getAllTrips" , method = RequestMethod.GET)
    public DBResponse getAllTrips(){
        return databaseService.getAllTrips();
    }

    @RequestMapping(value = "/getTrip/{id}" , method = RequestMethod.GET)
    public DBResponse getTrip(@PathVariable String id){
        return databaseService.getTrip(id);
    }

    @RequestMapping(value = "/getTripByEmp/{empNo}" , method = RequestMethod.GET)
    public DBResponse getTripByEmp(@PathVariable int empNo){
        return databaseService.getTripByEmp(empNo);
    }

    @RequestMapping(value = "/deleteTrip/{id}" , method = RequestMethod.DELETE)
    public DBResponse deleteTrip(@PathVariable String id){
        return databaseService.deleteTrip(id);
    }

    @RequestMapping(value = "/duplicateTrip/{id}", method = RequestMethod.GET)
    public DBResponse duplicateTrip(@PathVariable String id){
        return databaseService.duplicateTrip(id);
    }

    @RequestMapping(value = "/getTravelInfo/{id}" , method = RequestMethod.GET)
    public DBResponse getTravelInfoList(@PathVariable String id){
        return databaseService.getTravelInfoList(id);
    }

    @RequestMapping(value = "/getTravelCosts/{id}" , method = RequestMethod.GET)
    public DBResponse getTravelCostsList(@PathVariable String id){
        return databaseService.getTravelCostsList(id);
    }

    @RequestMapping(value = "/getAdvancesGiven/{id}" , method = RequestMethod.GET)
    public DBResponse getAdvancesGiven(@PathVariable String id){
        return databaseService.getAdvancesGiven(id);
    }

    @RequestMapping(value = "/getExpenseReimbursements/{id}" , method = RequestMethod.GET)
    public DBResponse getExpenseReimbursements(@PathVariable String id){
        return databaseService.getExpenseReimbursements(id);
    }

    @RequestMapping(value = "/deleteTravelInfo" , method = RequestMethod.DELETE)
    public DBResponse deleteTravelInfo(@RequestParam String id,@RequestParam String recordId){
        return databaseService.deleteTravelInfo(id,recordId);
    }

    @RequestMapping(value = "/addTravelInfo" , method = RequestMethod.POST)
    public DBResponse addTravelInfo(@RequestParam String id, @RequestBody TravelInfo travelInfo){
        return databaseService.addTravelInfo(id,travelInfo);
    }

    @RequestMapping(value = "/editTravelInfo" , method = RequestMethod.POST)
    public DBResponse editTravelInfo(@RequestParam String id,@RequestParam String recordId,@RequestBody TravelInfo travelInfo){
        return databaseService.editTravelInfo(id, recordId, travelInfo);
    }

    @RequestMapping(value = "/addAdvancesGiven" , method = RequestMethod.POST)
    public DBResponse addAdvancesGiven(@RequestParam  String id,@RequestBody AdvancesGiven advancesGiven){
        return databaseService.addAdvancesGiven(id,advancesGiven);
    }

    @RequestMapping(value = "/deleteAdvancesGiven" , method = RequestMethod.DELETE)
    public DBResponse deleteAdvancesGiven(@RequestParam String id,@RequestParam String recordId){
        return databaseService.deleteAdvancesGiven(id, recordId);
    }

    @RequestMapping(value = "/editAdvancesGiven" , method = RequestMethod.POST)
    public DBResponse editAdvancesGiven(@RequestParam String id,@RequestParam String recordId,@RequestBody AdvancesGiven advancesGiven){
        return databaseService.editAdvancesGiven(id, recordId, advancesGiven);
    }

    @RequestMapping(value = "/addTravelCost" , method = RequestMethod.POST)
    public DBResponse addTravelCost(@RequestParam String id,@RequestBody TravelCosts travelCosts){
        return databaseService.addTravelCost(id, travelCosts);
    }

    @RequestMapping(value = "/deleteTravelCost" , method = RequestMethod.DELETE)
    public DBResponse deleteTravelCost(@RequestParam String id,@RequestParam String recordId){
        return databaseService.deleteTravelCost(id, recordId);
    }

    @RequestMapping(value = "/editTravelCost" , method = RequestMethod.POST)
    public DBResponse editAdvancesGiven(@RequestParam String id,@RequestParam String recordId,@RequestBody TravelCosts travelCosts){
        return databaseService.editTravelCost(id, recordId, travelCosts);
    }

    @RequestMapping(value = "/addTravelExpense" , method = RequestMethod.POST)
    public DBResponse addTravelExpense(@RequestParam String id, @RequestBody ExpenseReimbursement expenseReimbursement){
        return databaseService.addTravelExpense(id, expenseReimbursement);
    }

    @RequestMapping(value = "/deleteTravelExpense" , method = RequestMethod.DELETE)
    public DBResponse deleteTravelExpense(@RequestParam String id,@RequestParam String expenseId){
        return databaseService.deleteTravelExpense(id, expenseId);
    }


    @RequestMapping(value = "/editTravelExpense" , method = RequestMethod.POST)
    public DBResponse editTravelExpense(@RequestParam String id,@RequestParam String recordId,@RequestBody ExpenseReimbursement expenseReimbursement){
        return databaseService.editTravelExpense(id, recordId, expenseReimbursement);
    }

    @RequestMapping(value = "/editProfile" , method = RequestMethod.POST)
    public DBResponse editProfile(@RequestParam String id,@RequestBody GeneralInfo generalInfo){
        return databaseService.editProfile(id, generalInfo);
    }

    @RequestMapping(value = "/getEmployeeInfo/{id}" , method = RequestMethod.GET)
    public DBResponse getEmployeeInfo(@PathVariable int id){
        return databaseService.getEmployeeInfo(id);
    }

    @RequestMapping(value = "/editGeneralInfo" , method = RequestMethod.POST)
    public DBResponse editGeneralInfo(@RequestParam String id,@RequestParam int empNo, @RequestBody TripGeneralInfo tripGeneralInfo){
        return databaseService.editGeneralInfo(id, tripGeneralInfo,empNo);
    }

    @RequestMapping(value = "/login/{username}" , method = RequestMethod.POST)
    public DBResponse login(@PathVariable String username,@RequestBody String password){
        return databaseService.login(username, password);
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public DBResponse register(@RequestBody Employee employee){
        return databaseService.register(employee);
    }
}

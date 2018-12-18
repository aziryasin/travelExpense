package com.aziryasin.travel_expense.DAO;

import com.aziryasin.travel_expense.Entity.*;
import com.aziryasin.travel_expense.Model.Employee;
import com.aziryasin.travel_expense.Model.Trip;
import com.aziryasin.travel_expense.Repository.EmployeeRepository;
import com.aziryasin.travel_expense.Repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DatabaseDao {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    public DBResponse startTrip(){
        Trip trip=new Trip();
        reportRepository.save(trip);
        String id=trip.get_id();
        return (new DBResponse("success","Trip Created",id));
    }

    public DBResponse saveTrip(Trip trip){
        System.out.println("Mongo saving...");
        reportRepository.save(trip);
        System.out.println(trip.get_id());
        return new DBResponse("Success","Object Added",trip.get_id());
    }


    public DBResponse updateReimbursement(Trip trip){
        if(trip.get_id()==null){
            System.out.println("Mongo saving...");
            reportRepository.save(trip);
            System.out.println(trip.get_id());
            return new DBResponse("Success","Object Added",trip.get_id());
        }else{
            try {
                Trip trip1 = reportRepository.findTripBy_id(trip.get_id());
                trip1.setExpenseReimbursementList(trip.getExpenseReimbursementList());
                reportRepository.save(trip1);
                System.out.println(trip1.get_id());
                return new DBResponse("Success", "Object Added", trip1.get_id());
            }
            catch (NullPointerException e){
                System.out.println(e);
                System.out.println("Mongo saving...");
                reportRepository.save(trip);
                System.out.println(trip.get_id());
                return new DBResponse("Success","Object Added",trip.get_id());
            }
        }

    }



    public DBResponse getAllTrips(){
        List<Trip> tripList=reportRepository.findAll();
        return new DBResponse("Success","Trip object is returned",tripList);
    }


    public DBResponse getTrip(String id){
        Trip trip=reportRepository.findTripBy_id(id);
        return new DBResponse("Success","Trip object is returned",trip);
    }


    public DBResponse getTripByEmp(int empNo){
        List<Trip> trips=reportRepository.findTripByGeneralInfo_EmpNo(empNo);
        return new DBResponse("Success","Trip object is returned",trips);
    }


    public DBResponse deleteTrip(String id){
        Trip trip=reportRepository.findTripBy_id(id);
        reportRepository.deleteTripBy_id(id);
        List<Trip> trips=reportRepository.findTripByGeneralInfo_EmpNo(trip.getGeneralInfo().getEmpNo());
        return new DBResponse("Success","Trip object is deleted",trips);
    }

    public DBResponse duplicateTrip(String id){
        Trip trip=reportRepository.findTripBy_id(id);
        Trip dupTrip=new Trip(trip.getGeneralInfo(),trip.getTripGeneralInfo(),trip.getTravelInfoList(),trip.getAdvancesGivenList(),trip.getTravelCostsList(),trip.getExpenseReimbursementList());
        reportRepository.save(dupTrip);
        List<Trip> trips=reportRepository.findTripByGeneralInfo_EmpNo(trip.getGeneralInfo().getEmpNo());
        return new DBResponse("Success","Trip object is deleted",trips);
    }


    public DBResponse getTravelInfoList(String id){
        Trip trip=reportRepository.findTripBy_id(id);
        return new DBResponse("Success","Travel info list",trip.getTravelInfoList());
    }


    public DBResponse getTravelCostsList(String id){
        Trip trip=reportRepository.findTripBy_id(id);
        return new DBResponse("Success","Travel Costs list",trip.getTravelCostsList());
    }


    public DBResponse getAdvancesGiven(String id){
        Trip trip=reportRepository.findTripBy_id(id);
        return new DBResponse("Success","Advances Given list",trip.getAdvancesGivenList());
    }

    public DBResponse getExpenseReimbursements(String id){
        Trip trip=reportRepository.findTripBy_id(id);
        return new DBResponse("Success","Expense Reimbursements list",trip.getExpenseReimbursementList());
    }

    public DBResponse deleteTravelInfo(String id,String recordId){
        Trip trip=reportRepository.findTripBy_id(id);
        List<TravelInfo> travelInfoList=trip.getTravelInfoList();
        travelInfoList.removeIf(travelInfo -> travelInfo.getId().equals(recordId));
        trip.setTravelInfoList(travelInfoList);
        saveTrip(trip);
        return new DBResponse("Success","Changes are made to the list",travelInfoList);
    }

    public DBResponse addTravelInfo(String id,TravelInfo travelInfo){
        Trip trip=reportRepository.findTripBy_id(id);
        List<TravelInfo> travelInfoList;
        try{
            travelInfoList=trip.getTravelInfoList();
            travelInfoList.add(travelInfo);
            trip.setTravelInfoList(travelInfoList);
        }catch (Exception e){
            e.printStackTrace();
            travelInfoList=new ArrayList<>();
            travelInfoList.add(travelInfo);
            trip.setTravelInfoList(travelInfoList);
        }

        saveTrip(trip);
        return new DBResponse("Success","Changes are made to the list",travelInfoList);
    }

    public DBResponse editTravelInfo(String id,String recordId,TravelInfo travelInfo){
        Trip trip=reportRepository.findTripBy_id(id);
        List<TravelInfo> travelInfoList=trip.getTravelInfoList();
        for(int i=0;i<travelInfoList.size();i++){
            if(travelInfoList.get(i).getId().equals(recordId)){
                travelInfoList.set(i,travelInfo);
            }
        }
        trip.setTravelInfoList(travelInfoList);
        saveTrip(trip);
        return new DBResponse("Success","Changes are made to the list",travelInfoList);
    }

    public DBResponse addAdvancesGiven(String id, AdvancesGiven advancesGiven){
        Trip trip=reportRepository.findTripBy_id(id);
        List<AdvancesGiven> advancesGivenList;

        try {
            advancesGivenList=trip.getAdvancesGivenList();
            advancesGivenList.add(advancesGiven);
        }catch (Exception e){
            e.printStackTrace();
            advancesGivenList=new ArrayList<>();
            advancesGivenList.add(advancesGiven);
        }

        trip.setAdvancesGivenList(advancesGivenList);
        saveTrip(trip);
        return new DBResponse("Success","Changes are made to the list",advancesGivenList);
    }

    public DBResponse deleteAdvancesGiven(String id,String recordId){
        Trip trip=reportRepository.findTripBy_id(id);
        List<AdvancesGiven> advancesGivenList=trip.getAdvancesGivenList();
        advancesGivenList.removeIf(advancesGiven -> advancesGiven.getId().equals(recordId));
        trip.setAdvancesGivenList(advancesGivenList);
        saveTrip(trip);
        return new DBResponse("Success","Changes are made to the list",advancesGivenList);
    }

    public DBResponse editAdvancesGiven(String id,String recordId,AdvancesGiven advancesGiven){
        Trip trip=reportRepository.findTripBy_id(id);
        List<AdvancesGiven> advancesGivenList=trip.getAdvancesGivenList();
        for(int i=0;i<advancesGivenList.size();i++){
            if(advancesGivenList.get(i).getId().equals(recordId)){
                advancesGivenList.set(i,advancesGiven);
            }
        }
        trip.setAdvancesGivenList(advancesGivenList);
        saveTrip(trip);
        return new DBResponse("Success","Changes are made to the list",advancesGivenList);
    }

    public DBResponse addTravelCost(String id, TravelCosts travelCosts){
        Trip trip=reportRepository.findTripBy_id(id);
        List<TravelCosts> travelCostsList;
        try{
            travelCostsList=trip.getTravelCostsList();
            travelCostsList.add(travelCosts);
        }catch (Exception e){
            travelCostsList=new ArrayList<>();
            travelCostsList.add(travelCosts);
        }

        trip.setTravelCostsList(travelCostsList);
        saveTrip(trip);
        return new DBResponse("Success","Changes are made to the list",travelCostsList);
    }

    public DBResponse deleteTravelCost(String id,String recordId){
        Trip trip=reportRepository.findTripBy_id(id);
        List<TravelCosts> travelCostsList=trip.getTravelCostsList();
        travelCostsList.removeIf(travelCosts -> travelCosts.getId().equals(recordId));
        trip.setTravelCostsList(travelCostsList);
        saveTrip(trip);
        return new DBResponse("Success","Changes are made to the list",travelCostsList);
    }

    public DBResponse editTravelCost(String id,String recordId,TravelCosts travelCosts){
        Trip trip=reportRepository.findTripBy_id(id);
        List<TravelCosts> travelCostsList=trip.getTravelCostsList();
        for(int i=0;i<travelCostsList.size();i++){
            if(travelCostsList.get(i).getId().equals(recordId)){
                travelCostsList.set(i,travelCosts);
            }
        }
        trip.setTravelCostsList(travelCostsList);
        saveTrip(trip);
        return new DBResponse("Success","Changes are made to the list",travelCostsList);
    }

    public DBResponse addTravelExpense(String id, ExpenseReimbursement expenseReimbursement){
        Trip trip=reportRepository.findTripBy_id(id);
        List<ExpenseReimbursement> expenseReimbursements=new ArrayList<>();
        if(trip.getExpenseReimbursementList()!=null){
            expenseReimbursements=trip.getExpenseReimbursementList();
        }
        expenseReimbursements.add(expenseReimbursement);
        trip.setExpenseReimbursementList(expenseReimbursements);
        saveTrip(trip);
        return new DBResponse("Success","Changes are made to the list",expenseReimbursements);
    }

    public DBResponse deleteTravelExpense(String id,String expenseId){
        Trip trip=reportRepository.findTripBy_id(id);
        List<ExpenseReimbursement> expenseReimbursements=new ArrayList<>();
        if(trip.getExpenseReimbursementList()!=null){
            expenseReimbursements=trip.getExpenseReimbursementList();
        }
        expenseReimbursements.removeIf(expenseReimbursement -> expenseReimbursement.getId().equals(expenseId));
        trip.setExpenseReimbursementList(expenseReimbursements);
        saveTrip(trip);
        return new DBResponse("Success","Changes are made to the list",expenseReimbursements);
    }

    public DBResponse editTravelExpense(String id,String recordId,ExpenseReimbursement expenseReimbursement){
        Trip trip=reportRepository.findTripBy_id(id);
        List<ExpenseReimbursement> expenseReimbursementList=trip.getExpenseReimbursementList();
        for(int i=0;i<expenseReimbursementList.size();i++){
            if(expenseReimbursementList.get(i).getId().equals(recordId)){
                expenseReimbursementList.set(i,expenseReimbursement);
            }
        }
        trip.setExpenseReimbursementList(expenseReimbursementList);
        saveTrip(trip);
        return new DBResponse("Success","Changes are made to the list",expenseReimbursementList);
    }

    public DBResponse editProfile(String id,GeneralInfo generalInfo){

        try{
            Employee employee=employeeRepository.findEmployeeByEmpNo(generalInfo.getEmpNo());
            employee.setEmployeeName(generalInfo.getEmployeeName());
            employee.setEpfNo(generalInfo.getEpfNo());
            employee.setOrganizationalUnit(generalInfo.getOrganizationalUnit());
            employeeRepository.save(employee);
            return new DBResponse("Success","Profile Updated",employee);
        }catch (Exception e){
            e.printStackTrace();
            Employee employee=new Employee(generalInfo.getEmployeeName(),generalInfo.getOrganizationalUnit(),generalInfo.getEpfNo(),generalInfo.getEmpNo());
            employeeRepository.save(employee);
            return new DBResponse("Success","Profile Updated",employee);
        }
    }

    public DBResponse getEmployeeInfo(int empNo){
        Employee employee=employeeRepository.findEmployeeByEmpNo(empNo);
        return new DBResponse("Success","General Info",employee);
    }

    public DBResponse editGeneralInfo(String id,TripGeneralInfo tripGeneralInfo,int empNo){
        Employee employee=employeeRepository.findEmployeeByEmpNo(empNo);
        GeneralInfo generalInfo=new GeneralInfo(employee.getEmployeeName(),employee.getOrganizationalUnit(),employee.getEpfNo(),empNo);
        Trip trip=reportRepository.findTripBy_id(id);
        trip.setGeneralInfo(generalInfo);
        trip.setTripGeneralInfo(tripGeneralInfo);
        saveTrip(trip);
        return new DBResponse("Success","Profile Updated",tripGeneralInfo);
    }

    public DBResponse login(String username,String password){
        Employee e=employeeRepository.findEmployeeByUsername(username);

        if(e==null){
            return new DBResponse("Failed","Username does not exists","Register Again");
        }
        else{
            if(e.getPassword().equals(password)){
                System.out.println(e.getEmployeeName()+" logged in");
                return new DBResponse("Login Success","User Details are sent",e);
            }else{
                return new DBResponse("Login Failed","please check your password",null);
            }
        }
    }

    public DBResponse register(Employee employee){
        try{
            Employee e=employeeRepository.findEmployeeByEmpNo(employee.getEmpNo());
            System.out.println(e);
            Employee u=employeeRepository.findEmployeeByUsername(employee.getUsername());
            if(e==null && u==null){
                employeeRepository.save(employee);
                return new DBResponse("Registration Success","User Created",employee);
            }
            else {
                return new DBResponse("Registration Failed","User Exists",e);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new DBResponse("Registration Failed","Exception found",e);
        }

    }
}

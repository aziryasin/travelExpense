package com.aziryasin.travel_expense.DAO;



import com.aziryasin.travel_expense.Entity.ReportResponse;
import com.aziryasin.travel_expense.Model.Employee;
import com.aziryasin.travel_expense.Model.Trip;
import com.aziryasin.travel_expense.Repository.EmployeeRepository;
import com.aziryasin.travel_expense.Repository.ReportRepository;
import com.aziryasin.travel_expense.config.CellsConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

@Repository
public class ReportGenerateDao {




        @Autowired
        DatabaseDao databaseDao;

        @Autowired
        ReportRepository reportRepository;

        @Autowired
        EmployeeRepository employeeRepository;

        public ReportResponse generatePriorReport(String id,int empNo){
            InputStream inp;
            CellsConfig cellsConfig=getCellsConfig();

            Trip priorReportData=reportRepository.findTripBy_id(id);


            try {

                inp=new URL("http://cmbintweb/visitor/Shared%20Documents/Travel%20Expense%20Form.xlsx").openStream();
               try{
                    Workbook workbook=WorkbookFactory.create(inp);
                    writePriorData(workbook,priorReportData,cellsConfig,empNo);
                    //writing to excel file
                    OutputStream fileOut=new FileOutputStream("Travel_Expense_Filled_Prior.xlsx");
                    workbook.write(fileOut);

                }catch (InvalidFormatException e){
                    e.printStackTrace();
                   return new ReportResponse("Failed",e.getMessage(),"No file created");
                }catch (IOException c){
                    c.printStackTrace();
                   return new ReportResponse("Failed",c.getMessage(),"No file created");
                }
            }catch (MalformedURLException e){
                e.printStackTrace();
                return new ReportResponse("Failed",e.getMessage(),"No file created");
            }catch (IOException e){
                e.printStackTrace();
                return new ReportResponse("Failed",e.getMessage(),"No file created");
            }
            System.out.println("Report Generation Success");
            return new ReportResponse("Success","URL to the filled excel file is in the path",System.getProperty("user.dir")+"\\Travel_Expense_Filled_Prior.xlsx");
        }

    public ReportResponse generateFinalReport(String id,int empNo){
        CellsConfig cellsConfig=getCellsConfig();
        Trip finalReportData=reportRepository.findTripBy_id(id);

        InputStream inp;
        try {

            inp=new URL("http://cmbintweb/visitor/Shared%20Documents/Travel%20Expense%20Form.xlsx").openStream();
            try{
                Workbook workbook=WorkbookFactory.create(inp);
                writeFinalData(workbook,finalReportData,cellsConfig,empNo);
                //writing to excel file
                OutputStream fileOut=new FileOutputStream("C:\\Apache24\\htdocs\\travelExpense\\Travel_Expense_Filled.xlsx");
                workbook.write(fileOut);

            }catch (InvalidFormatException e){
                e.printStackTrace();
                return new ReportResponse("Failed",e.getMessage(),"No file created");
            }catch (IOException c){
                c.printStackTrace();
                return new ReportResponse("Failed",c.getMessage(),"No file created");
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
            return new ReportResponse("Failed",e.getMessage(),"No file created");
        }catch (IOException e){
            e.printStackTrace();
            return new ReportResponse("Failed",e.getMessage(),"No file created");
        }
        System.out.println("Report Generation Success");
        return new ReportResponse("Success","URL to the filled excel file is in the path","C:\\Apache24\\htdocs\\travelExpense\\Travel_Expense_Filled.xlsx");
    }


        private void writePriorData(Workbook workbook,Trip priorReportData,CellsConfig cellsConfig,int empNo){
            Sheet sheet=workbook.getSheetAt(0);

            /* General Information Filling start*/
            Cell nameCell=sheet.getRow(cellsConfig.getGeneralInfo().getNameCell().get(0)).getCell(cellsConfig.getGeneralInfo().getNameCell().get(1));
            Cell empNoCell=sheet.getRow(cellsConfig.getGeneralInfo().getEmpNoCell().get(0)).getCell(cellsConfig.getGeneralInfo().getEmpNoCell().get(1));
            Cell orgCell=sheet.getRow(cellsConfig.getGeneralInfo().getOrgCell().get(0)).getCell(cellsConfig.getGeneralInfo().getOrgCell().get(1));
            Cell epfNoCell=sheet.getRow(cellsConfig.getGeneralInfo().getEpfNoCell().get(0)).getCell(cellsConfig.getGeneralInfo().getEpfNoCell().get(1));


            try{
                Employee employee=employeeRepository.findEmployeeByEmpNo(empNo);


            nameCell.setCellValue(employee.getEmployeeName());
            empNoCell.setCellValue(employee.getEmpNo());
            epfNoCell.setCellValue(employee.getEpfNo());
            orgCell.setCellValue(employee.getOrganizationalUnit());
            /* General Information Filling end*/

            }catch (NullPointerException e){
                e.printStackTrace();
            }


            /* Trip General Information Filling start*/

            Cell projNamesCell=sheet.getRow(cellsConfig.getTripInfo().getProjectNamesCell().get(0)).getCell(cellsConfig.getTripInfo().getProjectNamesCell().get(1));
            Cell idsCell=sheet.getRow(cellsConfig.getTripInfo().getProjectIdsCell().get(0)).getCell(cellsConfig.getTripInfo().getProjectIdsCell().get(1));
            Cell costCenter=sheet.getRow(cellsConfig.getTripInfo().getCostCenterCell().get(0)).getCell(cellsConfig.getTripInfo().getCostCenterCell().get(1));
            Cell startDate=sheet.getRow(cellsConfig.getTripInfo().getStartDateCell().get(0)).getCell(cellsConfig.getTripInfo().getStartDateCell().get(1));
            Cell endDate=sheet.getRow(cellsConfig.getTripInfo().getEndDateCell().get(0)).getCell(cellsConfig.getTripInfo().getEndDateCell().get(1));

            projNamesCell.setCellValue(priorReportData.getTripGeneralInfo().getProjectNames());
            idsCell.setCellValue(priorReportData.getTripGeneralInfo().getProjectIDs());
            costCenter.setCellValue(priorReportData.getTripGeneralInfo().getCostCenter());
            startDate.setCellValue(priorReportData.getTripGeneralInfo().getStartDate());
            endDate.setCellValue(priorReportData.getTripGeneralInfo().getEndDate());
            /* Trip General Information Filling end*/



            /* Travel Information Filling start*/
            for(int i=0;i<priorReportData.getTravelInfoList().size();i++){
                Row travelInfo=sheet.getRow(cellsConfig.getTravelInfo().getPurposeCell().get(0)+i);

                Cell purpose=travelInfo.getCell(cellsConfig.getTravelInfo().getPurposeCell().get(1));
                Cell country=travelInfo.getCell(cellsConfig.getTravelInfo().getCountryCell().get(1));
                Cell days=travelInfo.getCell(cellsConfig.getTravelInfo().getDaysCell().get(1));
                Cell currencyCode=travelInfo.getCell(cellsConfig.getTravelInfo().getCurrencyCodeCell().get(1));
                Cell dailyAllowance=travelInfo.getCell(cellsConfig.getTravelInfo().getDailyAllowanceCell().get(1));
                Cell rateLKR=travelInfo.getCell(cellsConfig.getTravelInfo().getCurrentRateLKRCell().get(1));
                Cell rateSEK=travelInfo.getCell(cellsConfig.getTravelInfo().getCurrentRateSEKCell().get(1));

                purpose.setCellValue(priorReportData.getTravelInfoList().get(i).getPurpose());
                country.setCellValue(priorReportData.getTravelInfoList().get(i).getCountry());
                days.setCellValue(priorReportData.getTravelInfoList().get(i).getDays());
                currencyCode.setCellValue(priorReportData.getTravelInfoList().get(i).getCurrencyCode());
                dailyAllowance.setCellValue(priorReportData.getTravelInfoList().get(i).getDailyAllowance());
                rateLKR.setCellValue(priorReportData.getTravelInfoList().get(i).getCurrentRateLKR());
                rateSEK.setCellValue(priorReportData.getTravelInfoList().get(i).getCurrentRateSEK());
            }
            /* Travel Information Filling end*/



            /* Advances Given Filling start*/
            for(int i=0;i<priorReportData.getAdvancesGivenList().size();i++){
                Row advances=sheet.getRow(cellsConfig.getAdvancesGiven().getTransactDateCell().get(0)+i);


                Cell transactDate=advances.getCell(cellsConfig.getAdvancesGiven().getTransactDateCell().get(1));
                Cell transactCurrency=advances.getCell(cellsConfig.getAdvancesGiven().getTransactCurrencyCell().get(1));
                Cell transactAmount=advances.getCell(cellsConfig.getAdvancesGiven().getTransactAmountCell().get(1));
                Cell currentRateLKR=advances.getCell(cellsConfig.getAdvancesGiven().getCurrentRateLKRCell().get(1));

                transactDate.setCellValue(priorReportData.getAdvancesGivenList().get(i).getTransactDate());
                transactCurrency.setCellValue(priorReportData.getAdvancesGivenList().get(i).getTransactCurrency());
                transactAmount.setCellValue(priorReportData.getAdvancesGivenList().get(i).getTransactAmount());
                currentRateLKR.setCellValue(priorReportData.getAdvancesGivenList().get(i).getCurrentRateLKR());
            }
            /* Advances Given Filling end*/


            /* Travel Cost Filling start*/
            for(int i=0;i<priorReportData.getTravelCostsList().size();i++){
                Row cost=sheet.getRow(cellsConfig.getTravelCosts().getDescriptionCell().get(0)+i);

                Cell desc=cost.getCell(cellsConfig.getTravelCosts().getDescriptionCell().get(1));
                Cell transactDate=cost.getCell(cellsConfig.getTravelCosts().getTransactDateCell().get(1));
                Cell transactCurrency=cost.getCell(cellsConfig.getTravelCosts().getTransactCurrencyCell().get(1));
                Cell transactAmount=cost.getCell(cellsConfig.getTravelCosts().getTransactAmountCell().get(1));
                Cell currentRateLKR=cost.getCell(cellsConfig.getTravelCosts().getCurrentRateLKRCell().get(1));
                Cell currentRateSEK=cost.getCell(cellsConfig.getTravelCosts().getCurrentRateSEKCell().get(1));

                desc.setCellValue(priorReportData.getTravelCostsList().get(i).getDescription());
                transactDate.setCellValue(priorReportData.getTravelCostsList().get(i).getTransactDate());
                transactCurrency.setCellValue(priorReportData.getTravelCostsList().get(i).getTransactCurrency());
                transactAmount.setCellValue(priorReportData.getTravelCostsList().get(i).getTransactAmount());
                currentRateLKR.setCellValue(priorReportData.getTravelCostsList().get(i).getCurrentRateLKR());
                currentRateSEK.setCellValue(priorReportData.getTravelCostsList().get(i).getCurrentRateSEK());
            }
            /* Travel Cost Filling end*/



        }

        private void writeFinalData(Workbook workbook, Trip finalReportData,CellsConfig cellsConfig,int empNo){
            Trip priorReportData=new Trip(finalReportData.getGeneralInfo(),finalReportData.getTripGeneralInfo(),finalReportData.getTravelInfoList(),finalReportData.getAdvancesGivenList(),finalReportData.getTravelCostsList());
            writePriorData(workbook,priorReportData,cellsConfig,empNo);

            Sheet sheet=workbook.getSheetAt(1);

            /* Expense Reimbursements Filling start*/
            for(int i=0;i<finalReportData.getExpenseReimbursementList().size();i++){
                Row cost=sheet.getRow(cellsConfig.getReimbursements().getDescriptionCell().get(0)+i);

                Cell desc=cost.getCell(cellsConfig.getReimbursements().getDescriptionCell().get(1));
                Cell transactDate=cost.getCell(cellsConfig.getReimbursements().getTransactDateCell().get(1));
                Cell transactCurrency=cost.getCell(cellsConfig.getReimbursements().getTransactCurrencyCell().get(1));
                Cell transactAmount=cost.getCell(cellsConfig.getReimbursements().getTransactAmountCell().get(1));
                Cell currentRateLKR=cost.getCell(cellsConfig.getReimbursements().getCurrentRateLKRCell().get(1));
                Cell currentRateSEK=cost.getCell(cellsConfig.getReimbursements().getCurrentRateSEKCell().get(1));

                desc.setCellValue(finalReportData.getExpenseReimbursementList().get(i).getDescription());
                transactDate.setCellValue(finalReportData.getExpenseReimbursementList().get(i).getTransactDate());
                transactCurrency.setCellValue(finalReportData.getExpenseReimbursementList().get(i).getTransactCurrency());
                transactAmount.setCellValue(finalReportData.getExpenseReimbursementList().get(i).getTransactAmount());
                currentRateLKR.setCellValue(finalReportData.getExpenseReimbursementList().get(i).getCurrentRateLKR());
                currentRateSEK.setCellValue(finalReportData.getExpenseReimbursementList().get(i).getCurrentRateSEK());
            }
            /* Expense Reimbursements Filling end*/

        }



        private CellsConfig getCellsConfig(){
            CellsConfig cellsConfig;
            InputStream json;
            ObjectMapper objectMapper=new ObjectMapper();

            try {
                json=new FileInputStream("cellsConfig.json");
                cellsConfig=objectMapper.readValue(json,CellsConfig.class);
                System.out.println("cellsConfig.json is opened");
                return cellsConfig;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }





}

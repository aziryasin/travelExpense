package com.aziryasin.travel_expense.Controller;

import com.aziryasin.travel_expense.Entity.*;
import com.aziryasin.travel_expense.Repository.ReportRepository;
import com.aziryasin.travel_expense.Service.ReportGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reportGenerate")
public class ReportGenerateController {


    @Autowired
    ReportGenerateService reportGenerateService;

    @Autowired
    ReportRepository reportRepository;


    @RequestMapping(value = "/prior" , method = RequestMethod.POST)
    public ReportResponse generatePriorReport(@RequestParam String id,@RequestParam int empNo){
        return reportGenerateService.generatePriorReport(id,empNo);
    }

    @RequestMapping(value = "/final" , method = RequestMethod.POST)
    public ReportResponse generateFinalReport(@RequestParam String id,@RequestParam int empNo){
        return reportGenerateService.generateFinalReport(id,empNo);
    }


}

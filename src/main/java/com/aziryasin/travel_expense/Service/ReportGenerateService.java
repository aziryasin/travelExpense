package com.aziryasin.travel_expense.Service;

import com.aziryasin.travel_expense.DAO.ReportGenerateDao;
import com.aziryasin.travel_expense.Entity.ReportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
@Service
public class ReportGenerateService {

    @Autowired
    ReportGenerateDao reportGenerateDao;

    public ReportResponse generatePriorReport(String id,int empNo){
        return reportGenerateDao.generatePriorReport(id,empNo);
    }

    public ReportResponse generateFinalReport(String id,int empNo){
        return reportGenerateDao.generateFinalReport(id,empNo);
    }
}

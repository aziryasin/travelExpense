package com.aziryasin.travel_expense.DAO;

import com.aziryasin.travel_expense.Entity.DailyAllowanceResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

import static java.lang.Float.parseFloat;

@Repository
public class DailyAllowanceDao {

@Autowired
CurrencyDao currencyDao;
    public DailyAllowanceResponse getDailyAllowance(String date, String country, String currencyCode){
        try {
            Document document= Jsoup.connect("http://cmbintweb/visitor/Shared%20Documents/Travel%20Allowances.aspx").get();
            Elements values= document.select("td p span");
            for(int i=0;i<values.size();i++){
               if(values.get(i).text().equals(country)){
                   if(values.get(i+1).text().substring(0,3).equals(currencyCode)){
                       return new DailyAllowanceResponse("Success","Daily Allowance for "+country+" in "+currencyCode,parseFloat(values.get(i+1).text().substring(3).trim()));
                   }else {
                       return new DailyAllowanceResponse("Success","Daily Allowance for "+country+" in "+currencyCode,currencyDao.getCurrencyResponse(date,currencyCode,values.get(i+1).text().trim().substring(0,3).trim()).getRate()*parseFloat(values.get(i+1).text().trim().substring(3).trim()));
                       }
               }
            }
        }catch (IOException e){
            e.getMessage();
        }
        return new DailyAllowanceResponse("Failed","Daily Allowance for "+country+" is not available",0);
    }
}

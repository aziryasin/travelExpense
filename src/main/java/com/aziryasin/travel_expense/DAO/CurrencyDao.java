package com.aziryasin.travel_expense.DAO;

import com.aziryasin.travel_expense.Entity.CurrencyConRes;
import com.aziryasin.travel_expense.Entity.CurrencyResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class CurrencyDao {

    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

    public CurrencyResponse getCurrencyResponse(String date,String baseCurrency,String quoteCurrency){


        try{
            Date parsedDate = sdf.parse(date);
            SimpleDateFormat print = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(print.format(parsedDate));
            String uri="https://www.oanda.com/currency/converter/update?base_currency_0="+baseCurrency+"&quote_currency="+quoteCurrency+"&end_date="+print.format(parsedDate)+"&view=details&id=1&action=C&";

            HttpHeaders headers=new HttpHeaders();
            headers.set("x-requested-with","XMLHttpRequest");

            HttpEntity entity=new HttpEntity(headers);

            RestTemplate restTemplate=new RestTemplate();


            ResponseEntity<CurrencyConRes> res=restTemplate.exchange(uri, HttpMethod.GET,entity,CurrencyConRes.class);
            try{
                int len=res.getBody().getData().getChartData().size();
                float rate=res.getBody().getData().getChartData().get(len-1).get(3);
                if(rate==0){
                    return new CurrencyResponse("Failed","Please check your currency code",rate);
                }else{
                    return new CurrencyResponse("Success","Currency Rate for 1 "+quoteCurrency+" in "+baseCurrency,rate);
                }

            }catch (Exception e){
                e.printStackTrace();
                return new CurrencyResponse("Failed","Check your currency codes",0);
            }
        }catch (ParseException e){
            e.printStackTrace();
            return new CurrencyResponse("Failed","Check your dates",0);
        }
    }

}

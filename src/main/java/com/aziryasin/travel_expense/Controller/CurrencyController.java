package com.aziryasin.travel_expense.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.aziryasin.travel_expense.Entity.CurrencyResponse;
import com.aziryasin.travel_expense.Entity.SEKRateResponse;
import com.aziryasin.travel_expense.Service.CurrencyService;
import com.aziryasin.travel_expense.Service.SEKrateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private SEKrateService seKrateService;

    @RequestMapping(value = "/convert" , method = RequestMethod.GET)
    public CurrencyResponse getCurrencyResponse(@RequestParam("date") String date, @RequestParam("baseCurrency") String baseCurrency, @RequestParam("quoteCurrency") String quoteCurrency){
        return currencyService.getCurrencyResponse(date, baseCurrency, quoteCurrency);
    }

    @RequestMapping(value = "/convertSEK" , method = RequestMethod.GET)
    public SEKRateResponse getSEKrate(@RequestParam("currencyCode") String currencyCode,@RequestParam("date") String date){
        return seKrateService.getSEKrate(currencyCode,date);
    }
}

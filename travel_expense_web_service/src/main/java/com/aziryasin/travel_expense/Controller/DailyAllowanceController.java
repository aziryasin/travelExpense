package com.aziryasin.travel_expense.Controller;

import com.aziryasin.travel_expense.Entity.DailyAllowanceResponse;
import com.aziryasin.travel_expense.Service.DailyAllowanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dailyAllowance")
public class DailyAllowanceController {
    @Autowired
    DailyAllowanceService dailyAllowanceService;

    @RequestMapping(method = RequestMethod.GET)
    public DailyAllowanceResponse getDailyAllowance(@RequestParam("date") String date, @RequestParam("country") String country, @RequestParam("currencyCode") String currencyCode){
        return dailyAllowanceService.getDailyAllowance(date,country,currencyCode);
    }
}

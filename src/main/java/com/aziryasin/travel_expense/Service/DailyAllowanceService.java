package com.aziryasin.travel_expense.Service;

import com.aziryasin.travel_expense.DAO.DailyAllowanceDao;
import com.aziryasin.travel_expense.Entity.DailyAllowanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailyAllowanceService {
    @Autowired
    public DailyAllowanceDao dailyAllowanceDao;

    public DailyAllowanceResponse getDailyAllowance(String date, String country, String currencyCode){
        return dailyAllowanceDao.getDailyAllowance(date,country,currencyCode);
    }
}

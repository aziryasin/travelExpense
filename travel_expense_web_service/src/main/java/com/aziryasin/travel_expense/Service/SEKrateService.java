package com.aziryasin.travel_expense.Service;

import com.aziryasin.travel_expense.DAO.SEKrateDao;
import com.aziryasin.travel_expense.Entity.SEKRateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SEKrateService {

    @Autowired
    SEKrateDao seKrateDao;

    public SEKRateResponse getSEKrate(String currencyCode, String date){
        return seKrateDao.getSEKrate(currencyCode,date);
    }
}

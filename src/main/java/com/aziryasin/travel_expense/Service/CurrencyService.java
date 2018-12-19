package com.aziryasin.travel_expense.Service;

import com.aziryasin.travel_expense.DAO.CurrencyDao;
import com.aziryasin.travel_expense.Entity.CurrencyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyDao currencyDao;

    public CurrencyResponse getCurrencyResponse(String date, String baseCurrency, String quoteCurrency){

        return currencyDao.getCurrencyResponse(date, baseCurrency, quoteCurrency);
    }
}

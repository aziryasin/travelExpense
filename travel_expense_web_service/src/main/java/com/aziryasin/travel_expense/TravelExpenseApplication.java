package com.aziryasin.travel_expense;

import com.aziryasin.travel_expense.DAO.SEKrateDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TravelExpenseApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelExpenseApplication.class, args);
        SEKrateDao.getFile();
    }
}

package com.aziryasin.travel_expense.Repository;

import com.aziryasin.travel_expense.Entity.TravelInfo;
import com.aziryasin.travel_expense.Entity.TripGeneralInfo;
import com.aziryasin.travel_expense.Model.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends MongoRepository<Trip,Integer> {
    Trip findTripBy_id(String id);
    void deleteTripBy_id(String id);
    List<Trip> findTripByGeneralInfo_EmpNo(int empNo);
}

package com.aziryasin.travel_expense.Repository;

import com.aziryasin.travel_expense.Model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee,String> {
    Employee findEmployeeByEmpNo(int empNo);
    Employee findEmployeeByUsername(String username);
}

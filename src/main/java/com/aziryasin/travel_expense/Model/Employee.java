package com.aziryasin.travel_expense.Model;

import org.springframework.data.annotation.Id;

public class Employee {
    @Id
    private String _id;

    private String employeeName,organizationalUnit,epfNo,username,password;
    private int empNo;

    public Employee() {
    }

    public Employee(String employeeName, String organizationalUnit, String epfNo, int empNo) {
        this.employeeName = employeeName;
        this.organizationalUnit = organizationalUnit;
        this.epfNo = epfNo;
        this.empNo = empNo;
    }

    public Employee(String username, String password, int empNo) {
        this.username = username;
        this.password = password;
        this.empNo = empNo;
    }

    public Employee(String employeeName, String organizationalUnit, String epfNo, String username, String password, int empNo) {
        this.employeeName = employeeName;
        this.organizationalUnit = organizationalUnit;
        this.epfNo = epfNo;
        this.username = username;
        this.password = password;
        this.empNo = empNo;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getOrganizationalUnit() {
        return organizationalUnit;
    }

    public void setOrganizationalUnit(String organizationalUnit) {
        this.organizationalUnit = organizationalUnit;
    }

    public String getEpfNo() {
        return epfNo;
    }

    public void setEpfNo(String epfNo) {
        this.epfNo = epfNo;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

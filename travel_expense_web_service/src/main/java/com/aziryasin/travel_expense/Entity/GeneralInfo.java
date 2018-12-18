package com.aziryasin.travel_expense.Entity;

public class GeneralInfo {
    String employeeName,organizationalUnit,epfNo;
    int empNo;

    public GeneralInfo() {

    }

    public GeneralInfo(String employeeName, String organizationalUnit, String epfNo, int empNo) {
        this.employeeName = employeeName;
        this.organizationalUnit = organizationalUnit;
        this.epfNo = epfNo;
        this.empNo = empNo;
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
}

package model.entity;

import java.sql.Date;
import java.time.LocalDate;

public class Employee {
    private int id;
    private String name;
    private Date dob;
    private String phone;
    private String address;
    private Date startDay;
    private Double salaryLevel;
    private String citizenID;
    private String insuranceID;
    private int role;
    private int branchID;
    private boolean working;

    public Employee(){

    }

    public Employee(String name, int role, int branchID) {
        this.name = name;
        this.role = role;
        this.branchID = branchID;
        startDay = Date.valueOf(LocalDate.now());
        salaryLevel = 0.0;
        working = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Double getSalaryLevel() {
        return salaryLevel;
    }

    public void setSalaryLevel(Double salaryLevel) {
        this.salaryLevel = salaryLevel;
    }

    public String getCitizenID() {
        return citizenID;
    }

    public void setCitizenID(String citizenID) {
        this.citizenID = citizenID;
    }

    public String getInsuranceID() {
        return insuranceID;
    }

    public void setInsuranceID(String insuranceID) {
        this.insuranceID = insuranceID;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getBranchID() {
        return branchID;
    }

    public void setBranchID(int branchID) {
        this.branchID = branchID;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }
}

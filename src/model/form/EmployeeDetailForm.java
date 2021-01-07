package model.form;

import javafx.scene.control.Accordion;
import model.ExcelObject;
import model.entity.Account;
import model.entity.Branch;
import model.entity.Employee;

import java.sql.Date;

public class EmployeeDetailForm implements ExcelObject {
    private int employeeId;
    private String name;
    private Date dob;
    private String phone;
    private String address;
    private double salaryLevel;
    private Date startDate;
    private String citizenId;
    private String insuranceId;
    private int branchId;
    private String branchName;
    private int role;
    private boolean working;


    public EmployeeDetailForm(Employee employee, Branch branch){
        employeeId = employee.getId();
        name = employee.getName();
        dob = employee.getDob();
        phone = employee.getPhone();
        address = employee.getAddress();
        salaryLevel = employee.getSalaryLevel();
        startDate = employee.getStartDay();
        citizenId = employee.getCitizenID();
        insuranceId = employee.getInsuranceID();
        branchId = branch.getId();
        branchName = branch.getName();
        role = employee.getRole();
        working = employee.isWorking();
    }


    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    public double getSalaryLevel() {
        return salaryLevel;
    }

    public void setSalaryLevel(double salaryLevel) {
        this.salaryLevel = salaryLevel;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    @Override
    public Object[] getFields() {
        String roleName = "";
        if(role == 1){
            roleName = "Quản lí";
        }else if(role == 2){
            roleName = "Nhân viên bán hàng";
        }else{
            roleName = "Nhân viên kỹ thuật";
        }
        return new Object[]{getName(), getDob(), getPhone(), getAddress(), getCitizenId(), roleName, getBranchName()};
    }
}

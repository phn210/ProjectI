package model.form;

import model.entity.Salary;

public class SalaryDetailForm {
    private int employeeId;
    private int month;
    private int year;
    private double salaryLevel;
    private double totalHours;
    private double totalSalary;
    private String name;

    public SalaryDetailForm(Salary salary, String name) {
        employeeId = salary.getEmployeeID();
        month = salary.getMonth();
        year = salary.getYear();
        salaryLevel = salary.getSalaryLevel();
        totalHours = salary.getTotalHour();
        totalSalary = salary.getTotalSalary();
        this.name = name;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getSalaryLevel() {
        return salaryLevel;
    }

    public void setSalaryLevel(double salaryLevel) {
        this.salaryLevel = salaryLevel;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

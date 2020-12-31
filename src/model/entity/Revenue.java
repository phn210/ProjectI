package model.entity;

public class Revenue {
    private int branchID;
    private int month;
    private int year;
    private Double totalIncome;
    private Double facilitiesFee;
    private Double tax;
    private Double totalSalary;
    private Double revenue;

    public Revenue(){

    }

    public int getBranchID() {
        return branchID;
    }

    public void setBranchID(int branchID) {
        this.branchID = branchID;
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

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getFacilitiesFee() {
        return facilitiesFee;
    }

    public void setFacilitiesFee(Double facilitiesFee) {
        this.facilitiesFee = facilitiesFee;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(Double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }
}

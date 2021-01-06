package model.form;

import model.entity.*;

import java.sql.Date;

public class InvoiceForm {
    private int id;
    private Date date;
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private String employee;
    private String paymentMethod;
    private Double totalMoney;
    private Double tax;
    private Double surcharge;
    private String note;
    private String branch;

    public InvoiceForm(){

    }

    public InvoiceForm(Invoice invoice, Customer customer, Employee employee, Branch branch){
        this.setId(invoice.getId());
        this.setDate(invoice.getDate());
        this.setCustomerName(customer.getName());
        this.setCustomerPhone(customer.getPhone());
        this.setCustomerAddress(customer.getAddress());
        this.setEmployee(employee.getName());
        this.setPaymentMethod(invoice.getPaymentMethod());
        this.setTotalMoney(invoice.getTotalMoney());
        this.setTax(invoice.getTax());
        this.setSurcharge(invoice.getSurcharge());
        this.setNote(invoice.getNote());
        this.setBranch(branch.getName());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(Double surcharge) {
        this.surcharge = surcharge;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}

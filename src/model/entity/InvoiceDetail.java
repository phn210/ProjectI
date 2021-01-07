package model.entity;

import model.form.InvoiceDetailForm;

public class InvoiceDetail {
    private int invoiceID;
    private int productID;
    private int importID;
    private int amount;
    private Double retailPrice;
    private Double importPrice;
    private int discount;
    private Double totalMoney;

    public InvoiceDetail(){

    }

    public InvoiceDetail(int invoiceID, InvoiceDetailForm invoiceDetailForm){
        this.setInvoiceID(invoiceID);
        this.setProductID(invoiceDetailForm.getProductID());
        this.setImportID(invoiceDetailForm.getImportID());
        this.setAmount(invoiceDetailForm.getAmount());
        this.setRetailPrice(invoiceDetailForm.getRetailPrice());
        this.setImportPrice(invoiceDetailForm.getImportPrice());
        this.setDiscount(invoiceDetailForm.getDiscount());
        this.setTotalMoney(invoiceDetailForm.getTotalMoney());
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getImportID() {
        return importID;
    }

    public void setImportID(int importID) {
        this.importID = importID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(Double importPrice) {
        this.importPrice = importPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }
}

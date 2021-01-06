package model.form;

import model.entity.InvoiceDetail;
import model.entity.Product;

public class InvoiceDetailForm {
    private int invoiceID;
    private String product;
    private int importID;
    private int amount;
    private Double retailPrice;
    private Double importPrice;
    private int discount;
    private Double totalMoney;

    public InvoiceDetailForm(){

    }

    public InvoiceDetailForm(InvoiceDetail invoiceDetail, Product product){
        this.setInvoiceID(invoiceDetail.getInvoiceID());
        this.setProduct(product.getName());
        this.setImportID(invoiceDetail.getImportID());
        this.setAmount(invoiceDetail.getAmount());
        this.setRetailPrice(invoiceDetail.getRetailPrice());
        this.setImportPrice(invoiceDetail.getImportPrice());
        this.setDiscount(invoiceDetail.getDiscount());
        this.setTotalMoney(invoiceDetail.getTotalMoney());
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
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

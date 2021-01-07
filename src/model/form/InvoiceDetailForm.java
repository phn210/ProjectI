package model.form;

import model.ExcelObject;
import model.entity.ImportDetail;
import model.entity.Invoice;
import model.entity.InvoiceDetail;
import model.entity.Product;

public class InvoiceDetailForm implements ExcelObject {
    private int productID;
    private String productName;
    private int importID;
    private int amount;
    private Double retailPrice;
    private Double importPrice;
    private int discount;
    private Double totalMoney;

    public InvoiceDetailForm(){

    }

    public InvoiceDetailForm(InvoiceDetail invoiceDetail, Product productName){
        this.setProductID(productName.getId());
        this.setProductName(productName.getName());
        this.setImportID(invoiceDetail.getImportID());
        this.setAmount(invoiceDetail.getAmount());
        this.setRetailPrice(invoiceDetail.getRetailPrice());
        this.setImportPrice(invoiceDetail.getImportPrice());
        this.setDiscount(invoiceDetail.getDiscount());
        this.setTotalMoney(invoiceDetail.getTotalMoney());
    }

    public InvoiceDetailForm(ImportDetail importDetail, Product product, int amount){
        this.setProductID(product.getId());
        this.setProductName(product.getName());
        this.setImportID(importDetail.getImportID());
        this.setAmount(amount);
        this.setRetailPrice(product.getRetailPrice());
        this.setImportPrice(importDetail.getImportPrice());
        this.setDiscount(product.getDiscount());
        this.setTotalMoney(product.getRetailPrice()*(100-discount)*0.01*amount);
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    @Override
    public Object[] getFields() {
        return new Object[]{getProductName(), getImportID(), getAmount(), getRetailPrice(), getDiscount(), getTotalMoney()};
    }
}

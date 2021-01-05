package model.form;

import model.entity.Import;
import model.entity.ImportDetail;
import model.entity.Supplier;

import java.sql.Date;

public class ProductDetailForm{
    private int importID;
    private String supplier;
    private Date importDate;
    private int amount;
    private Double importPrice;

    public ProductDetailForm(){

    }

    public ProductDetailForm(ImportDetail importDetail, Import anImport, Supplier supplier){
        this.setImportID(anImport.getId());
        this.setSupplier(supplier.getName());
        this.setImportDate(anImport.getImportDate());
        this.setAmount(importDetail.getAmount());
        this.setImportPrice(importDetail.getImportPrice());
    }

    public int getImportID() {
        return importID;
    }

    public void setImportID(int importID) {
        this.importID = importID;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(Double importPrice) {
        this.importPrice = importPrice;
    }
}

package model.entity;

import model.form.ImportDetailForm;

public class ImportDetail {
    private int importID;
    private int productID;
    private int amount;
    private Double importPrice;

    public ImportDetail(){

    }

    public ImportDetail(int importID, ImportDetailForm importDetailForm){
        this.setImportID(importID);
        this.setProductID(importDetailForm.getProductID());
        this.setAmount(importDetailForm.getAmount());
        this.setImportPrice(importDetailForm.getImportPrice());
    }

    public int getImportID() {
        return importID;
    }

    public void setImportID(int importID) {
        this.importID = importID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
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

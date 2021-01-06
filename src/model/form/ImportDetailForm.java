package model.form;

import model.entity.Import;
import model.entity.ImportDetail;
import model.entity.Product;

public class ImportDetailForm {
    private int productID;
    private String productName;
    private int amount;
    private Double importPrice;

    public ImportDetailForm(){
    }

    public ImportDetailForm(Product product, int amount, double importPrice){
        this.setProductID(product.getId());
        this.setProductName(product.getName());
        this.setAmount(amount);
        this.setImportPrice(importPrice);
    }

    public ImportDetailForm(ImportDetail importDetail, Product product){
        this.setProductID(importDetail.getProductID());
        this.setProductName(product.getName());
        this.setAmount(importDetail.getAmount());
        this.setImportPrice(importDetail.getImportPrice());
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

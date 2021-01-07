package model.form;

import model.entity.Brand;
import model.entity.ImportDetail;
import model.entity.Product;
import model.entity.Type;

import java.util.ArrayList;

public class ProductForm {
    private int id;
    private String name;
    private String description;
    private Double retailPrice;
    private int discount;
    private String brand;
    private String type;
    private int amount;

    public ProductForm(){

    }

    public ProductForm(Product product, Type type, Brand brand){
        this.setId(product.getId());
        this.setName(product.getName());
        this.setDescription(product.getDescription());
        this.setRetailPrice(product.getRetailPrice());
        this.setDiscount(product.getDiscount());
        this.setBrand(brand.getName());
        this.setType(type.getName());
        this.setAmount(product.getAmount());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Object[] getFields() {
        return new Object[]{getId(),getName(),getType(),getBrand(),getAmount(),getDescription(),getRetailPrice(),getDiscount()};
    }
}

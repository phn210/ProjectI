package model.form;

import model.entity.Brand;
import model.entity.ImportDetail;
import model.entity.Product;
import model.entity.Type;

import java.util.ArrayList;

public class ProductForm {
    private Product product;
    private Type type;
    private Brand brand;
    private ArrayList<ImportDetail> importDetails;

    public ProductForm() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public ArrayList<ImportDetail> getImportDetails() {
        return importDetails;
    }

    public void setImportDetails(ArrayList<ImportDetail> importDetails) {
        this.importDetails = importDetails;
    }
}

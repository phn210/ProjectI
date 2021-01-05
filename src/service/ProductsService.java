package service;

import model.entity.Brand;
import model.entity.Product;
import model.entity.Type;
import model.form.ProductForm;
import repository.BrandRepo;
import repository.ProductRepo;
import repository.TypeRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductsService {
    private ProductRepo productRepo;
    private TypeRepo typeRepo;
    private BrandRepo brandRepo;

    public ProductsService(){
        this.productRepo = new ProductRepo();
        this.typeRepo = new TypeRepo();
        this.brandRepo = new BrandRepo();
    }

    public ArrayList<ProductForm> getAllProductForm() throws SQLException {
        ArrayList<Product> products = (ArrayList<Product>) productRepo.findAll();
        ArrayList<ProductForm> productForms = new ArrayList<>();

        for(Product product : products){
            Type type = new Type();
            Brand brand = new Brand();
            try {
                type = typeRepo.findByID(product.getTypeID());
                brand = brandRepo.findByID(product.getBrandID());
            } catch (NullPointerException e) {
                return null;
            }
            ProductForm productForm = new ProductForm(product, type, brand);
            productForms.add(productForm);
        }

        return productForms;
    }
}

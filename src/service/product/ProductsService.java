package service.product;

import model.entity.*;
import model.form.*;
import repository.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductsService {
    public ProductRepo productRepo;
    public TypeRepo typeRepo;
    public BrandRepo brandRepo;
    public ImportRepo importRepo;
    public SupplierRepo supplierRepo;
    public ImportDetailRepo importDetailRepo;

    public ProductsService(){
        this.productRepo = new ProductRepo();
        this.typeRepo = new TypeRepo();
        this.brandRepo = new BrandRepo();
        this.importRepo = new ImportRepo();
        this.supplierRepo = new SupplierRepo();
        this.importDetailRepo = new ImportDetailRepo();
    }

    public ArrayList<ProductForm> getAllProductForm() throws SQLException, NullPointerException {
        ArrayList<Product> products = productRepo.findAll();
        ArrayList<ProductForm> productForms = new ArrayList<>();

        for(Product product : products){
            Type type = typeRepo.findByID(product.getTypeID());
            Brand brand = brandRepo.findByID(product.getBrandID());
            ProductForm productForm = new ProductForm(product, type, brand);
            productForms.add(productForm);
        }

        return productForms;
    }

    public ArrayList<String> getAllTypeName() throws SQLException, NullPointerException {
        ArrayList<Product> products = productRepo.findAll();
        ArrayList<String> types = new ArrayList<>();
        for(Product product : products){
            Type type = typeRepo.findByID(product.getTypeID());
            types.add(type.getName());
        }
        return types;
    }

    public ArrayList<String> getAllBrandName() throws SQLException, NullPointerException {
        ArrayList<Product> products = productRepo.findAll();
        ArrayList<String> brands = new ArrayList<>();
        for(Product product : products){
            Brand brand = brandRepo.findByID(product.getBrandID());
            brands.add(brand.getName());
        }
        return brands;
    }

    public ArrayList<ImportForm> getAllImportForm() throws SQLException, NullPointerException {
        ArrayList<Import> imports = importRepo.findAll();
        ArrayList<ImportForm> importForms = new ArrayList<>();
        for(Import anImport : imports){
            Supplier supplier = supplierRepo.findByID(anImport.getSupplierID());
            ImportForm importForm = new ImportForm(anImport, supplier);
            importForms.add(importForm);
        }
        return importForms;
    }

    public ArrayList<String> getAllSupplierName() throws SQLException, NullPointerException {
        ArrayList<Import> imports = importRepo.findAll();
        ArrayList<String> suppliers = new ArrayList<>();
        for(Import anImport: imports){
            Type type = typeRepo.findByID(anImport.getSupplierID());
            suppliers.add(type.getName());
        }
        return suppliers;
    }

    public ArrayList<ProductDetailForm> getAllProductDetailForm(Product product) throws SQLException, NullPointerException {
        ArrayList<ImportDetail> importDetails = importDetailRepo.getImportDetails(product);
        ArrayList<ProductDetailForm> productDetailForms = new ArrayList<>();
        for(ImportDetail importDetail: importDetails){
            Import anImport = importRepo.findByID(importDetail.getImportID());
            Supplier supplier = supplierRepo.findByID(anImport.getSupplierID());
            ProductDetailForm productDetailForm = new ProductDetailForm(importDetail, anImport, supplier);
            productDetailForms.add(productDetailForm);
        }
        return productDetailForms;
    }
}
package service.product;

import model.entity.*;
import model.form.*;
import repository.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<ProductForm> toProductForm(ArrayList<Product> products) throws SQLException, NullPointerException{
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
        ArrayList<Type> types = typeRepo.findAll();
        ArrayList<String> typeNames = new ArrayList<>();
        for(Type type : types){
            typeNames.add(typeRepo.findByID(type.getId()).getName());
        }
        return typeNames;
    }

    public ArrayList<String> getAllBrandName() throws SQLException, NullPointerException {
        ArrayList<Brand> brands = brandRepo.findAll();
        ArrayList<String> brandNames = new ArrayList<>();
        for(Brand brand : brands){
            brandNames.add(brandRepo.findByID(brand.getId()).getName());
        }
        return brandNames;
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

    //-----------------------------------------------------------------

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

    public ArrayList<ImportForm> toImportForm(ArrayList<Import> imports) throws SQLException, NullPointerException {
        ArrayList<ImportForm> importForms = new ArrayList<>();
        for(Import anImport : imports){
            Supplier supplier = supplierRepo.findByID(anImport.getSupplierID());
            ImportForm importForm = new ImportForm(anImport, supplier);
            importForms.add(importForm);
        }
        return importForms;
    }

    public ArrayList<String> getAllSupplierName() throws SQLException, NullPointerException {
        ArrayList<Supplier> suppliers = supplierRepo.findAll();
        ArrayList<String> supplierNames = new ArrayList<>();
        for(Supplier supplier: suppliers){
            supplierNames.add(supplierRepo.findByID(supplier.getId()).getName());
        }
        return supplierNames;
    }

    public ArrayList<ImportDetailForm> getAllImportDetailForm(Import anImport) throws SQLException, NullPointerException {
        ArrayList<ImportDetail> importDetails = importDetailRepo.getImportDetails(anImport);
        ArrayList<ImportDetailForm> importDetailForms = new ArrayList<>();
        for(ImportDetail importDetail: importDetails){
            Product product = productRepo.findByID(importDetail.getProductID());
            ImportDetailForm importDetailForm = new ImportDetailForm(importDetail, product);
            importDetailForms.add(importDetailForm);
        }
        return importDetailForms;
    }

    public void addImport(Import anImport, List<ImportDetailForm> importDetailForms) throws SQLException {
        DBConnector.connection.setAutoCommit(false);
        importRepo.insert(anImport);
        int importID = importRepo.getLastID();
        for (ImportDetailForm importDetailForm: importDetailForms){
            ImportDetail importDetail = new ImportDetail(importID, importDetailForm);
            importDetailRepo.insert(importDetail);
            productRepo.add(importDetail.getProductID(), importDetail.getAmount());
        }
        DBConnector.connection.commit();
        DBConnector.connection.setAutoCommit(true);
    }

}

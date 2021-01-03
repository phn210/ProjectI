package repository;

import model.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepo {
    public ProductRepo(){
        
    }
    
    public List<Product> getAllProduct(){
        List<Product> list = new ArrayList<>();

        String query = "select * from Product";

        try(Connection con = DBConnector.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getNString("name"));
                product.setDescription(rs.getNString("description"));
                product.setRetailPrice(rs.getDouble("retail_price"));
                product.setDiscount(rs.getInt("discount"));
                product.setBrandID(rs.getInt("brand_id"));
                product.setTypeID(rs.getInt("type_id"));
                product.setAmount(rs.getInt("amount"));
                list.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public List<ImportDetail> getImportDetails(Product product){
        List<ImportDetail> list = new ArrayList<>();

        String query = "select * from Import_Detail " +
                        "where product_id = ?";

        try (Connection con = DBConnector.getConnection()){
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, product.getId());
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                ImportDetail importDetail = new ImportDetail();
                importDetail.setImportID(rs.getInt("import_id"));
                importDetail.setProductID(rs.getInt("product_id"));
                importDetail.setAmount(rs.getInt("amount"));
                importDetail.setImportPrice(rs.getDouble("import_price"));
                list.add(importDetail);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public List<Product> search(int id, String name, String type, String brand, boolean discount){
        List<Product> list = new ArrayList<>();

        String query = "select * from Product, " +
                    "(select id, name from Type) as type, " +
                    "(select id, name from Brand) as brand " +
                    "where Product.type_id = type.id " +
                    "and Product.brand_id = brand.id";

        if(!(id == 0))
            query = query + " and Product.id = " + id;
        if(!name.equals(""))
            query = query + " and Product.name like " + "N'%" + name + "%'";
        if(!type.equals(""))
            query = query + " and type.name like " + "N'%" + type + "%'";
        if(!brand.equals(""))
            query = query + " and brand.name like " + "N'%" + brand + "%'";
        if(discount)
            query = query + " and (discount = 0 or discount is null)";

        try (Connection con = DBConnector.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("Product.id"));
                product.setName(rs.getNString("Product.name"));
                product.setDescription(rs.getNString("description"));
                product.setRetailPrice(rs.getDouble("retail_price"));
                product.setDiscount(rs.getInt("discount"));
                product.setBrandID(rs.getInt("brand_id"));
                product.setTypeID(rs.getInt("type_id"));
                product.setAmount(rs.getInt("amount"));
                list.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}

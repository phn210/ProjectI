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

    public List<String> getAllTypeName(){
        List<String> list = new ArrayList<>();

        String query = "select name from Type";

        try (Connection con = DBConnector.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                list.add(rs.getNString("name"));
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

    public Type getType(Product product){
        Type type = new Type();

        String query = "select * from Product " +
                    "where type_id = ?";

        try (Connection con = DBConnector.getConnection()){
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, product.getTypeID());
            ResultSet rs = pstmt.executeQuery();

            if(rs.isBeforeFirst()){
                rs.next();
                type.setId(rs.getInt("id"));
                type.setName(rs.getNString("name"));
                type.setDescription(rs.getNString("description"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return type;
    }

    public Brand getBrand(Product product){
        Brand brand = new Brand();

        String query = "select * from Product " +
                    "where brand_id = ?";

        try (Connection con = DBConnector.getConnection()){
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, product.getBrandID());
            ResultSet rs = pstmt.executeQuery();

            if(rs.isBeforeFirst()){
                rs.next();
                brand.setId(rs.getInt("id"));
                brand.setName(rs.getNString("name"));
                brand.setCountry(rs.getNString("country"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return brand;
    }
}

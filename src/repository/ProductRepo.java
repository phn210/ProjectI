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
    public Product getProduct(int id){
        Product product = new Product();

        String query = "select * from Product " +
                    "where id = " + id;
        try (Connection con = DBConnector.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if(rs.isBeforeFirst()){
                rs.next();
                product.setId(rs.getInt("id"));
                product.setName(rs.getNString("name"));
                product.setDescription(rs.getNString("description"));
                product.setRetailPrice(rs.getDouble("retail_price"));
                product.setDiscount(rs.getInt("discount"));
                product.setBrandID(rs.getInt("brand_id"));
                product.setTypeID(rs.getInt("type_id"));
                product.setAmount(rs.getInt("amount"));
                return product;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return product;
    }

    public boolean addProduct(Product product){
        String insert = "insert into Product(name, description, retail_price, " +
                        "discount, brand_id, type_id, amount) " +
                        "values (?, ?, ?, ?, ?, ?, 0)";
        try (Connection con = DBConnector.getConnection()){
            PreparedStatement pstmt = con.prepareStatement(insert);
            pstmt.setNString(1, product.getName());
            pstmt.setNString(2, product.getDescription());
            pstmt.setDouble(3, product.getRetailPrice());
            pstmt.setInt(4, product.getDiscount());
            pstmt.setInt(5, product.getBrandID());
            pstmt.setInt(6, product.getTypeID());

            int n = pstmt.executeUpdate();
            if(n>0) return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public boolean updateProduct(Product product){
        String update = "update Product " +
                    "set name = ?," +
                    "description = ?, " +
                    "retail_price = ?, " +
                    "discount = ?, " +
                    "brand_id = ?, " +
                    "type_id = ? " +
                    "where id = ?";
        try (Connection con = DBConnector.getConnection()){
            PreparedStatement pstmt = con.prepareStatement(update);
            pstmt.setNString(1, product.getName());
            pstmt.setNString(2, product.getDescription());
            pstmt.setDouble(3, product.getRetailPrice());
            pstmt.setInt(4, product.getDiscount());
            pstmt.setInt(5, product.getBrandID());
            pstmt.setInt(6, product.getTypeID());
            pstmt.setInt(7, product.getId());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public boolean deleteProduct(Product product){
        if (product.getAmount() > 0) return false;

        String delete = "delete from Product " +
                    "where id = ?";
        try (Connection con = DBConnector.getConnection()){
            PreparedStatement pstmt = con.prepareStatement(delete);
            pstmt.setInt(1, product.getId());

            int n = pstmt.executeUpdate();
            if (n>0) return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public List<Product> search(int id, String name, String type, String brand, boolean discount){
        List<Product> list = new ArrayList<>();

        String query = "select * from Product, " +
                    "(select id as t_id, name as t_name from Type) as type, " +
                    "(select id as b_id, name as b_name from Brand) as brand " +
                    "where Product.type_id = type.t_id " +
                    "and Product.brand_id = brand.b_id";

        if(!(id == 0))
            query = query + " and Product.id = " + id;
        if(!name.equals(""))
            query = query + " and Product.name like " + "N'%" + name + "%'";
        if(!(type == null))
            query = query + " and type.t_name like " + "N'%" + type + "%'";
        if(!brand.equals(""))
            query = query + " and brand.b_name like " + "N'%" + brand + "%'";
        if(discount)
            query = query + " and (discount > 0 or discount is not null)";

        try (Connection con = DBConnector.getConnection()){
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
}

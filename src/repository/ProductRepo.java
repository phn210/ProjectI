package repository;

import model.entity.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}

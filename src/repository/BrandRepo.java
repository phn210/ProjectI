package repository;

import model.entity.Brand;
import model.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandRepo {
    public BrandRepo(){

    }

    public Brand getBrand(Product product){
        Brand brand = new Brand();

        String query = "select * from Brand " +
                "where id = ?";

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

    public boolean addBrand(Brand brand){
        String insert = "insert into Brand(name, country) " +
                    "values (?, ?)";

        try (Connection con = DBConnector.getConnection()){
            PreparedStatement pstmt = con.prepareStatement(insert);
            pstmt.setNString(1, brand.getName());
            pstmt.setNString(2, brand.getCountry());

            int n = pstmt.executeUpdate();
            if (n > 0) return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public boolean updateBrand(Brand brand){
        String update = "update Brand " +
                    "set name = ?," +
                    "country = ? " +
                    "where id = ?";
        try(Connection con = DBConnector.getConnection()){
            PreparedStatement pstmt = con.prepareStatement(update);
            pstmt.setNString(1, brand.getName());
            pstmt.setNString(2, brand.getCountry());
            pstmt.setInt(3, brand.getId());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }
}

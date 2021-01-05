package repository;

import model.entity.Brand;
import model.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrandRepo extends BaseRepo<Brand>{
    public BrandRepo(){

    }

    @Override
    protected Brand getObject(ResultSet rs) throws SQLException {
        Brand brand = new Brand();
        brand.setId(rs.getInt("id"));
        brand.setName(rs.getNString("name"));
        brand.setCountry(rs.getNString("country"));
        return brand;
    }

    @Override
    protected ArrayList<Brand> findAll() throws SQLException {
        String query = "select * from Brand";
        PreparedStatement pstmt = prepare(query);
        ResultSet rs = pstmt.executeQuery();
        return getList(rs);
    }

    public List<Brand> getAllBrand(){
        List<Brand> list = new ArrayList<>();

        String query = "select * from Brand";

        try (Connection con = DBConnector.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                Brand brand = new Brand();
                brand.setId(rs.getInt("id"));
                brand.setName(rs.getNString("name"));
                brand.setCountry(rs.getNString("country"));
                list.add(brand);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public List<String> getAllBrandName(){
        List<String> list = new ArrayList<>();

        String query = "select name from Brand";

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

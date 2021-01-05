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
        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }

    public List<String> getAllBrandName() throws SQLException {
        String query = "select name from Brand";
        PreparedStatement preparedStatement = prepare(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Brand> brandArrayList = getList(resultSet);
        List<String> nameArrayList = new ArrayList<>();
        for (int i = 0; i < brandArrayList.size(); i++) {
            nameArrayList.add(brandArrayList.get(i).getName());
        }
        return nameArrayList;
    }

    public Brand getBrand(Product product) throws SQLException {
        Brand brand = new Brand();
        String query = "select * from Brand " +
                "where id = ?";
        PreparedStatement pstmt = prepare(query);
        pstmt.setInt(1, product.getBrandID());
        ResultSet rs = pstmt.executeQuery();
        rs.first();
        return getObject(rs);
    }

    public boolean insertBrand(Brand brand) throws SQLException {
        String insert = "insert into Brand(name, country) " +
                    "values (?, ?)";

        PreparedStatement pstmt = prepare(insert);
        pstmt.setNString(1, brand.getName());
        pstmt.setNString(2, brand.getCountry());
        return pstmt.executeUpdate() > 0;
    }

    public boolean updateBrand(Brand brand) throws SQLException {
        String update = "update Brand " +
                    "set name = ?," +
                    "country = ? " +
                    "where id = ?";
        PreparedStatement pstmt = prepare(update);
        pstmt.setNString(1, brand.getName());
        pstmt.setNString(2, brand.getCountry());
        pstmt.setInt(3, brand.getId());
        return pstmt.executeUpdate() > 0;
    }


}

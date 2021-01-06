package repository;

import model.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepo extends BaseRepo<Product>{
    public ProductRepo(){
        
    }

    @Override
    public Product getObject(ResultSet rs) throws SQLException {
        Product product = new Product();
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

    @Override
    public ArrayList<Product> findAll() throws SQLException {
        String query = "select * from Product";
        PreparedStatement pstmt = prepare(query);
        ResultSet rs = pstmt.executeQuery();
        return getList(rs);
    }

    public Product findByID(int id) throws SQLException {
        Product product = new Product();
        String query = "select * from Product " +
                        "where id = " + id;
        PreparedStatement preparedStatement = prepare(query);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        rs.first();
        return getObject(rs);
    }

    @Override
    public int insert(Product product) throws SQLException{
        return 0;
    };

    @Override
    public int update(Product product) throws SQLException{
        return 0;
    };

    //--------------------------------------------------------------------------------
    
    public List<Product> getAllProduct() throws SQLException {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product";
        PreparedStatement stmt = prepare(query);
        ResultSet rs = stmt.executeQuery();
        return getList(rs);
    }


    


    public boolean addProduct(Product product) throws SQLException {
        String insert = "insert into Product(name, description, retail_price, " +
                        "discount, brand_id, type_id, amount) " +
                        "values (?, ?, ?, ?, ?, ?, 0)";
        PreparedStatement pstmt = prepare(insert);
        pstmt.setNString(1, product.getName());
        pstmt.setNString(2, product.getDescription());
        pstmt.setDouble(3, product.getRetailPrice());
        pstmt.setInt(4, product.getDiscount());
        pstmt.setInt(5, product.getBrandID());
        pstmt.setInt(6, product.getTypeID());
        return pstmt.executeUpdate() > 0;
    }

    public boolean updateProduct(Product product) throws SQLException {
        String update = "update Product " +
                    "set name = ?," +
                    "description = ?, " +
                    "retail_price = ?, " +
                    "discount = ?, " +
                    "brand_id = ?, " +
                    "type_id = ? " +
                    "where id = ?";
            PreparedStatement pstmt = prepare(update);
            pstmt.setNString(1, product.getName());
            pstmt.setNString(2, product.getDescription());
            pstmt.setDouble(3, product.getRetailPrice());
            pstmt.setInt(4, product.getDiscount());
            pstmt.setInt(5, product.getBrandID());
            pstmt.setInt(6, product.getTypeID());
            pstmt.setInt(7, product.getId());

           return pstmt.executeUpdate() > 0;
    }

    public boolean deleteProduct(Product product) throws SQLException {
        if (product.getAmount() > 0) return false;
        String delete = "delete from Product " +
                    "where id = ?";
        PreparedStatement pstmt = prepare(delete);
        pstmt.setInt(1, product.getId());
        return pstmt.executeUpdate()>0;
    }

    public List<Product> search(int id, String name, String type, String brand, boolean discount) throws SQLException {
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

        PreparedStatement stmt = prepare(query);
        ResultSet rs = stmt.executeQuery();
        return getList(rs);
    }


}

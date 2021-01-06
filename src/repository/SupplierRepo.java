package repository;

import model.entity.Import;
import model.entity.ImportDetail;
import model.entity.Supplier;
import model.entity.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepo extends BaseRepo<Supplier>{
    public SupplierRepo(){

    }

    @Override
    public Supplier getObject(ResultSet rs) throws SQLException {
        Supplier supplier = new Supplier();
        supplier.setId(rs.getInt("id"));
        supplier.setName(rs.getNString("name"));
        supplier.setAddress(rs.getNString("address"));
        supplier.setPhone(rs.getString("phone"));
        return supplier;
    }

    @Override
    public ArrayList<Supplier> findAll() throws SQLException {
        String query = "select * from Supplier";
        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }

    public Supplier findByID(int id) throws SQLException {
        Supplier supplier = new Supplier();
        String query = "select * from Supplier " +
                        "where id = ?";
        PreparedStatement pstmt = prepare(query);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        rs.first();
        return getObject(rs);
    }

    @Override
    public int insert(Supplier supplier) throws SQLException{
        return 0;
    };

    @Override
    public int update(Supplier supplier) throws SQLException{
        return 0;
    };




}

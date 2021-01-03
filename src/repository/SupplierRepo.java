package repository;

import model.entity.Import;
import model.entity.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepo {
    public SupplierRepo(){

    }

    public List<String> getAllSupplierName(){
        List<String> list = new ArrayList<>();

        String query = "select name from Supplier";

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

    public Supplier getSupplier(Import imprt){
        Supplier supplier = new Supplier();

        String query = "select * from Supplier " +
                "where id = ?";
        try (Connection con = DBConnector.getConnection()){
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, imprt.getSupplierID());
            ResultSet rs = pstmt.executeQuery();

            if(rs.isBeforeFirst()){
                rs.next();
                supplier.setId(rs.getInt("id"));
                supplier.setName(rs.getNString("name"));
                supplier.setAddress(rs.getNString("address"));
                supplier.setPhone(rs.getString("phone"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return supplier;
    }
}

package repository;

import model.entity.Import;
import model.entity.ImportDetail;
import model.entity.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepo {
    public SupplierRepo(){

    }

    public List<Supplier> getAllSupplier(){
        List<Supplier> list = new ArrayList<>();

        String query = "select * from Supplier";


        try (Connection con = DBConnector.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                Supplier supplier = new Supplier();
                supplier.setId(rs.getInt("id"));
                supplier.setName(rs.getNString("name"));
                supplier.setAddress(rs.getNString("address"));
                supplier.setPhone(rs.getString("phone"));
                list.add(supplier);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
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

    public String getSupplierName(ImportDetail importDetail){
        String query = "select * from (select id, name from Supplier) as supplier, " +
                    "(select id, supplier_id from Import) as import, " +
                    "(select import_id, product_id from ImportDetail) as importDetail " +
                    "where import.supplier_id = supplier.id " +
                    "and import.id = importDetail.import_id " +
                    "and import_id = ? " +
                    "and product_id = ?";
        try (Connection con = DBConnector.getConnection()){
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, importDetail.getImportID());
            pstmt.setInt(2, importDetail.getProductID());
            ResultSet rs = pstmt.executeQuery();

            if(rs.isBeforeFirst()){
                rs.next();
                return rs.getNString("supplier.name");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "";
    }
}

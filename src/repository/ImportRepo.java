package repository;

import model.entity.Import;
import model.entity.ImportDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImportRepo {
    public ImportRepo(){

    }

    public List<Import> getAllImport(){
        List<Import> list = new ArrayList<>();

        String query = "select * from import";

        try(Connection con = DBConnector.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                Import imprt = new Import();
                imprt.setId(rs.getInt("id"));
                imprt.setSupplierID(rs.getInt("supplier"));
                imprt.setImportDate(rs.getDate("import_date"));
                imprt.setTotalMoney(rs.getDouble("total_money"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public ImportDetail getImportDetail(Import imprt){
        ImportDetail importDetail = new ImportDetail();

        String query = "select * from Import_Detail " +
                    "where import_id = ?";
        try (Connection con = DBConnector.getConnection()){
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, imprt.getId());
            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {
                rs.next();
                importDetail.setImportID(rs.getInt("import_id"));
                importDetail.setProductID(rs.getInt("product_id"));
                importDetail.setAmount(rs.getInt("amount"));
                importDetail.setImportPrice(rs.getDouble("import_price"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return importDetail;
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

    public List<Import> search(int id, String supplier, Date importDate){
        List<Import> list = new ArrayList<>();

        String query = "select * from Import, (select id, name from Supplier) as supplier " +
                    "where Import.supplier_id  = supplier.id";

        if(!(id == 0))
            query = query + " and Import.id = " + id;
        if(!supplier.equals(""))
            query = query + " and supplier.name like " + "N'%" + supplier + "%'";
        if(!importDate.equals(null))
            query = query + " and type.name = " + "'%" + importDate + "%'";

        try (Connection con = DBConnector.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Import imprt = new Import();
                imprt.setId(rs.getInt("Import.id"));
                imprt.setSupplierID(rs.getInt("supplier_id"));
                imprt.setImportDate(rs.getDate("import_date"));
                imprt.setTotalMoney(rs.getDouble("total_money"));
                list.add(imprt);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}

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
}

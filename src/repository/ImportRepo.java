package repository;

import model.entity.Import;
import model.entity.ImportDetail;
import model.entity.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImportRepo extends BaseRepo<Import>{
    public ImportRepo(){

    }

    @Override
    protected Import getObject(ResultSet rs) throws SQLException {
        Import imprt = new Import();
        imprt.setId(rs.getInt("id"));
        imprt.setSupplierID(rs.getInt("supplier"));
        imprt.setImportDate(rs.getDate("import_date"));
        imprt.setTotalMoney(rs.getDouble("total_money"));
        return imprt;
    }

    @Override
    protected ArrayList<Import> findAll() throws SQLException {
        String query = "select * from import";
        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
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
                list.add(imprt);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<ImportDetail> getImportDetail(Import imprt){
        List<ImportDetail> list = new ArrayList<>();

        String query = "select * from Import_Detail " +
                    "where import_id = ?";
        try (Connection con = DBConnector.getConnection()){
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, imprt.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ImportDetail importDetail = new ImportDetail();
                importDetail.setImportID(rs.getInt("import_id"));
                importDetail.setProductID(rs.getInt("product_id"));
                importDetail.setAmount(rs.getInt("amount"));
                importDetail.setImportPrice(rs.getDouble("import_price"));
                list.add(importDetail);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public Import getImport(ImportDetail importDetail){
        Import imprt = new Import();

        String query = "select * from Import " +
                    "where id = ?";
        try (Connection con = DBConnector.getConnection()){
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, importDetail.getImportID());
            ResultSet rs = pstmt.executeQuery();

            if(rs.isBeforeFirst()){
                rs.next();
                imprt.setId(rs.getInt("id"));
                imprt.setSupplierID(rs.getInt("supplier"));
                imprt.setImportDate(rs.getDate("import_date"));
                imprt.setTotalMoney(rs.getDouble("total_money"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return imprt;
    }

    public List<Import> search(int id, String supplier, Date importDate){
        List<Import> list = new ArrayList<>();

        String query = "select * from Import, (select id as s_id, " +
                    "name as s_name from Supplier) as supplier " +
                    "where Import.supplier_id  = supplier.s_id";

        if(!(id == 0))
            query = query + " and Import.id = " + id;
        if(!(supplier == null))
            query = query + " and supplier.s_name like " + "N'%" + supplier + "%'";
        if(!(importDate == null))
            query = query + " and import_date = " + "'%" + importDate + "%'";

        try (Connection con = DBConnector.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Import imprt = new Import();
                imprt.setId(rs.getInt("id"));
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

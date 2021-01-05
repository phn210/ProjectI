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

//    public ArrayList<ImportDetail> getImportDetail(Import imprt) throws SQLException {
//        String query = "select * from Import_Detail " +
//                    "where import_id = ?";
//        PreparedStatement preparedStatement = prepare(query);
//        preparedStatement.setInt(1, imprt.getId());
//        ResultSet rs = preparedStatement.executeQuery();
//        return getList(rs);
//    }

    public Import getImport(ImportDetail importDetail) throws SQLException {
                String query = "select * from Import " +
                    "where id = ?";

        PreparedStatement preparedStatement = prepare(query);
        preparedStatement.setInt(1, importDetail.getImportID());
        ResultSet rs = preparedStatement.executeQuery();

        if(rs.isBeforeFirst()) {
            return getObject(rs);
        }
        return null;
    }

    public ArrayList<Import> search(int id, String supplier, Date importDate) throws SQLException {
        String query = "select * from Import, (select id as s_id, " +
                    "name as s_name from Supplier) as supplier " +
                    "where Import.supplier_id  = supplier.s_id";

        if(!(id == 0))
            query = query + " and Import.id = " + id;
        if(!(supplier == null))
            query = query + " and supplier.s_name like " + "N'%" + supplier + "%'";
        if(!(importDate == null))
            query = query + " and import_date = " + "'%" + importDate + "%'";

        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }


}

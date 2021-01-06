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
    public Import getObject(ResultSet rs) throws SQLException {
        Import imprt = new Import();
        imprt.setId(rs.getInt("id"));
        imprt.setSupplierID(rs.getInt("supplier"));
        imprt.setImportDate(rs.getDate("import_date"));
        imprt.setTotalMoney(rs.getDouble("total_money"));
        return imprt;
    }

    @Override
    public ArrayList<Import> findAll() throws SQLException {
        String query = "select * from import";
        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }

    public Import findByID(int id) throws SQLException {
        String query = "select * from Import " +
                        "where id = ?";
        PreparedStatement preparedStatement = prepare(query);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        rs.first();
        return getObject(rs);
    }

    @Override
    public int insert(Import anImport) throws SQLException{
        String insert = "insert into Import " +
                "values (?, ?, ?)";
        PreparedStatement preparedStatement = prepare(insert);
        preparedStatement.setInt(1, anImport.getSupplierID());
        preparedStatement.setDate(2, anImport.getImportDate());
        preparedStatement.setDouble(3, anImport.getTotalMoney());
        return preparedStatement.executeUpdate();
    };

    @Override
    public int update(Import anImport) throws SQLException{
        return 0;
    };

    public int getLastID() throws SQLException {
        String getID = "select IDENT_CURRENT('Import')";
        PreparedStatement preparedStatement = prepare(getID);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.first();
        return resultSet.getInt(0);
    }

    public List<Import> getAllImport() throws SQLException {
        String query = "select * from import";
        Statement stmt = prepare(query);
        ResultSet rs = stmt.executeQuery(query);
        return getList(rs);
    }

    public Import getImport(ImportDetail importDetail) throws SQLException {
        String query = "select * from Import " +
                    "where id = ?";
        PreparedStatement pstmt = prepare(query);
        pstmt.setInt(1, importDetail.getImportID());
        ResultSet rs = pstmt.executeQuery();
        rs.first();
        return getObject(rs);
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

        Statement stmt = prepare(query);
        ResultSet rs = stmt.executeQuery(query);
        return getList(rs);
    }
}

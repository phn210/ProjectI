package repository;

import model.entity.Import;
import model.entity.ImportDetail;
import model.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImportDetailRepo extends BaseRepo<ImportDetail>{
    @Override
    public ImportDetail getObject(ResultSet rs) throws SQLException {
        ImportDetail importDetail = new ImportDetail();
        importDetail.setImportID(rs.getInt("import_id"));
        importDetail.setProductID(rs.getInt("product_id"));
        importDetail.setAmount(rs.getInt("amount"));
        importDetail.setImportPrice(rs.getDouble("import_price"));
        return importDetail;
    }

    @Override
    public ArrayList<ImportDetail> findAll() throws SQLException {
        String query = "select * from Import_Detail";
        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }


    public ArrayList<ImportDetail> getImportDetails(Product product) throws SQLException {
        String query = "select * from Import_Detail " +
                "where product_id = ?";
        PreparedStatement preparedStatement = prepare(query);
        preparedStatement.setInt(1, product.getId());
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }

    public ArrayList<ImportDetail> getImportDetails(Import anImport) throws SQLException {
        String query = "select * from Import_Detail " +
                "where import_id = ?";
        PreparedStatement preparedStatement = prepare(query);
        preparedStatement.setInt(1, anImport.getId());
        ResultSet rs = preparedStatement.executeQuery(query);
        return getList(rs);
    }
}

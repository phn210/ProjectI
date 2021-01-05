package repository;

import model.entity.ImportDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImportDetailRepo extends BaseRepo<ImportDetail>{
    @Override
    protected ImportDetail getObject(ResultSet rs) throws SQLException {
        ImportDetail importDetail = new ImportDetail();
        importDetail.setImportID(rs.getInt("import_id"));
        importDetail.setProductID(rs.getInt("product_id"));
        importDetail.setAmount(rs.getInt("amount"));
        importDetail.setImportPrice(rs.getDouble("import_price"));
        return importDetail;
    }

    @Override
    protected ArrayList<ImportDetail> findAll() throws SQLException {
        String query = "select * from Import_Detail";
        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }
}

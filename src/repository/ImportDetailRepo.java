package repository;

import javafx.stage.Stage;
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

    @Override
    public int insert(ImportDetail importDetail) throws SQLException{
        return 0;
    };

    @Override
    public int update(ImportDetail importDetail) throws SQLException{
        return 0;
    };


    public ImportDetail findByID(int productID, int importID) throws SQLException {
        String query = "select * from Import_Detail " +
                "where product_id = ? " +
                "and import_id = ?";
        PreparedStatement preparedStatement = prepare(query);
        preparedStatement.setInt(1, productID);
        preparedStatement.setInt(2, importID);
        ResultSet rs = preparedStatement.executeQuery();
        rs.first();
        return getObject(rs);
    }

}

package repository;

import model.entity.InvoiceDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InvoiceDetailRepo extends BaseRepo<InvoiceDetail>{
    @Override
    protected InvoiceDetail getObject(ResultSet rs) throws SQLException {
        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.setInvoiceID(rs.getInt("invoice_id"));
        invoiceDetail.setProductID(rs.getInt("product_id"));
        invoiceDetail.setImportID(rs.getInt("import_id"));
        invoiceDetail.setAmount(rs.getInt("amount"));
        invoiceDetail.setRetailPrice(rs.getDouble("retail_price"));
        invoiceDetail.setDiscount(rs.getInt("discount"));
        invoiceDetail.setTotalMoney(rs.getDouble("total_money"));
        invoiceDetail.setImportPrice(rs.getDouble("import_price"));
        return invoiceDetail;
    }

    @Override
    public ArrayList<InvoiceDetail> findAll() throws SQLException {
        String query = "select * from InvoiceDetail";
        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }

    @Override
    public int insert(InvoiceDetail invoiceDetail) throws SQLException{
        String insert = "insert into Invoice_Detail " +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = prepare(insert);
        preparedStatement.setInt(1, invoiceDetail.getInvoiceID());
        preparedStatement.setInt(2, invoiceDetail.getProductID());
        preparedStatement.setInt(3, invoiceDetail.getImportID());
        preparedStatement.setInt(4, invoiceDetail.getAmount());
        preparedStatement.setDouble(5, invoiceDetail.getRetailPrice());
        preparedStatement.setInt(6, invoiceDetail.getDiscount());
        preparedStatement.setDouble(7, invoiceDetail.getTotalMoney());
        preparedStatement.setDouble(8, invoiceDetail.getImportPrice());
        return preparedStatement.executeUpdate();
    };

    @Override
    public int update(InvoiceDetail invoiceDetail) throws SQLException{
        return 0;
    };
}

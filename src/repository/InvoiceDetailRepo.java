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
    protected ArrayList<InvoiceDetail> findAll() throws SQLException {
        String query = "select * from InvoiceDetail";
        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }
}

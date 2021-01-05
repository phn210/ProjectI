package repository;

import model.entity.Invoice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InvoiceRepo extends BaseRepo<Invoice>{

    @Override
    protected Invoice getObject(ResultSet rs) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setId(rs.getInt("id"));
        invoice.setDate(rs.getDate("date"));
        invoice.setCustomerID(rs.getInt("customer_id"));
        invoice.setEmployeeID(rs.getInt("employee_id"));
        invoice.setPaymentMethod(rs.getNString("payment_method"));
        invoice.setTotalMoney(rs.getDouble("total_money"));
        invoice.setTax(rs.getDouble("tax"));
        invoice.setSurcharge(rs.getDouble("surcharge"));
        invoice.setNote(rs.getString("note"));
        return invoice;
    }

    @Override
    protected ArrayList<Invoice> findAll() throws SQLException {
        String query = "select * from Invoice";
        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }
}

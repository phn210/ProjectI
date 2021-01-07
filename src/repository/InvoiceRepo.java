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
    public ArrayList<Invoice> findAll() throws SQLException {
        String query = "select * from Invoice";
        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }

    public Invoice findByID(int id) throws SQLException {
        String query = "select * from Invoice " +
                "where id = ?";
        PreparedStatement preparedStatement = prepare(query);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        rs.first();
        return getObject(rs);
    }

    @Override
    public int insert(Invoice invoice) throws SQLException{
        String insert = "insert into Invoice " +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = prepare(insert);
        preparedStatement.setDate(1, invoice.getDate());
        preparedStatement.setInt(2, invoice.getCustomerID());
        preparedStatement.setInt(3, invoice.getEmployeeID());
        preparedStatement.setNString(4, invoice.getPaymentMethod());
        preparedStatement.setDouble(5, invoice.getTotalMoney());
        preparedStatement.setDouble(6, invoice.getTax());
        preparedStatement.setDouble(7, invoice.getSurcharge());
        preparedStatement.setString(8, invoice.getNote());
        return preparedStatement.executeUpdate();
    };

    @Override
    public int update(Invoice invoice) throws SQLException{
        return 0;
    };

    public int getLastID() throws SQLException {
        String getID = "select IDENT_CURRENT('Invoice')";
        PreparedStatement preparedStatement = prepare(getID);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.first();
        return resultSet.getInt(1);
    }
}

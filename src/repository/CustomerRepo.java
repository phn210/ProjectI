package repository;

import model.entity.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerRepo extends BaseRepo<Customer>{
    @Override
    protected Customer getObject(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setName(rs.getNString("name"));
        customer.setPhone(rs.getString("phone"));
        customer.setAddress(rs.getNString("address"));
        customer.setPoint(rs.getInt("point"));
        customer.setEmail(rs.getString("email"));
        return customer;
    }

    @Override
    protected ArrayList<Customer> findAll() throws SQLException {
        String query = "select * from Customer";
        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }
}

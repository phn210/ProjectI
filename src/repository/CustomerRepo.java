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
        customer.setEmail(rs.getString("email"));
        return customer;
    }

    @Override
    public ArrayList<Customer> findAll() throws SQLException {
        String query = "select * from Customer";
        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }

    public Customer findByID(int id) throws SQLException {
        String query = "select * from Customer " +
                "where id = " + id;
        PreparedStatement preparedStatement = prepare(query);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        rs.first();
        return getObject(rs);
    }

    @Override
    public int insert(Customer customer) throws SQLException{
        String sql = "INSERT INTO customer " +
                "VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getPhone());
        preparedStatement.setString(3, customer.getAddress());
        preparedStatement.setString(4, customer.getEmail());
        return preparedStatement.executeUpdate();
    };

    @Override
    public int update(Customer customer) throws SQLException{
        String sql = "UPDATE customer " +
                "SET name = ?, " +
                "phone = ?, " +
                "address = ?, " +
                "email = ? " +
                "WHERE id = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getPhone());
        preparedStatement.setString(3, customer.getAddress());
        preparedStatement.setString(4, customer.getEmail());
        preparedStatement.setInt(5, customer.getId());
        return preparedStatement.executeUpdate();
    };
}

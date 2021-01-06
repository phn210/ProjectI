package repository;

import model.entity.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountRepo extends BaseRepo<Account> {

    @Override
    protected Account getObject(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setEmployeeID(rs.getInt("employee_id"));
        account.setUsername(rs.getString("username"));
        account.setPassword(rs.getString("password"));
        return account;
    }

    @Override
    public ArrayList<Account> findAll() throws SQLException {
        String query = "select * from Account";
        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }

    public Account login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM Account WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            resultSet.first();
            return getObject(resultSet);
        }else{
            return null;
        }
    }

    public Account findByEmployeeId(int id) throws SQLException {
        String sql = "SELECT * FROM Account WHERE employee_id = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            resultSet.first();
            return getObject(resultSet);
        }else{
            return null;
        }
    }

    @Override
    public int insert(Account account) throws SQLException{
        String sql = "INSERT INTO Account " +
                "VALUES(?,?,?)";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1, account.getEmployeeID());
        preparedStatement.setString(2, account.getUsername());
        preparedStatement.setString(3, account.getPassword());
        return preparedStatement.executeUpdate();
    };

    @Override
    public int update(Account account) throws SQLException{
        String sql = "UPDATE Account " +
                "SET username = ?, " +
                "password = ? " +
                "WHERE employee_id = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setString(1, account.getUsername());
        preparedStatement.setString(2, account.getPassword());
        preparedStatement.setInt(3, account.getEmployeeID());
        return preparedStatement.executeUpdate();
    };
}

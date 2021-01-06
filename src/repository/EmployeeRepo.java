package repository;

import model.entity.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeRepo extends BaseRepo<Employee>{
    @Override
    protected Employee getObject(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setDob(rs.getDate("dob"));
        employee.setPhone(rs.getString("phone"));
        employee.setAddress(rs.getString("address"));
        employee.setStartDay(rs.getDate("start_day"));
        employee.setSalaryLevel(rs.getDouble("salary_level"));
        employee.setCitizenID(rs.getString("citizen_id"));
        employee.setInsuranceID(rs.getString("insurance_id"));
        employee.setRole(rs.getInt("role"));
        employee.setBranchID(rs.getInt("branch_id"));
        employee.setWorking(rs.getBoolean("working"));
        return employee;
    }

    @Override
    public ArrayList<Employee> findAll() throws SQLException {
        String sql = "SELECT * FROM Employee";
        PreparedStatement preparedStatement = prepare(sql);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }

    public Employee findById(int id) throws SQLException {
        String sql = "SELECT * FROM Employee WHERE id = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.first();
        return getObject(resultSet);
    }

    @Override
    public int insert(Employee employee) throws SQLException{
        return 0;
    };

    @Override
    public int update(Employee employee) throws SQLException{
        String sql = "UPDATE employee " +
                "SET name = ?, " +
                "dob = ?, " +
                "phone = ?, " +
                "address = ?, " +
                "salary_level = ?, " +
                "citizen_id = ?, " +
                "insurance_id = ?, " +
                "role = ?, " +
                "branch_id = ?," +
                "working = ? " +
                "WHERE id = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setDate(2, employee.getDob());
        preparedStatement.setString(3, employee.getPhone());
        preparedStatement.setString(4, employee.getAddress());
        preparedStatement.setDouble(5, employee.getSalaryLevel());
        preparedStatement.setString(6, employee.getCitizenID());
        preparedStatement.setString(7, employee.getInsuranceID());
        preparedStatement.setInt(8, employee.getRole());
        preparedStatement.setInt(9, employee.getBranchID());
        preparedStatement.setBoolean(10, employee.isWorking());
        preparedStatement.setInt(11, employee.getId());
        return preparedStatement.executeUpdate();
    };
}

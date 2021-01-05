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
        return employee;
    }

    @Override
    protected ArrayList<Employee> findAll() throws SQLException {
        String sql = "SELECT * FROM Employee";
        PreparedStatement preparedStatement = prepare(sql);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }

    public Employee findById(int id) throws SQLException {
        String sql = "SELECT * FROM Employee WHERE id = ?";
        PreparedStatement preparedStatement = prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.first();
        return getObject(resultSet);
    }
}

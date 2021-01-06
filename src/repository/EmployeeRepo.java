package repository;

import model.entity.Employee;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
        String sql = "INSERT INTO employee " +
                "VALUES(? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? )";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setDate(2, employee.getDob());
        preparedStatement.setString(3, employee.getPhone());
        preparedStatement.setString(4, employee.getAddress());
        preparedStatement.setDate(5, employee.getStartDay());
        preparedStatement.setDouble(6, employee.getSalaryLevel());
        preparedStatement.setString(7, employee.getCitizenID());
        preparedStatement.setString(8, employee.getInsuranceID());
        preparedStatement.setInt(9, employee.getRole());
        preparedStatement.setInt(10, employee.getBranchID());
        preparedStatement.setBoolean(11, employee.isWorking());
        return preparedStatement.executeUpdate();
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

    public ArrayList<Employee> findAllEmployeeWorkBeforeDate(Date date) throws SQLException {
        String sql = "SELECT * FROM employee WHERE start_day <= ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setDate(1, date);
        ResultSet resultSet = preparedStatement.executeQuery();
        return getList(resultSet);
    }

    public ArrayList<Employee> search(int id, String name, String phone, int age, String citizenId) throws SQLException {
        String sql = "SELECT * FROM employee ";
        String append = "";
        if(id != -1){
            append += " AND id = " + id + " ";
        }
        if(!name.isEmpty()){
            append += " AND name like "+"'%"+name+"%'"+" ";
        }
        if(!phone.isEmpty()){
            append += " AND phone = '" + phone + "' ";
        }
        if(age != -1){
            append += " AND YEAR(dob) = " + (LocalDate.now().getYear()-age) + " ";
        }
        if(!citizenId.isEmpty()){
            append += " AND citizen_id = '" + citizenId + "' ";
        }
        if(append.length() > 5){
            append = " WHERE " + append.substring(5,append.length());
            sql += append;
        }
        PreparedStatement preparedStatement = prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        return getList(resultSet);

    }
}

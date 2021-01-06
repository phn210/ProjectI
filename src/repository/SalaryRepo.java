package repository;

import model.entity.Salary;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryRepo extends BaseRepo<Salary>{

    @Override
    protected Salary getObject(ResultSet rs) throws SQLException {
        Salary salary = new Salary();
        salary.setEmployeeID(rs.getInt("employee_id"));
        salary.setMonth(rs.getInt("month"));
        salary.setYear(rs.getInt("year"));
        salary.setSalaryLevel(rs.getDouble("salary_level"));
        salary.setTotalHour(rs.getDouble("total_hour"));
        salary.setTotalSalary(rs.getDouble("total_salary"));
        return salary;
    }

    @Override
    public ArrayList<Salary> findAll() throws SQLException {
        String query = "select * from Salary";
        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }

    public ArrayList<Salary> findByMonthAndYear(int month, int year) throws SQLException {
        String sql = "SELECT * FROM salary WHERE month = ? AND year = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1, month);
        preparedStatement.setInt(2, year);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }

    @Override
    public int insert(Salary salary) throws SQLException{
        String sql = "INSERT INTO salary " +
                "VALUES(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1, salary.getEmployeeID());
        preparedStatement.setInt(2, salary.getMonth());
        preparedStatement.setInt(3, salary.getYear());
        preparedStatement.setDouble(4, salary.getSalaryLevel());
        preparedStatement.setDouble(5, salary.getTotalHour());
        preparedStatement.setDouble(6, salary.getTotalSalary());
        return preparedStatement.executeUpdate();
    };

    @Override
    public int update(Salary salary) throws SQLException{
        String sql = "UPDATE salary " +
                "SET total_hour = ?, " +
                "total_salary = ? " +
                "WHERE employee_id = ? AND month = ? AND year = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setDouble(1, salary.getTotalHour());
        preparedStatement.setDouble(2, salary.getTotalSalary());
        preparedStatement.setInt(3, salary.getEmployeeID());
        preparedStatement.setInt(4, salary.getMonth());
        preparedStatement.setInt(5, salary.getYear());
        return preparedStatement.executeUpdate();
    };
}

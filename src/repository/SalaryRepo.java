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
        salary.setTotalHour(rs.getFloat("total_hour"));
        salary.setTotalSalary(rs.getDouble("total_salary"));
        return salary;
    }

    @Override
    protected ArrayList<Salary> findAll() throws SQLException {
        String query = "select * from Salary";
        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }
}

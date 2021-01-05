package repository;

import model.entity.Revenue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RevenueRepo extends BaseRepo<Revenue>{
    @Override
    protected Revenue getObject(ResultSet rs) throws SQLException {
        Revenue revenue = new Revenue();
        revenue.setBranchID(rs.getInt("branch_id"));
        revenue.setMonth(rs.getInt("month"));
        revenue.setYear(rs.getInt("year"));
        revenue.setTotalIncome(rs.getDouble("total_income"));
        revenue.setFacilitiesFee(rs.getDouble("facilities_fee"));
        revenue.setTax(rs.getDouble("tax"));
        revenue.setTotalSalary(rs.getDouble("total_salary"));
        revenue.setRevenue(rs.getDouble("revenue"));
        return revenue;
    }

    @Override
    protected ArrayList<Revenue> findAll() throws SQLException {
        String query = "select * from Revenue";
        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }
}

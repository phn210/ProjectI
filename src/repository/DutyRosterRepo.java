package repository;

import model.entity.DutyRoster;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DutyRosterRepo extends BaseRepo<DutyRoster>{
    @Override
    protected DutyRoster getObject(ResultSet rs) throws SQLException {
        DutyRoster dutyRoster = new DutyRoster();
        dutyRoster.setId(rs.getInt("employee_id"));
        dutyRoster.setDate(rs.getDate("date"));
        dutyRoster.setTotalHour(rs.getFloat("total_hour"));
        dutyRoster.setNote(rs.getString("note"));
        return dutyRoster;
    }

    @Override
    public ArrayList<DutyRoster> findAll() throws SQLException {
        String query = "select * from Duty_Roster";
        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }


    @Override
    public int insert(DutyRoster dutyRoster) throws SQLException{
        String sql = "INSERT INTO Duty_Roster " +
                "VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1, dutyRoster.getId());
        preparedStatement.setDate(2, dutyRoster.getDate());
        preparedStatement.setDouble(3, dutyRoster.getTotalHour());
        preparedStatement.setString(4, dutyRoster.getNote());
        return preparedStatement.executeUpdate();
    };

    @Override
    public int update(DutyRoster dutyRoster) throws SQLException{
        String sql = "UPDATE Duty_Roster " +
                "SET total_hour = ?, " +
                "note = ? " +
                "WHERE employee_id = ? AND date = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setDouble(1, dutyRoster.getTotalHour());
        preparedStatement.setString(2, dutyRoster.getNote());
        preparedStatement.setInt(3, dutyRoster.getId());
        preparedStatement.setDate(4, dutyRoster.getDate());
        return preparedStatement.executeUpdate();
    };

    public ArrayList<DutyRoster> findByIdAndDate(int id, int month, int year) throws SQLException {
        String sql = "SELECT * FROM Duty_Roster WHERE employee_id = ? AND MONTH(date) = ? AND YEAR(DATE) = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.setInt(2, month);
        preparedStatement.setInt(3, year);
        ResultSet resultSet = preparedStatement.executeQuery();
        return getList(resultSet);
    }
}

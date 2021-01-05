package repository;

import model.entity.DutyRoster;

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
        return null;
    }

    @Override
    protected ArrayList<DutyRoster> findAll() throws SQLException {
        String query = "select * from Duty_Roster";
        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }
}

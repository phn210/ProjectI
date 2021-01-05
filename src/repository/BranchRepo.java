package repository;

import model.entity.Branch;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BranchRepo extends BaseRepo<Branch>{
    @Override
    protected Branch getObject(ResultSet rs) throws SQLException {
        Branch branch = new Branch();
        branch.setId(rs.getInt("id"));
        branch.setAddress(rs.getNString("address"));
        branch.setName(rs.getNString("name"));
        branch.setManagerID(rs.getInt("manager_id"));
        return branch;
    }

    @Override
    protected ArrayList<Branch> findAll() throws SQLException {
        String query = "select * from Branch";
        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }
}

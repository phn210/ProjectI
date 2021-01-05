package repository;

import model.entity.Product;
import model.entity.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeRepo extends BaseRepo<Type> {
    public TypeRepo() {

    }

    @Override
    protected Type getObject(ResultSet rs) throws SQLException {
        Type type = new Type();
        type.setId(rs.getInt("id"));
        type.setName(rs.getNString("name"));
        type.setDescription(rs.getNString("description"));
        return type;
    }

    @Override
    protected ArrayList<Type> findAll() throws SQLException {
        String query = "select * from Type";
        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }

    public List<Type> getAllType() throws SQLException {
        List<Type> list = new ArrayList<>();
        String query = "select * from Type";
        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery(query);
        return getList(rs);
    }

    public List<String> getAllTypeName() throws SQLException {
        String query = "select * from Type";
        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery();
        ArrayList<Type> typeArrayList = getList(rs);
        List<String> nameArrayList = new ArrayList<>();
        for (int i = 0; i < typeArrayList.size(); i++) {
            nameArrayList.add(typeArrayList.get(i).getName());
        }
        return nameArrayList;
    }

    public Type getType(Product product) throws SQLException {
        Type type = new Type();

        String query = "select * from Type " +
                "where id = ?";
        PreparedStatement pstmt = prepare(query);
        pstmt.setInt(1, product.getTypeID());
        ResultSet rs = pstmt.executeQuery();
        rs.first();
        return getObject(rs);
    }

    public boolean addType(Type type) throws SQLException {
        String insert = "insert into Type(name, description) " +
                "values (?, ?)";
        PreparedStatement preparedStatement = prepare(insert);
        preparedStatement.setNString(1, type.getName());
        preparedStatement.setNString(2, type.getDescription());
        return preparedStatement.executeUpdate() > 0;
    }

    public boolean updateType(Type type) throws SQLException {
        String update = "update Type" +
                "set name = ?," +
                "description = ? " +
                "where id = ?";
        PreparedStatement preparedStatement = prepare(update);
        preparedStatement.setNString(1, type.getName());
        preparedStatement.setNString(2, type.getDescription());
        preparedStatement.setInt(3, type.getId());
        return preparedStatement.executeUpdate() > 0;
    }


}

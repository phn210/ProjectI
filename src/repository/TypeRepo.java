package repository;

import model.entity.Product;
import model.entity.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeRepo {
    public TypeRepo(){

    }

    public List<String> getAllTypeName(){
        List<String> list = new ArrayList<>();

        String query = "select name from Type";

        try (Connection con = DBConnector.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                list.add(rs.getNString("name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public Type getType(Product product){
        Type type = new Type();

        String query = "select * from Type " +
                "where id = ?";

        try (Connection con = DBConnector.getConnection()){
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, product.getTypeID());
            ResultSet rs = pstmt.executeQuery();

            if(rs.isBeforeFirst()){
                rs.next();
                type.setId(rs.getInt("id"));
                type.setName(rs.getNString("name"));
                type.setDescription(rs.getNString("description"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return type;
    }

    public boolean addType(Type type){
        String insert = "insert into Type(name, description) " +
                    "values (?, ?)";
        try (Connection con = DBConnector.getConnection()){
            PreparedStatement pstmt = con.prepareStatement(insert);
            pstmt.setNString(1, type.getName());
            pstmt.setNString(2, type.getDescription());

            int n = pstmt.executeUpdate();
            if(n>0) return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public boolean updateType(Type type){
        String update = "update Type" +
                    "set name = ?," +
                    "description = ? " +
                    "where id = ?";
        try (Connection con = DBConnector.getConnection()){
            PreparedStatement pstmt = con.prepareStatement(update);
            pstmt.setNString(1, type.getName());
            pstmt.setNString(2, type.getDescription());
            pstmt.setInt(3, type.getId());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }
}

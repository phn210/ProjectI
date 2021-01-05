package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class BaseRepo<T> {
    protected PreparedStatement prepare(String sql){
        try {
            System.out.println(">> "+sql);
            //connection.prepareStatment trả về đối tượng PrepareStatment dùng để thực hiện query String sql
            return DBConnector.connection.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //ResultSet.TYPE_SCROLL_SENSITIVE: cho phép con trỏ resultSet chạy từ bản ghi đầu đến cuối.
            //ResultSet.CONCUR_UPDATABLE: tạo ra một đuối tượng resultSet có thể được cập nhập.
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    protected abstract T getObject(ResultSet rs) throws SQLException;

    protected ArrayList<T> getList(ResultSet rs) throws SQLException{
        ArrayList<T> data = new ArrayList<>();
        while(rs.next()){
            data.add(getObject(rs));
        }
        return data;
    }

    public abstract ArrayList<T> findAll() throws SQLException;
    public abstract  int insert(T t) throws SQLException;
    public abstract  int update(T t) throws SQLException;
}

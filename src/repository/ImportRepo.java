package repository;

import model.entity.Import;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImportRepo {
    public ImportRepo(){

    }

    public List<Import> getAllImport(){
        List<Import> list = new ArrayList<>();

        String query = "select * from import";

        try(Connection con = DBConnector.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                Import imprt = new Import();
                imprt.setId(rs.getInt("id"));
                imprt.setSupplierID(rs.getInt("supplier"));
                imprt.setImportDate(rs.getDate("import_date"));
                imprt.setTotalMoney(rs.getDouble("total_money"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }
}

package repository;

import model.entity.Import;
import model.entity.ImportDetail;
import model.entity.Supplier;
import model.entity.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepo extends BaseRepo<Supplier>{
    public SupplierRepo(){

    }

    @Override
    protected Supplier getObject(ResultSet rs) throws SQLException {
        Supplier supplier = new Supplier();
        supplier.setId(rs.getInt("id"));
        supplier.setName(rs.getNString("name"));
        supplier.setAddress(rs.getNString("address"));
        supplier.setPhone(rs.getString("phone"));
        return supplier;
    }

    @Override
    protected ArrayList<Supplier> findAll() throws SQLException {
        String query = "select * from Supplier";
        PreparedStatement preparedStatement = prepare(query);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }

    public List<Supplier> getAllSupplier() throws SQLException {
        String query = "select * from Supplier";
        Statement stmt = prepare(query);
        ResultSet rs = stmt.executeQuery(query);
        return getList(rs);
    }

    public List<String> getAllSupplierName() throws SQLException {
        String query = "select name from Supplier";
        Statement stmt = prepare(query);
        ResultSet rs = stmt.executeQuery(query);
        ArrayList<Supplier> typeArrayList = getList(rs);
        List<String> nameArrayList = new ArrayList<>();
        for (int i = 0; i < typeArrayList.size(); i++) {
            nameArrayList.add(typeArrayList.get(i).getName());
        }
        return nameArrayList;
    }

    public Supplier getSupplier(Import imprt) throws SQLException {
        Supplier supplier = new Supplier();
        String query = "select * from Supplier " +
                "where id = ?";
        PreparedStatement pstmt = prepare(query);
        pstmt.setInt(1, imprt.getSupplierID());
        ResultSet rs = pstmt.executeQuery();
        supplier.setId(rs.getInt("id"));
        supplier.setName(rs.getNString("name"));
        supplier.setAddress(rs.getNString("address"));
        supplier.setPhone(rs.getString("phone"));
        return supplier;
    }

    public String getSupplierName(ImportDetail importDetail) throws SQLException {
        String query = "select * from (select id, name from Supplier) as supplier, " +
                    "(select id, supplier_id from Import) as import, " +
                    "(select import_id, product_id from ImportDetail) as importDetail " +
                    "where import.supplier_id = supplier.id " +
                    "and import.id = importDetail.import_id " +
                    "and import_id = ? " +
                    "and product_id = ?";
        PreparedStatement pstmt =prepare(query);
        pstmt.setInt(1, importDetail.getImportID());
        pstmt.setInt(2, importDetail.getProductID());
        ResultSet rs = pstmt.executeQuery();
        return "";
    }


}

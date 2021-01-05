package repository;

import model.entity.Invoice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InvoiceRepo extends BaseRepo<Invoice>{

    @Override
    protected Invoice getObject(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    protected ArrayList<Invoice> findAll() throws SQLException {
        return null;
    }
}

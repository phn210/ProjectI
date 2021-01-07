package model.form;

import model.entity.Import;
import model.entity.ImportDetail;
import model.entity.Supplier;

import java.sql.Date;
import java.util.ArrayList;

public class ImportForm {
    private int id;
    private String supplier;
    private Date importDate;
    private Double totalMoney;
    private ArrayList<ImportDetail> importDetails;

    public ImportForm() {
        this.importDetails = new ArrayList<>();
    }

    public ImportForm(Import anImport, Supplier supplier) {
        this.setId(anImport.getId());
        this.setSupplier(supplier.getName());
        this.setImportDate(anImport.getImportDate());
        this.setTotalMoney(anImport.getTotalMoney());
        this.importDetails = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public ArrayList<ImportDetail> getImportDetails() {
        return importDetails;
    }

    public void setImportDetails(ArrayList<ImportDetail> importDetails) {
        this.importDetails = importDetails;
    }

    public Object[] getFields() {
        return new Object[]{getId(), getSupplier(), getImportDate(), getTotalMoney()};
    }
}

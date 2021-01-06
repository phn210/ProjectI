package service.invoice;

import model.entity.*;
import model.form.InvoiceDetailForm;
import model.form.InvoiceForm;
import repository.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class InvoicesService {
    public InvoiceRepo invoiceRepo;
    public InvoiceDetailRepo invoiceDetailRepo;
    public CustomerRepo customerRepo;
    public EmployeeRepo employeeRepo;
    public BranchRepo branchRepo;
    public ProductRepo productRepo;
    public ImportDetailRepo importDetailRepo;

    public InvoicesService(){
        this.invoiceRepo = new InvoiceRepo();
        this.invoiceDetailRepo = new InvoiceDetailRepo();
        this.customerRepo = new CustomerRepo();
        this.employeeRepo = new EmployeeRepo();
        this.branchRepo = new BranchRepo();
        this.productRepo = new ProductRepo();
        this.importDetailRepo = new ImportDetailRepo();
    }

    public ArrayList<InvoiceForm> getAllInvoiceForm() throws SQLException, NullPointerException {
        ArrayList<Invoice> invoices = invoiceRepo.findAll();
        ArrayList<InvoiceForm> invoiceForms = new ArrayList<>();
        for(Invoice invoice: invoices){
            Customer customer = customerRepo.findByID(invoice.getCustomerID());
            Employee employee = employeeRepo.findById(invoice.getEmployeeID());
            Branch branch = branchRepo.findById(employee.getBranchID());
            InvoiceForm invoiceForm = new InvoiceForm(invoice, customer, employee, branch);
            invoiceForms.add(invoiceForm);
        }
        return invoiceForms;
    }

    public ArrayList<InvoiceDetailForm> getAllInvoiceDetailForm() throws SQLException {
        ArrayList<InvoiceDetail> invoiceDetails = invoiceDetailRepo.findAll();
        ArrayList<InvoiceDetailForm> invoiceDetailForms = new ArrayList<>();
        for(InvoiceDetail invoiceDetail: invoiceDetails){
            Product product = productRepo.findByID(invoiceDetail.getProductID());
            InvoiceDetailForm invoiceDetailForm = new InvoiceDetailForm(invoiceDetail, product);
            invoiceDetailForms.add(invoiceDetailForm);
        }
        return invoiceDetailForms;
    }

    public ArrayList<Integer> getImportIDs(int productID) throws SQLException {
        ArrayList<ImportDetail> importDetails = importDetailRepo.findAll();
        ArrayList<Integer> importIDs = new ArrayList<>();
        for(ImportDetail importDetail: importDetails){
            importIDs.add(importDetailRepo.findByProductID(productID).getImportID());
        }
        return importIDs;
    }
}

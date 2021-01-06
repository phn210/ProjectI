package service.invoice;

import model.entity.*;
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

    public InvoicesService(){
        this.invoiceRepo = new InvoiceRepo();
        this.invoiceDetailRepo = new InvoiceDetailRepo();
        this.customerRepo = new CustomerRepo();
        this.employeeRepo = new EmployeeRepo();
        this.branchRepo = new BranchRepo();
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

}

package model.form;

import javafx.scene.control.Accordion;
import model.entity.Account;
import model.entity.Branch;
import model.entity.Employee;

public class EmployeeDetailForm {
    private Employee employee;
    private Branch branch;
    private Account account;

    public EmployeeDetailForm(Employee employee, Branch branch, Account account) {
        this.employee = employee;
        this.branch = branch;
        this.account = account;
    }

    public EmployeeDetailForm() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

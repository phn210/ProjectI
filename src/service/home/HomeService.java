package service.home;

import model.entity.Account;
import model.entity.Branch;
import model.entity.Employee;
import model.form.EmployeeDetailForm;
import repository.AccountRepo;
import repository.BranchRepo;
import repository.EmployeeRepo;

import java.sql.SQLException;

public class HomeService {
    AccountRepo accountRepo;
    EmployeeRepo employeeRepo;
    BranchRepo branchRepo;

    public HomeService() {
        accountRepo = new AccountRepo();
        employeeRepo = new EmployeeRepo();
        branchRepo = new BranchRepo();
    }

    public EmployeeDetailForm getEmployeeDetail(Account account) throws SQLException {
        Employee employee = employeeRepo.findById(account.getEmployeeID());
        Branch branch = branchRepo.findById(employee.getBranchID());
        EmployeeDetailForm employeeDetailForm = new EmployeeDetailForm(employee, branch, account);
        return employeeDetailForm;
    }
}

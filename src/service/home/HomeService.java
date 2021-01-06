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
        System.out.println(employee.getBranchID());
        System.out.println(employee.getId());
        System.out.println(employee.getName());
        Branch branch = branchRepo.findById(employee.getBranchID());
        EmployeeDetailForm employeeDetailForm = new EmployeeDetailForm(employee, branch);
        return employeeDetailForm;
    }
}

package service.employee;

import model.entity.Account;
import model.entity.Branch;
import model.entity.Employee;
import model.form.EmployeeDetailForm;
import repository.AccountRepo;
import repository.BranchRepo;
import repository.EmployeeRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeService {
    EmployeeRepo employeeRepo;
    BranchRepo branchRepo;
    AccountRepo accountRepo;

    public EmployeeService(){
        employeeRepo = new EmployeeRepo();
        branchRepo = new BranchRepo();
        accountRepo = new AccountRepo();
    }

    public ArrayList<EmployeeDetailForm> getAllEmployee() throws SQLException {
        ArrayList<Employee> employeeArrayList = employeeRepo.findAll();
        ArrayList<EmployeeDetailForm> employeeDetailFormArrayList = new ArrayList<>();
        for (int i = 0; i < employeeArrayList.size(); i++) {
            Employee employee = employeeArrayList.get(i);
            Branch branch = branchRepo.findById(employee.getBranchID());
            EmployeeDetailForm employeeDetailForm = new EmployeeDetailForm(employee, branch);
            employeeDetailFormArrayList.add(employeeDetailForm);
        }
        return employeeDetailFormArrayList;
    }

    public ArrayList<String> getAllBranchName() throws SQLException {
        ArrayList<Branch> branchArrayList = branchRepo.findAll();
        ArrayList<String> branchNameArrayList = new ArrayList<>();
        for (int i = 0; i < branchArrayList.size(); i++) {
            branchNameArrayList.add(branchArrayList.get(i).getName());
        }
        return branchNameArrayList;
    }

    public ArrayList<Branch> getAllBranch() throws SQLException {
        return branchRepo.findAll();
    }

    public void updateEmployee(Employee employee) throws SQLException {
        employeeRepo.update(employee);
    }

    public Account getAccount(int id) throws SQLException {
        return accountRepo.findByEmployeeId(id);
    }

    public Branch getBranchById(int id) throws SQLException {
        return branchRepo.findById(id);
    }

    public void registerAccount(Account account) throws SQLException {
        accountRepo.insert(account);
    }

    public void updateAccount(Account account) throws SQLException {
        accountRepo.update(account);
    }

    public void addEmployee(Employee employee) throws SQLException {
        employeeRepo.insert(employee);
    }
}

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

    public ArrayList<EmployeeDetailForm> getAllEmployees() throws SQLException {
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
}

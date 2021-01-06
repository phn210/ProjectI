package service.duty_roster;

import model.entity.DutyRoster;
import model.entity.Employee;
import model.entity.Salary;
import model.form.DutyRosterDetailForm;
import model.form.SalaryDetailForm;
import repository.DutyRosterRepo;
import repository.EmployeeRepo;
import repository.SalaryRepo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class DutyRosterService {
    DutyRosterRepo dutyRosterRepo;
    EmployeeRepo employeeRepo;
    SalaryRepo salaryRepo;

    public DutyRosterService() {
        dutyRosterRepo = new DutyRosterRepo();
        employeeRepo = new EmployeeRepo();
        salaryRepo = new SalaryRepo();
    }

    public ArrayList<DutyRosterDetailForm> getAllDutyRosterDetailForm() throws SQLException {
        ArrayList<DutyRoster> dutyRosterArrayList = dutyRosterRepo.findAll();
        ArrayList<DutyRosterDetailForm> dutyRosterDetailFormArrayList = new ArrayList<>();
        for (int i = 0; i < dutyRosterArrayList.size(); i++) {
            DutyRoster dutyRoster = dutyRosterArrayList.get(i);
            dutyRosterDetailFormArrayList.add(toDutyRosterDetailForm(dutyRoster));
        }
        return  dutyRosterDetailFormArrayList;
    }

    public void addDutyRoster(DutyRoster dutyRoster) throws SQLException {
        dutyRosterRepo.insert(dutyRoster);
    }

    public void updateDutyRoster(DutyRoster dutyRoster) throws SQLException {
        dutyRosterRepo.update(dutyRoster);
    }

    public DutyRosterDetailForm toDutyRosterDetailForm(DutyRoster dutyRoster) throws SQLException {
        int employeeId = dutyRoster.getId();
        Employee employee = employeeRepo.findById(employeeId);
        DutyRosterDetailForm dutyRosterDetailForm = new DutyRosterDetailForm(dutyRoster, employee.getName());
        return dutyRosterDetailForm;
    }

    public DutyRoster toDutyRoster(DutyRosterDetailForm dutyRosterDetailForm){
        DutyRoster dutyRoster = new DutyRoster();
        dutyRoster.setId(dutyRosterDetailForm.getEmployeeId());
        dutyRoster.setDate(dutyRosterDetailForm.getDate());
        dutyRoster.setNote(dutyRosterDetailForm.getNote());
        dutyRoster.setTotalHour(dutyRosterDetailForm.getTotalHours());
        return dutyRoster;
    }

    public Employee getEmployee(int id) throws SQLException {
        return employeeRepo.findById(id);
    }

    public ArrayList<SalaryDetailForm> getAllSalaryDetail(Date date) throws SQLException {
        ArrayList<Salary> salaryArrayList = salaryRepo.findByMonthAndYear(date.getMonth(), date.getYear());
        ArrayList<Employee> employeeArrayList = employeeRepo.findAllEmployeeWorkBeforeDate(date);
        ArrayList<SalaryDetailForm> salaryDetailFormArrayList = new ArrayList<>();
        for (int i = 0; i < employeeArrayList.size(); i++) {
            Employee employee = employeeArrayList.get(i);
            if(!include(salaryArrayList, employee)){
                Salary salary = new Salary();
                salary.setEmployeeID(employee.getId());
                salary.setMonth(date.getMonth());
                salary.setYear(date.getYear());
                salary.setSalaryLevel(employee.getSalaryLevel());
                ArrayList<DutyRoster> dutyRosterArrayList = dutyRosterRepo.findByIdAndDate(employee.getId(), date.getMonth(), date.getYear());
                double totalHours = 0;
                for (int j = 0; j < dutyRosterArrayList.size(); j++) {
                    totalHours += dutyRosterArrayList.get(i).getTotalHour();
                }
                salary.setTotalHour(totalHours);
                salary.setTotalSalary(totalHours*salary.getSalaryLevel());
                salaryRepo.insert(salary);
                salaryArrayList.add(salary);
            }
        }
        for (int i = 0; i < salaryArrayList.size(); i++) {
            salaryDetailFormArrayList.add(toSalaryDetailForm(salaryArrayList.get(i)));
        }
        return salaryDetailFormArrayList;
    }

    public SalaryDetailForm toSalaryDetailForm(Salary salary) throws SQLException {
        Employee employee = employeeRepo.findById(salary.getEmployeeID());
        SalaryDetailForm salaryDetailForm = new SalaryDetailForm(salary, employee.getName());
        return salaryDetailForm;
    }

    public boolean include(ArrayList<Salary> salaryArrayList, Employee employee){
        for (int i = 0; i < salaryArrayList.size(); i++) {
            if(salaryArrayList.get(i).getEmployeeID() == employee.getId()){
                return true;
            }
        }
        return false;
    }
}

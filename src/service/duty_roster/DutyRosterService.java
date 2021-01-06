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
import java.sql.Savepoint;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

    public ArrayList<SalaryDetailForm> getAllSalaryDetail(LocalDate localDate) throws SQLException {
        ArrayList<Salary> salaryArrayList = salaryRepo.findByMonthAndYear(localDate.getMonthValue(), localDate.getYear());
        ArrayList<Employee> employeeArrayList = employeeRepo.findAllEmployeeWorkBeforeDate(Date.valueOf(localDate));
        ArrayList<SalaryDetailForm> salaryDetailFormArrayList = new ArrayList<>();
        for (int i = 0; i < salaryArrayList.size(); i++) {
            Salary salary = salaryArrayList.get(i);
            ArrayList<DutyRoster> dutyRosterArrayList = dutyRosterRepo.findByIdAndDate(salary.getEmployeeID(), salary.getMonth(), salary.getYear());
            double newTotalHour = 0;
            for (int j = 0; j < dutyRosterArrayList.size(); j++) {
                newTotalHour += dutyRosterArrayList.get(j).getTotalHour();
            }
            salary.setTotalHour(newTotalHour);
            salary.setTotalSalary(newTotalHour*salary.getSalaryLevel());
            salaryRepo.update(salary);
            salaryArrayList.set(i, salary);
        }
        for (int i = 0; i < employeeArrayList.size(); i++) {
            Employee employee = employeeArrayList.get(i);
            if(!include(salaryArrayList, employee)){
                Salary salary = new Salary();
                salary.setEmployeeID(employee.getId());
                salary.setMonth(localDate.getMonthValue());
                salary.setYear(localDate.getYear());
                salary.setSalaryLevel(employee.getSalaryLevel());
                ArrayList<DutyRoster> dutyRosterArrayList = dutyRosterRepo.findByIdAndDate(employee.getId(), localDate.getMonthValue(), localDate.getYear());
                double totalHours = 0;
                for (int j = 0; j < dutyRosterArrayList.size(); j++) {
                    totalHours += dutyRosterArrayList.get(j).getTotalHour();
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

    public double amountOfMoneyToBePaid(int month, int year) throws SQLException {
        double result = 0;
        Calendar calendar = new GregorianCalendar(year, month-1, 1);
        int numberOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        LocalDate localDate = LocalDate.of(year, month, numberOfDays);
        ArrayList<SalaryDetailForm> salaryDetailFormArrayList = getAllSalaryDetail(localDate);
        for (int i = 0; i < salaryDetailFormArrayList.size(); i++) {
            result += salaryDetailFormArrayList.get(i).getTotalSalary();
        }
        return result;
    }

}

package service.duty_roster;

import model.entity.DutyRoster;
import model.entity.Employee;
import model.form.DutyRosterDetailForm;
import repository.DutyRosterRepo;
import repository.EmployeeRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class DutyRosterService {
    DutyRosterRepo dutyRosterRepo;
    EmployeeRepo employeeRepo;

    public DutyRosterService() {
        dutyRosterRepo = new DutyRosterRepo();
        employeeRepo = new EmployeeRepo();
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

    private DutyRosterDetailForm toDutyRosterDetailForm(DutyRoster dutyRoster) throws SQLException {
        int employeeId = dutyRoster.getId();
        Employee employee = employeeRepo.findById(employeeId);
        DutyRosterDetailForm dutyRosterDetailForm = new DutyRosterDetailForm(dutyRoster, employee.getName());
        return dutyRosterDetailForm;
    }

    private DutyRoster toDutyRoster(DutyRosterDetailForm dutyRosterDetailForm){
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
}

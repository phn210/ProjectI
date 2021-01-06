package model.form;

import model.entity.DutyRoster;

import java.sql.Date;

public class DutyRosterDetailForm {
    private int employeeId;
    private Date date;
    private double totalHours;
    private String note;
    private String name;

    public DutyRosterDetailForm() {
    }

    public DutyRosterDetailForm(DutyRoster dutyRoster, String name) {
        employeeId = dutyRoster.getId();
        date = dutyRoster.getDate();
        totalHours = dutyRoster.getTotalHour();
        note = dutyRoster.getNote();
        this.name = name;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

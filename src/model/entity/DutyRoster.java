package model.entity;

import java.sql.Date;

public class DutyRoster {
    private int id;
    private Date date;
    private float totalHour;
    private String note;

    public DutyRoster(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(float totalHour) {
        this.totalHour = totalHour;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

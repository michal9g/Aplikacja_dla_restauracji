package client.tableView;

public class ScheduleTable {
    int id, shift;
    String firstname, lastname, date;

    public ScheduleTable(int id, String firstname, String lastname, int shift, String date) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.shift = shift;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

package messages;

import java.io.Serializable;

public class ScheduleMessage implements Serializable {
    int id, shift;
    String firstname, lastname, date;

    public ScheduleMessage(int id, String firstname, String lastname, int shift, String date) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.shift = shift;
        this.date = date;
    }

    public ScheduleMessage(int id, int shift, String date) {
        this.id = id;
        this.shift = shift;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getShift() {
        return shift;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getDate() {
        return date;
    }
}

package messages;

import java.io.Serializable;

public class WorkTimeMessage implements Serializable {
    String firstname,lastname,start,stop, sum;
    int id;

    public WorkTimeMessage(int id, String start, String stop){
        this.id = id;
        this.start = start;
        this.stop = stop;
    }
    public WorkTimeMessage(String sum){
        this.sum = sum;
    }
    public WorkTimeMessage(int id, String firstname, String lastname, String start, String stop) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.start = start;
        this.stop = stop;
    }
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getStart() {
        return start;
    }

    public String getStop() {
        return stop;
    }

    public String getSum() {
        return sum;
    }

    public int getId() {
        return id;
    }
}

package messages;

import java.io.Serializable;

public class CustomersMessage implements Serializable {
    String lastname, firstname, phonenumber;
    int id;

    public CustomersMessage(int id, String firstname, String lastname, String phonenumber) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
    }

    public CustomersMessage(String firstname, String lastname, String phonenumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public int getId() {
        return id;
    }
}

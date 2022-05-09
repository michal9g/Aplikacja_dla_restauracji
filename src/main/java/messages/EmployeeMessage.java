package messages;

import java.io.Serializable;

public class EmployeeMessage implements Serializable {
    int id;

    public EmployeeMessage(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}

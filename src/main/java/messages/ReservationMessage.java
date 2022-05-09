package messages;

import java.io.Serializable;

public class ReservationMessage implements Serializable {
    int id, userId;
    String date, table_num, num_of_people;

    public ReservationMessage(int id, String date, String table_num, String num_of_people) {
        this.id = id;
        this.date = date;
        this.table_num = table_num;
        this.num_of_people = num_of_people;
    }

    public ReservationMessage(int id, int userId, String date, String table_num, String num_of_people) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.table_num = table_num;
        this.num_of_people = num_of_people;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTable_num() {
        return table_num;
    }

    public String getNum_of_people() {
        return num_of_people;
    }
}

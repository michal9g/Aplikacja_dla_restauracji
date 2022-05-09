package client.tableView;

public class ReservationTable {
    int id;
    String date, table_num, num_of_people;

    public ReservationTable(int id, String date, String table_num, String num_of_people) {
        this.id = id;
        this.date = date;
        this.table_num = table_num;
        this.num_of_people = num_of_people;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTable_num() {
        return table_num;
    }

    public void setTable_num(String table_num) {
        this.table_num = table_num;
    }

    public String getNum_of_people() {
        return num_of_people;
    }

    public void setNum_of_people(String num_of_people) {
        this.num_of_people = num_of_people;
    }
}

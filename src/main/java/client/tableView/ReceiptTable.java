package client.tableView;

public class ReceiptTable {
    String value_n, value_b,type_of_payment,date_rec;
    int id;

    public ReceiptTable(int id, String value_n, String value_b, String type_of_payment, String date_rec) {
        this.id = id;
        this.value_n = value_n;
        this.value_b = value_b;
        this.type_of_payment = type_of_payment;
        this.date_rec = date_rec;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getValue_n() {
        return value_n;
    }
    public void setValue_n(String value_n) {
        this.value_n = value_n;
    }
    public String getValue_b() {
        return value_b;
    }
    public void setValue_b(String value_b) {
        this.value_b = value_b;
    }
    public String getType_of_payment() {
        return type_of_payment;
    }
    public void setType_of_payment(String type_of_payment) {
        this.type_of_payment = type_of_payment;
    }
    public String getDate_rec() {
        return date_rec;
    }
    public void setDate_rec(String date_rec) {
        this.date_rec = date_rec;
    }
}

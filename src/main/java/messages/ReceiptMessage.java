package messages;

import java.io.Serializable;

public class ReceiptMessage implements Serializable {
    String value_n, value_b,type_of_payment,date_rec, start, stop;
    int idRec;

    public ReceiptMessage(int id, String value_n, String value_b, String type_of_payment, String date_rec) {
        this.idRec = id;
        this.value_n = value_n;
        this.value_b = value_b;
        this.type_of_payment = type_of_payment;
        this.date_rec = date_rec;
    }
    public ReceiptMessage(String start, String stop){
        this.start = start;
        this.stop = stop;
    }

    public String getValue_n() {
        return value_n;
    }

    public String getValue_b() {
        return value_b;
    }

    public String getType_of_payment() {
        return type_of_payment;
    }

    public String getDate_rec() {
        return date_rec;
    }

    public int getIdRec() {
        return idRec;
    }

    public String getStart() {
        return start;
    }

    public String getStop() {
        return stop;
    }
}

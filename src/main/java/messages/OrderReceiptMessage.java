package messages;

import java.io.Serializable;

public class OrderReceiptMessage implements Serializable {
    int userId, orderId;
    float value_n, value_b, discount;
    String type_of_payment, date;

    public OrderReceiptMessage(int userId, int orderId, float value_n, float value_b, float discount, String type_of_payment, String date) {
        this.userId = userId;
        this.orderId = orderId;
        this.value_n = value_n;
        this.value_b = value_b;
        this.discount = discount;
        this.type_of_payment = type_of_payment;
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public float getValue_n() {
        return value_n;
    }

    public float getValue_b() {
        return value_b;
    }

    public float getDiscount() {
        return discount;
    }

    public String getType_of_payment() {
        return type_of_payment;
    }

    public String getDate() {
        return date;
    }
}

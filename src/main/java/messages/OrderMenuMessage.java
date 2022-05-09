package messages;

import java.io.Serializable;

public class OrderMenuMessage implements Serializable {
    String name, at_location;
    int number, table_num, id;
    float price, sum;

    public OrderMenuMessage(int table_num, float sum, String at_location) {
        this.table_num = table_num;
        this.sum = sum;
        this.at_location = at_location;
    }
    public OrderMenuMessage(int id) {
        this.id = id;
    }
    public OrderMenuMessage(String name, int number, float price) {
        this.name = name;
        this.number = number;
        this.price = price;
    }
    public OrderMenuMessage(int id, int number) {
        this.id = id;
        this.number = number;
    }
    public OrderMenuMessage(String at_location,int id, int table_num) {
        this.at_location = at_location;
        this.id = id;
        this.table_num =table_num;
    }
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public float getPrice() {
        return price;
    }

    public float getSum() {
        return sum;
    }

    public String getAt_location() {
        return at_location;
    }

    public int getTable_num() {
        return table_num;
    }

    @Override
    public String toString() {
        return  "Numer stolika = \t" + table_num + '\n' +
                "Czy na miejscu = \t" + at_location + '\n';
    }
}

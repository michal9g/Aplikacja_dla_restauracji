package client.tableView;

public class OrderTable {
    String name_pos;
    int number;
    float price;

    public OrderTable(String name_pos, int number, float price) {
        this.name_pos = name_pos;
        this.number = number;
        this.price = price;
    }

    public OrderTable(String name_pos, int number) {
        this.name_pos = name_pos;
        this.number = number;
    }

    public String getName_pos() {
        return name_pos;
    }

    public void setName_pos(String name_pos) {
        this.name_pos = name_pos;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

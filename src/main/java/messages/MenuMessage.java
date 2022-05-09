package messages;

import java.io.Serializable;

public class MenuMessage implements Serializable {
    int id;
    float price;
    String name,type;

    public MenuMessage(int id, String name, String type, float price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public MenuMessage(String name, String type, float price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public MenuMessage(int id, float price) {
        this.id = id;
        this.price = price;
    }

    public MenuMessage(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}

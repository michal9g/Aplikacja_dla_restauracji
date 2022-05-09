package messages;

import java.io.Serializable;

public class EditOrderMessage implements Serializable {
    int idOrder, number, idMenu;

    public EditOrderMessage(int idOrder, int number, int idMenu) {
        this.idOrder = idOrder;
        this.number = number;
        this.idMenu = idMenu;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public int getNumber() {
        return number;
    }

    public int getIdMenu() {
        return idMenu;
    }
}

package client;

import client.tableView.OrderTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import messages.MenuMessage;
import messages.OrderMenuMessage;
import messages.ServerOperation;

import java.util.LinkedList;

public class AddOrderController {
    @FXML
    private TableView<OrderTable> orderTable;
    @FXML
    private TextField numberTextField;
    @FXML
    private TextField tableNumberTextField;
    @FXML
    private TableColumn<OrderTable, Integer> nameOrderTable;
    @FXML
    private TableColumn<OrderTable, String> numberOrderTable;
    @FXML
    private Label messageLabel;
    @FXML
    private ComboBox<String> nameMenuBox;
    @FXML
    private ComboBox<String> locationBox;

    LinkedList<OrderMenuMessage> inListOrder = new LinkedList<>();
    LinkedList<MenuMessage> inList = new LinkedList<>();
    ObservableList<OrderTable> oblist = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        inList = (LinkedList<MenuMessage>)
                ServerConnection.sendToServer.send(ServerOperation.getMenu, null);
        nameMenuBox.getItems().clear();
        for(MenuMessage menu : inList) {
            nameMenuBox.getItems().add(menu.getName());
        }
        locationBox.getItems().addAll("Tak", "Nie");
        locationBox.getSelectionModel().select(0);
        nameMenuBox.getSelectionModel().select(0);
    }

    public void addButtonOnAction(){
        if(numberTextField.getText().isEmpty()) {
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Podaj ilość!");
        }else if(!isNumeric(numberTextField.getText())){
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Ilość musi być liczbą!");
        }else {
            showOrderTable();
            int menuId = 0;
            for (MenuMessage r : inList) {
                if (r.getName().equals(nameMenuBox.getValue())) {
                    menuId = r.getId();
                }
            }
            inListOrder.add(new OrderMenuMessage(menuId, Integer.parseInt(numberTextField.getText())));
        }
    }

    public void showOrderTable() {
        oblist.add(new OrderTable(nameMenuBox.getValue(),Integer.parseInt(numberTextField.getText())));
        nameOrderTable.setCellValueFactory(new PropertyValueFactory<>("name_pos"));
        numberOrderTable.setCellValueFactory(new PropertyValueFactory<>("number"));
        orderTable.setItems(oblist);
    }

    public void createOrderButtonOnAction(){
        if(inListOrder.isEmpty()){
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Nie można stworzyć pustego zamówienia!");
        }else{
            if(tableNumberTextField.getText().isEmpty()) {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Podaj numer stolika!");
            }else if (!isNumeric(tableNumberTextField.getText())){
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Numer stolika musi być liczbą!");
            }else{
                createOrder();
            }
        }
    }

    public void createOrder(){
        inListOrder.add(new OrderMenuMessage(locationBox.getValue(),LoginController.userId,
                Integer.parseInt(tableNumberTextField.getText())));
        Boolean bool = (Boolean) ServerConnection.sendToServer.send(ServerOperation.createOrder, inListOrder);
        if (!bool) {
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Nie udało sie stworzyć zamówienia!");
        } else {
            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("Poprawnie stworzono zamówienie!");
        }
    }

    public void clearButtonOnAction(){
        inListOrder.clear();
        oblist.clear();
        messageLabel.setText(" ");
        tableNumberTextField.clear();
        numberTextField.clear();
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}

package client;

import client.tableView.OrderTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import messages.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class OrderController {

    @FXML
    private ComboBox<String> nameMenuBox;
    @FXML
    private TextField discountTextField;
    @FXML
    private TextArea detailsArea;
    @FXML
    private TextField numberTextField;
    @FXML
    private ComboBox<Integer> idOrderBox;
    @FXML
    private ComboBox<String> typeBox;
    @FXML
    private ComboBox<Integer> idOrderEditBox;
    @FXML
    private TableView<OrderTable> orderTable;
    @FXML
    private TableColumn<OrderTable, Integer> nameOrderTable;
    @FXML
    private TableColumn<OrderTable, String> numberOrderTable;
    @FXML
    private TableColumn<OrderTable, String> priceOrderTable;
    @FXML
    private Label SumLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private Label messageEditLabel;

    SceneController scene = new SceneController();
    ObservableList<OrderTable> oblist = FXCollections.observableArrayList();
    LinkedList<MenuMessage> inList = new LinkedList<>();

    @FXML
    public void initialize() {
        setBox();
    }

    public void setBox() {
        inList = (LinkedList<MenuMessage>)
                ServerConnection.sendToServer.send(ServerOperation.getMenu, null);
        nameMenuBox.getItems().clear();
        for(MenuMessage menu : inList) {
            nameMenuBox.getItems().add(menu.getName());
        }
        idOrderBox.getItems().clear();
        idOrderEditBox.getItems().clear();
        LinkedList<OrderMenuMessage> inListOrder = (LinkedList<OrderMenuMessage>)
                ServerConnection.sendToServer.send(ServerOperation.getOrdersId, null);
        while (!inListOrder.isEmpty()) {
            OrderMenuMessage ordersId = inListOrder.pop();
            idOrderEditBox.getItems().add(ordersId.getId());
            idOrderBox.getItems().add(ordersId.getId());
        }
        typeBox.getItems().addAll("Karta", "Gotowka");
        typeBox.getSelectionModel().select(0);
        nameMenuBox.getSelectionModel().select(0);
        idOrderBox.getSelectionModel().select(0);
        idOrderEditBox.getSelectionModel().select(0);
    }

    public void showOrderTable() {
        oblist.clear();
        OrderMenuMessage message = new OrderMenuMessage(idOrderBox.getValue());
        LinkedList<OrderMenuMessage> inList = (LinkedList<OrderMenuMessage>)
                ServerConnection.sendToServer.send(ServerOperation.getOrder, message);
        while (!inList.isEmpty()) {
            OrderMenuMessage r = inList.pop();
            oblist.add(new OrderTable(r.getName(), r.getNumber(), r.getPrice()));
        }

        nameOrderTable.setCellValueFactory(new PropertyValueFactory<>("name_pos"));
        numberOrderTable.setCellValueFactory(new PropertyValueFactory<>("number"));
        priceOrderTable.setCellValueFactory(new PropertyValueFactory<>("price"));
        orderTable.setItems(oblist);
    }

    public void detailsButtonOnAction() {
        showOrderTable();
        OrderMenuMessage message = new OrderMenuMessage(idOrderBox.getValue());
        OrderMenuMessage messageDetails = (OrderMenuMessage) ServerConnection.sendToServer.send(ServerOperation.getOrderDetails,
                message);
        detailsArea.appendText(messageDetails.toString());
        SumLabel.setText("Do zapłaty: " + messageDetails.getSum());
    }

    public void newOrderButtonOnAction(){
        scene.openAddOrderWindow();
    }

    public void generateReceiptButtonOnAction() {
        if (idOrderBox.getValue() == null) {
            messageLabel.setTextFill(Color.TOMATO);
            messageLabel.setText("Nie wybrano numeru zamówienia!");
        } else{
            Date date = new Date();
            SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
            OrderMenuMessage message = new OrderMenuMessage(idOrderBox.getValue());
            OrderMenuMessage messageDetails = (OrderMenuMessage) ServerConnection.sendToServer.send(ServerOperation.getOrderDetails,
                message);
            double value_nN = messageDetails.getSum() / 1.23;
            float value_n = (float) value_nN;
            OrderReceiptMessage message1 = new OrderReceiptMessage(LoginController.userId, idOrderBox.getValue(), value_n,
                messageDetails.getSum(), Float.parseFloat(discountTextField.getText()), typeBox.getValue(), dataFormat.format(date));
            Boolean bool = (Boolean) ServerConnection.sendToServer.send(ServerOperation.generateReceipt, message1);
            if (!bool) {
                messageLabel.setTextFill(Color.TOMATO);
                messageLabel.setText("Nie udało sie poprawnie rozliczyć!");
            } else {
                messageLabel.setTextFill(Color.GREEN);
                messageLabel.setText("Poprawnie rozliczono zamówienie!");
            }
        }
    }

    public void editOrderButtonOnAction(){
        if(numberTextField.getText().isEmpty()) {
            messageEditLabel.setTextFill(Color.RED);
            messageEditLabel.setText("Podaj ilość!");
        }else if(!isNumeric(numberTextField.getText())){
            messageEditLabel.setTextFill(Color.RED);
            messageEditLabel.setText("Ilość musi być liczbą!");
        }else {
            int menuId = 0;
            for (MenuMessage r : inList) {
                if (r.getName().equals(nameMenuBox.getValue())) {
                    menuId = r.getId();
                }
            }
            EditOrderMessage message = new EditOrderMessage(idOrderEditBox.getValue(), Integer.parseInt(numberTextField.getText()),
                    menuId);
            Boolean bool = (Boolean)ServerConnection.sendToServer.send(ServerOperation.editOrder, message);
            if (!bool) {
                messageEditLabel.setTextFill(Color.RED);
                messageEditLabel.setText("Błąd edycji!");
            } else {
                messageEditLabel.setTextFill(Color.GREEN);
                messageEditLabel.setText("Poprawnie edytowano!");
            }
        }
    }

    public void clearText(){
        messageLabel.setText("");
        messageEditLabel.setText("");
        setBox();
    }

    public void returnButtonOnAction(ActionEvent event){
        scene.switchToHomePage(event);
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

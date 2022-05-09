package client;

import client.tableView.CustomersTable;
import client.tableView.ReservationTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import messages.CustomersMessage;
import messages.ReservationMessage;
import messages.ServerOperation;

import java.util.LinkedList;

public class ReservationController{

    @FXML
    private TextField idTextField;
    @FXML
    private DatePicker dateTextField;
    @FXML
    private TextField tableTextField;
    @FXML
    private TextField numTextField;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private TableView<CustomersTable> customersTable;
    @FXML
    private TableColumn<CustomersTable, Integer> idCustomersTable;
    @FXML
    private TableColumn<CustomersTable, String> nameCustomersTable;
    @FXML
    private TableColumn<CustomersTable, String> lastnameCustomersTable;
    @FXML
    private TableColumn<CustomersTable, String> phoneCustomersTable;
    @FXML
    private TableView<ReservationTable> reservationTable;
    @FXML
    private TableColumn<ReservationTable, Integer> idReservationTable;
    @FXML
    private TableColumn<ReservationTable, String> numberTableReservationTable;
    @FXML
    private TableColumn<ReservationTable, String> numberReservationTable;
    @FXML
    private TableColumn<ReservationTable, String> dateReservationTable;
    @FXML
    private ComboBox<String> hourBox;
    @FXML
    private ComboBox<String> minuteBox;

    SceneController scene = new SceneController();
    ObservableList<CustomersTable> oblist = FXCollections.observableArrayList();
    ObservableList<ReservationTable> oblistRes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        hourBox.getItems().addAll("8","9","10","11","12","13","14","15","16","17","18","19","20","21","22");
        hourBox.getSelectionModel().select("16");
        minuteBox.getItems().addAll("00","10","15","20","25","30","35","40","45","50","55");
        minuteBox.getSelectionModel().select("30");
        showCustomersTable();
        showReservationTable();
    }

    public void addReservationButtonOnAction(){

        if(idTextField.getText().isEmpty() || dateTextField.getValue() == null
                || tableTextField.getText().isEmpty() || numTextField.getText().isEmpty()) {
            errorMessageLabel.setText("Podaj wszystkie dane!");
        }else if (!isNumeric(idTextField.getText())){
            errorMessageLabel.setText("ID musi być liczbą!");
        }else if (!isNumeric(tableTextField.getText()) || !isNumeric(numTextField.getText())){
            errorMessageLabel.setText("Dane muszą być liczbą!");
        }else{
                addReservation();
            }
        }

    public void showReservationTable(){
        LinkedList<ReservationMessage> inList = (LinkedList<ReservationMessage>)
                ServerConnection.sendToServer.send(ServerOperation.getReservation, null);
        while(!inList.isEmpty()) {
            ReservationMessage reservation = inList.pop();
            oblistRes.add(new ReservationTable(reservation.getId(), reservation.getDate(),
                    reservation.getTable_num(), reservation.getNum_of_people()));
        }

        idReservationTable.setCellValueFactory(new PropertyValueFactory<>("id"));
        numberTableReservationTable.setCellValueFactory(new PropertyValueFactory<>("table_num"));
        numberReservationTable.setCellValueFactory(new PropertyValueFactory<>("num_of_people"));
        dateReservationTable.setCellValueFactory(new PropertyValueFactory<>("date"));
        reservationTable.setItems(oblistRes);
    }

    public void addReservation(){
        String date = dateTextField.getValue().toString();
        String dateHM = date + " " + hourBox.getValue() +":"+ minuteBox.getValue();
        ReservationMessage reservation = new ReservationMessage(Integer.parseInt(idTextField.getText()),LoginController.userId,dateHM,
                tableTextField.getText(), numTextField.getText());
        Boolean bool = (Boolean) ServerConnection.sendToServer.send(ServerOperation.addReservation,reservation);
        if (!bool) {
            errorMessageLabel.setTextFill(Color.RED);
            errorMessageLabel.setText("Nie udało sie dodać rezerwacji!");
        } else {
            errorMessageLabel.setTextFill(Color.GREEN);
            errorMessageLabel.setText("Poprawnie dodano rezerwację!");
        }
        oblistRes.clear();
        showReservationTable();
    }

    public void addCustomerButtonOnAction(){
        scene.openAddCustomerWindow();
    }

    public void showCustomersTable(){
        oblist.clear();
        LinkedList<CustomersMessage> inList = (LinkedList<CustomersMessage>)
                ServerConnection.sendToServer.send(ServerOperation.getCustomers, null);
        while(!inList.isEmpty()) {
            CustomersMessage customers = inList.pop();
            oblist.add(new CustomersTable(customers.getId(), customers.getFirstname(),
                    customers.getLastname(), customers.getPhonenumber()));
        }

        idCustomersTable.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCustomersTable.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastnameCustomersTable.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        phoneCustomersTable.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        customersTable.setItems(oblist);
    }

    public void clearButtonOnAction(){
        idTextField.setText("");
        dateTextField.getEditor().clear();
        numTextField.setText("");
        tableTextField.setText("");
        errorMessageLabel.setText(" ");
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

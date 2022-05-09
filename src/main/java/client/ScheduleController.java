package client;

import client.tableView.ScheduleTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import messages.EmployeeMessage;
import messages.ScheduleMessage;
import messages.ServerOperation;

import java.util.LinkedList;

public class ScheduleController {

    @FXML
    private DatePicker dateTextField;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private ComboBox<Integer> shiftBox;
    @FXML
    private ComboBox<Integer> employeeBox;
    @FXML
    private TableView<ScheduleTable> scheduleTable;
    @FXML
    private TableColumn<ScheduleTable, Integer> idScheduleTable;
    @FXML
    private TableColumn<ScheduleTable, String> nameScheduleTable;
    @FXML
    private TableColumn<ScheduleTable, String> lastnameScheduleTable;
    @FXML
    private TableColumn<ScheduleTable, Integer> shiftScheduleTable;
    @FXML
    private TableColumn<ScheduleTable, String> dateScheduleTable;

    SceneController scene = new SceneController();
    ObservableList<ScheduleTable> oblist = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        shiftBox.getItems().addAll(1,2);
        employeeBox.getItems().clear();
        LinkedList<EmployeeMessage> inList = (LinkedList<EmployeeMessage>) ServerConnection.sendToServer.send(ServerOperation.getEmployee, null);
        showScheduleTable();
        while(!inList.isEmpty()) {
            EmployeeMessage employee = inList.pop();
            employeeBox.getItems().add(employee.getId());
        }
    }

    public void addButtonOnAction(){
        if(dateTextField.getValue() == null){
            errorMessageLabel.setText("Podaj wszystkie dane!");
        }else{
            addSchedule();
        }
    }

    public void addSchedule(){
        String date = dateTextField.getValue().toString();
        ScheduleMessage message = new ScheduleMessage(employeeBox.getValue(), shiftBox.getValue(), date);
        Boolean bool = (Boolean) ServerConnection.sendToServer.send(ServerOperation.addSchedule, message );
        if (!bool) {
            errorMessageLabel.setTextFill(Color.RED);
            errorMessageLabel.setText("Nie udało sie dodać pozycji!");
        } else {
            errorMessageLabel.setTextFill(Color.GREEN);
            errorMessageLabel.setText("Poprawnie dodano pozycji!");
        }
        showScheduleTable();
    }

    public void showScheduleTable(){
        oblist.clear();
        LinkedList<ScheduleMessage> inList = (LinkedList<ScheduleMessage>)
                ServerConnection.sendToServer.send(ServerOperation.getSchedule, null);
        while(!inList.isEmpty()) {
            ScheduleMessage r = inList.pop();
            oblist.add(new ScheduleTable(r.getId(), r.getFirstname(), r.getLastname(), r.getShift(), r.getDate()));
        }

        idScheduleTable.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameScheduleTable.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastnameScheduleTable.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        shiftScheduleTable.setCellValueFactory(new PropertyValueFactory<>("shift"));
        dateScheduleTable.setCellValueFactory(new PropertyValueFactory<>("date"));
        scheduleTable.setItems(oblist);
    }

    public void clearText(){
        errorMessageLabel.setText("");
    }

    public void returnButtonOnAction(ActionEvent event){
        scene.switchToHomePage(event);
    }
}

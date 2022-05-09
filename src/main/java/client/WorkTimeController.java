package client;

import client.tableView.WorkTimeTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import messages.EmployeeMessage;
import messages.ServerOperation;
import messages.WorkTimeMessage;

import java.util.LinkedList;

public class WorkTimeController{

    @FXML
    private DatePicker startDateTextField;
    @FXML
    private DatePicker stopDateTextField;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private Label workingTimeMessageLabel;
    @FXML
    private TableView<WorkTimeTable> workTimeTable;
    @FXML
    private TableColumn<WorkTimeTable, String> idWorkTimeTable;
    @FXML
    private TableColumn<WorkTimeTable, String> nameWorkTimeTable;
    @FXML
    private TableColumn<WorkTimeTable, String> lastnameWorkTimeTable;
    @FXML
    private TableColumn<WorkTimeTable, String> startWorkTimeTable;
    @FXML
    private TableColumn<WorkTimeTable, String> stopWorkTimeTable;
    @FXML
    private ComboBox<Integer> employeeBox;

    ObservableList<WorkTimeTable> oblist = FXCollections.observableArrayList();
    SceneController scene = new SceneController();

    @FXML
    public void initialize() {
        employeeBox.getItems().clear();
        LinkedList<EmployeeMessage> inList = (LinkedList<EmployeeMessage>)
                ServerConnection.sendToServer.send(ServerOperation.getEmployee, null);
        while(!inList.isEmpty()) {
            EmployeeMessage employee = inList.pop();
            employeeBox.getItems().add(employee.getId());
        }
    }

    public void searchButtonOnAction(){

        if(startDateTextField.getValue() == null || stopDateTextField.getValue() == null ) {
            errorMessageLabel.setText("Podaj wszystkie dane!");
        } else {
            oblist.clear();
            showWorkTimeTable();
            workingTimeCounting();
        }
    }

    public void showWorkTimeTable(){
        oblist.clear();
        String start = startDateTextField.getValue().toString();
        String stop = stopDateTextField.getValue().toString();
        WorkTimeMessage wt = new WorkTimeMessage(employeeBox.getValue(), start, stop);
        LinkedList<WorkTimeMessage> inList = (LinkedList<WorkTimeMessage>) ServerConnection.sendToServer.send(ServerOperation.getWorkTimeList, wt);
        while(!inList.isEmpty()) {
            WorkTimeMessage workTime = inList.pop();
            oblist.add(new WorkTimeTable(workTime.getId(), workTime.getFirstname(), workTime.getLastname(),
                    workTime.getStart(), workTime.getStop()));
        }
        idWorkTimeTable.setCellValueFactory(new PropertyValueFactory<>("account_id"));
        nameWorkTimeTable.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastnameWorkTimeTable.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        startWorkTimeTable.setCellValueFactory(new PropertyValueFactory<>("start"));
        stopWorkTimeTable.setCellValueFactory(new PropertyValueFactory<>("stop"));
        workTimeTable.setItems(oblist);
    }

    public void workingTimeCounting(){
        String start = startDateTextField.getValue().toString();
        String stop = stopDateTextField.getValue().toString();
        WorkTimeMessage wt = new WorkTimeMessage(employeeBox.getValue(), start, stop);
        WorkTimeMessage message = (WorkTimeMessage) ServerConnection.sendToServer.send(ServerOperation.getWorkTime, wt);
        workingTimeMessageLabel.setText("Przepracowany czas w zadanym okresie: "+message.getSum()+" H");
    }

    public void clearButtonOnAction(){
        startDateTextField.getEditor().clear();
        stopDateTextField.getEditor().clear();
        errorMessageLabel.setText(" ");
        oblist.clear();
    }

    public void returnButtonOnAction(ActionEvent event){
        scene.switchToHomePage(event);
    }

}

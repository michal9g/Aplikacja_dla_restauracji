package client;

import client.tableView.ReceiptTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import messages.ReceiptMessage;
import messages.ReceiptReportMessage;
import messages.ServerOperation;

import java.util.LinkedList;

public class ReceiptController{

    @FXML
    private Label errorMessageLabel;
    @FXML
    private DatePicker startDateTextField;
    @FXML
    private DatePicker stopDateTextField;
    @FXML
    private TextArea reportArea;
    @FXML
    private TableView<ReceiptTable> receiptTable;
    @FXML
    private TableColumn<ReceiptTable, Integer> idReceiptTable;
    @FXML
    private TableColumn<ReceiptTable, String> vnReceiptTable;
    @FXML
    private TableColumn<ReceiptTable, String> vbReceiptTable;
    @FXML
    private TableColumn<ReceiptTable, String> typeReceiptTable;
    @FXML
    private TableColumn<ReceiptTable, String> dateReceiptTable;

    ObservableList<ReceiptTable> oblist = FXCollections.observableArrayList();
    SceneController scene = new SceneController();

    public void generateButtonOnAction(){

        if(startDateTextField.getValue() == null
                || stopDateTextField.getValue() == null ) {
            errorMessageLabel.setText("Podaj wszystkie dane!");
        }else {
            oblist.clear();
            showReceiptTable();
            generateReport();
        }
    }

    public void showReceiptTable(){
        oblist.clear();
        String start = startDateTextField.getValue().toString();
        String stop = stopDateTextField.getValue().toString();
        ReceiptMessage rec = new ReceiptMessage(start, stop);
        LinkedList<ReceiptMessage> inList = (LinkedList<ReceiptMessage>) ServerConnection.sendToServer.send(ServerOperation.getReceipt, rec);
        while(!inList.isEmpty()) {
            ReceiptMessage message = inList.pop();
            oblist.add(new ReceiptTable(message.getIdRec(), message.getValue_n(), message.getValue_b(), message.getType_of_payment(),
                    message.getDate_rec()));
        }

        idReceiptTable.setCellValueFactory(new PropertyValueFactory<>("id"));
        vnReceiptTable.setCellValueFactory(new PropertyValueFactory<>("value_n"));
        vbReceiptTable.setCellValueFactory(new PropertyValueFactory<>("value_b"));
        typeReceiptTable.setCellValueFactory(new PropertyValueFactory<>("type_of_payment"));
        dateReceiptTable.setCellValueFactory(new PropertyValueFactory<>("date_rec"));

        receiptTable.setItems(oblist);
    }

    public void generateReport(){
        String start = startDateTextField.getValue().toString();
        String stop = stopDateTextField.getValue().toString();
        ReceiptReportMessage receiptReport = new ReceiptReportMessage(start, stop);
        ReceiptReportMessage message = (ReceiptReportMessage) ServerConnection.sendToServer.send(ServerOperation.getReceiptReport, receiptReport);
        reportArea.appendText(message.toString());
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
package client;

import client.tableView.MenuTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import messages.MenuMessage;
import messages.ServerOperation;

import java.util.LinkedList;

public class MenuController{

    @FXML
    private TableView<MenuTable> menuTable;
    @FXML
    private TableColumn<MenuTable, Integer> idMenuTable;
    @FXML
    private TableColumn<MenuTable, String> nameMenuTable;
    @FXML
    private TableColumn<MenuTable, String> typeMenuTable;
    @FXML
    private TableColumn<MenuTable, Float> priceMenuTable;
    @FXML
    private TextField priceTextField;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private ComboBox<Integer> idBox;

    SceneController scene = new SceneController();
    ObservableList<MenuTable> oblist = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setBox();
        showMenuTable();
    }

    public void setBox(){
        idBox.getItems().clear();
        LinkedList<MenuMessage> inList = (LinkedList<MenuMessage>) ServerConnection.sendToServer.send(ServerOperation.getMenu, null);
        while(!inList.isEmpty()) {
            MenuMessage menu = inList.pop();
            idBox.getItems().add(menu.getId());
        }
        idBox.getSelectionModel().select(0);
    }

    public void showMenuTable(){
        oblist.clear();
        errorMessageLabel.setText("");
        LinkedList<MenuMessage> inList = (LinkedList<MenuMessage>)
                ServerConnection.sendToServer.send(ServerOperation.getMenu, null);
        while(!inList.isEmpty()) {
            MenuMessage r = inList.pop();
            oblist.add(new MenuTable(r.getId(), r.getName(), r.getType(), r.getPrice()));
        }

        idMenuTable.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameMenuTable.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeMenuTable.setCellValueFactory(new PropertyValueFactory<>("type"));
        priceMenuTable.setCellValueFactory(new PropertyValueFactory<>("price"));
        menuTable.setItems(oblist);
    }

    public void deleteButtonOnAction(){
        MenuMessage menu = new MenuMessage(idBox.getValue());
        Boolean bool = (Boolean) ServerConnection.sendToServer.send(ServerOperation.deleteMenu, menu);
        oblist.clear();
        showMenuTable();
        setBox();
        if (!bool) {
            errorMessageLabel.setTextFill(Color.RED);
            errorMessageLabel.setText("Nie udało sie usunąć!");
        } else {
            errorMessageLabel.setTextFill(Color.GREEN);
            errorMessageLabel.setText("Poprawnie usunięto!");
        }
    }

    public void addMenuButtonOnAction(){
       scene.openAddMenuWindow();
    }

    public void editMenuButtonOnAction(){
        if (priceTextField.getText().isEmpty()) {
            errorMessageLabel.setText("Podaj wszystkie dane!");
        } else {
            MenuMessage menu = new MenuMessage(idBox.getValue(),Float.parseFloat(priceTextField.getText()));
            Boolean bool = (Boolean) ServerConnection.sendToServer.send(ServerOperation.editMenu, menu);
            System.out.println(idBox.getValue());
            if (!bool) {
                errorMessageLabel.setTextFill(Color.RED);
                errorMessageLabel.setText("Nie udało sie edytowac!");
            } else {
                errorMessageLabel.setTextFill(Color.GREEN);
                errorMessageLabel.setText("Poprawnie edytowano!");
            }
        }
    }

    public void returnButtonOnAction(ActionEvent event){
        scene.switchToHomePage(event);
    }
}

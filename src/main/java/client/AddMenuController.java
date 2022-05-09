package client;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import messages.MenuMessage;
import messages.ServerOperation;

public class AddMenuController {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField typeTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private Label errorMessageLabel;

    public void addMenuButtonOnAction() {
        if (nameTextField.getText().isEmpty() || typeTextField.getText().isEmpty()
                || priceTextField.getText().isEmpty()) {
            errorMessageLabel.setText("Podaj wszystkie dane!");
        } else {
            MenuMessage menu = new MenuMessage(nameTextField.getText(), typeTextField.getText(),
                    Float.parseFloat(priceTextField.getText()));
            Boolean bool = (Boolean) ServerConnection.sendToServer.send(ServerOperation.addMenu, menu);
            if (!bool) {
                errorMessageLabel.setTextFill(Color.RED);
                errorMessageLabel.setText("Nie udało sie dodać pozycji!");
            } else {
                errorMessageLabel.setTextFill(Color.GREEN);
                errorMessageLabel.setText("Poprawnie dodano pozycji!");
            }
        }
    }
}
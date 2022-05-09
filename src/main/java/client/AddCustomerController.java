package client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import messages.CustomersMessage;
import messages.ServerOperation;

public class AddCustomerController {
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private Label errorMessageLabel;

    public void addCustomerButtonOnAction() {
        if (firstnameTextField.getText().isEmpty() || lastnameTextField.getText().isEmpty()
                || phoneTextField.getText().isEmpty()) {
            errorMessageLabel.setText("Podaj wszystkie dane!");
        } else {
            CustomersMessage message = new CustomersMessage(firstnameTextField.getText(), lastnameTextField.getText(),
                    phoneTextField.getText());
            Boolean bool = (Boolean) ServerConnection.sendToServer.send(ServerOperation.addCustomer, message);
            if (!bool) {
                errorMessageLabel.setTextFill(Color.RED);
                errorMessageLabel.setText("Nie udało sie dodać klienta!");
            } else {
                errorMessageLabel.setTextFill(Color.GREEN);
                errorMessageLabel.setText("Poprawnie dodano klienta!");
            }
        }
    }
}

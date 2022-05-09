package client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import messages.LoginMessage;
import messages.ServerOperation;

public class LoginController{

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterpasswordField;

    SceneController scene = new SceneController();
    static public String username;
    static public int userId;

    public void loginButtonAction(){
        if(usernameTextField.getText().isEmpty() && enterpasswordField.getText().isEmpty()){
            loginMessageLabel.setText("Błędny login lub hasło!");
        }
        else{
            validateLogin();
        }
    }

    public void cancelButtonOnAction(){
        ServerConnection.sendToServer.send(ServerOperation.disconnect, null);
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin(){
        username = usernameTextField.getText();

            LoginMessage messageOut = new LoginMessage(username, enterpasswordField.getText());
            LoginMessage message = (LoginMessage) ServerConnection.sendToServer.send(ServerOperation.verifyLogin, messageOut);
            if(message.getVerNum() == 1){
                    userId = message.getUserId();
                    scene.openHomePage();
                    Stage stage = (Stage) cancelButton.getScene().getWindow();
                    stage.close();
               }else {
                    loginMessageLabel.setText("Nieprawidłowe dane!");
                }
    }
}

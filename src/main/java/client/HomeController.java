package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import messages.LogoutMessage;
import messages.ServerOperation;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeController{

    @FXML
    private Button logoutButton;
    @FXML
    private Label dataLabel;
    @FXML
    private Label userLabel;

    SceneController scene = new SceneController();
    Date Startdate;
    Date Stopdate;

    @FXML
    public void initialize() {
        Startdate = new Date();
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy");
        dataLabel.setText("Data: "+dataFormat.format(Startdate));
        userLabel.setText("Użytkownik: "+LoginController.username);
    }

    public void logoutButtonOnAction(){
        Stopdate = new Date();
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        LogoutMessage mess = new LogoutMessage(dataFormat.format(Startdate),dataFormat.format(Stopdate),LoginController.userId);
        ServerConnection.sendToServer.send(ServerOperation.logout, mess);
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
        scene.loginPage();
    }

    public void menuButtonOnAction(ActionEvent event){ scene.switchToMenu(event);
    }

    public void scheduleButtonOnAction(ActionEvent event){ scene.switchToSchedule(event);
    }

    public void orderButtonOnAction(ActionEvent event){ scene.switchToOrder(event);
    }

    public void reservationButtonOnAction(ActionEvent event){ scene.switchToReservation(event);
    }

    public void receiptButtonOnAction(ActionEvent event){ scene.switchToReceipt(event);
    }

    public void workTimeButtonOnAction(ActionEvent event){ scene.switchToWorkTime(event);
    }
}

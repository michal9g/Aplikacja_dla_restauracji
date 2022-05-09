package client;

import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SceneController{

    public void openHomePage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("graphics/home.fxml"));
            Stage homeStage = new Stage();
            homeStage.setTitle("Ciekawa");
            homeStage.setScene(new Scene(root, 770, 500));
            homeStage.getIcons().add(new Image("image/cafe.png"));
            homeStage.setResizable(false);
            homeStage.show();
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void loginPage(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("graphics/login.fxml"));
            Stage loginStage = new Stage();
            loginStage.initStyle(StageStyle.UNDECORATED);
            loginStage.setScene(new Scene(root, 520, 400));
            loginStage.getIcons().add(new Image("image/cafe.png"));
            loginStage.show();
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void switchToHomePage(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("graphics/home.fxml"));
            Scene homePage = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(homePage);
            appStage.show();
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void switchToMenu(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("graphics/menu.fxml"));
            Scene menuPage = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(menuPage);
            appStage.show();
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void switchToSchedule(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("graphics/schedule.fxml"));
            Scene schedulePage = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(schedulePage);
            appStage.show();
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void switchToOrder(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("graphics/order.fxml"));
            Scene orderPage = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(orderPage);
            appStage.show();
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void switchToReservation(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("graphics/reservation.fxml"));
            Scene reservationPage = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(reservationPage);
            appStage.show();
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void switchToReceipt(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("graphics/receipt.fxml"));
            Scene receiptPage = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(receiptPage);
            appStage.show();
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void switchToWorkTime(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("graphics/worktime.fxml"));
            Scene worktimePage = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(worktimePage);
            appStage.show();
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void openAddMenuWindow() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("graphics/addMenu.fxml"));
            Stage addMenuStage = new Stage();
            addMenuStage.setScene(new Scene(root, 372, 300));
            addMenuStage.getIcons().add(new Image("image/cafe.png"));
            addMenuStage.setResizable(false);
            addMenuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void openAddCustomerWindow() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("graphics/addCustomer.fxml"));
            Stage addCustomerStage = new Stage();
            addCustomerStage.setScene(new Scene(root, 372, 300));
            addCustomerStage.getIcons().add(new Image("image/cafe.png"));
            addCustomerStage.setResizable(false);
            addCustomerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void openAddOrderWindow() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("graphics/addOrder.fxml"));
            Stage addOrderStage = new Stage();
            addOrderStage.setScene(new Scene(root, 515, 395));
            addOrderStage.getIcons().add(new Image("image/cafe.png"));
            addOrderStage.setResizable(false);
            addOrderStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("server.fxml"));
        primaryStage.setTitle("Ciekawa - serwer");
        primaryStage.setScene(new Scene(root, 570, 350));
        primaryStage.getIcons().add(new Image("image/cafe.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

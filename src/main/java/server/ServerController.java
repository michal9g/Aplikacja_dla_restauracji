package server;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {

    int port = 1234;
    ServerSocket server;

    @FXML
    private TextArea statusText;
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;

    @FXML
    public void initialize() {

        stopButton.setDisable(true);

    }

    public void startButtonOnAction() {
        Thread thread = new Thread(() -> {
            statusText.appendText("Serwer wystartował: port - " + port+"\n");
            statusText.appendText("Serwer w trybie nasłuchiwania... \n");
            startServer();
        });
        thread.start();
        startButton.setDisable(true);
        stopButton.setDisable(false);
    }

    public void stopButtonOnAction() {
        Thread thread = new Thread(() -> {
            statusText.appendText("Serwer wstrzymany\n");
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stopButton.setDisable(true);
            startButton.setDisable(false);
        });
        thread.start();
    }

    public void startServer() {

        try {
            server = new ServerSocket(port);

            while (true) {
                Socket client = server.accept();
                 statusText.appendText("Nowy klient: \n" + InetAddress.getLocalHost()+" połączył się" +
                        " z serwem\n");

                ClientHandler clientSock = new ClientHandler(client);
                new Thread(clientSock).start();
            }
        } catch
        (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch
                (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnection {

    public static SendToServer sendToServer;

    public void ServerConn() {
        Socket socket;
        try {
            socket = new Socket("localhost", 1234);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            sendToServer = new SendToServer(out,in);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

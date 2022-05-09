package client;

import messages.ServerOperation;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SendToServer {

    ObjectOutputStream out;
    ObjectInputStream in;

    public SendToServer(ObjectOutputStream out,ObjectInputStream in) {
        this.out = out;
        this.in = in;
    }

    public Object send(ServerOperation serverOperation, Object object) {
        try {
            out.writeObject(serverOperation.toString());
            out.writeObject(object);
            return in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

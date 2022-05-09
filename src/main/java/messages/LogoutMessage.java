package messages;

import java.io.Serializable;

public class LogoutMessage implements Serializable {
    String dataStart, dataStop;
    int userId;
    public LogoutMessage(String dataStart, String dataStop, int userId){
        this.dataStart = dataStart;
        this.dataStop = dataStop;
        this.userId = userId;
    }

    public String getDataStart() {
        return dataStart;
    }

    public String getDataStop() {
        return dataStop;
    }

    public int getUserId() {
        return userId;
    }

}

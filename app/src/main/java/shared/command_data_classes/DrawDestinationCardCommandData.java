package shared.command_data_classes;

/**
 * Created by erics on 3/7/2017.
 */

public class DrawDestinationCardCommandData {
    private String destinationCardName;
    private int playerID;
    private String auth;

    public DrawDestinationCardCommandData() {

    }

    public String getDestinationCardName() {
        return destinationCardName;
    }

    public void setDestinationCardName(String destinationCardName) {
        this.destinationCardName = destinationCardName;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}

package shared.command_data_classes;

/**
 * Created by erics on 3/6/2017.
 */

public class EndTurnCommandData {
    private int gameID;

    public EndTurnCommandData() {

    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}

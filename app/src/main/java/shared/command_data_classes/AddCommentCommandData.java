package shared.command_data_classes;

/**
 * Created by erics on 3/6/2017.
 */

public class AddCommentCommandData {

    private String message;
    private String auth;
    private int gameID;

    public AddCommentCommandData() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}

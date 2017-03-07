package shared.command_data_classes;

/**
 * Created by erics on 3/6/2017.
 */

public class GetGamesCommandData {
    private String auth;

    public GetGamesCommandData() {

    }

    //getters
    public String getAuth() {
        return auth;
    }

    //setters
    public void setAuth(String auth) {
        this.auth = auth;
    }
}

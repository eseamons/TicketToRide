package shared.command_data_classes;

/**
 * Created by erics on 3/6/2017.
 */

public class RemoveDestinationCardCommandData {
    private String destinationCardName;
    private String auth;

    public RemoveDestinationCardCommandData() {

    }

    public String getDestinationCardName() {
        return destinationCardName;
    }

    public void setDestinationCardName(String destinationCardName) {
        this.destinationCardName = destinationCardName;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}

package shared.command_data_classes;

/**
 * Created by erics on 3/6/2017.
 */

public class RegisterCommandData {
    private String username;
    private String password;

    public RegisterCommandData() {

    }

    //setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

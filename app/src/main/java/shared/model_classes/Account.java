package shared.model_classes;

public class Account {

    private String username;
    private String password;
    private String authentication;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAuthentication() {
        return authentication;
    }
    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    /**
     * This function determines if the username and password the client gives matches the account
     * @param username
     * @param password
     * @return boolean for whether login info is valid
     */
    public boolean loginInfoValid(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }


}

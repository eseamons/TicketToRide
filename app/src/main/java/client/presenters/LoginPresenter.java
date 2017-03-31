package client.presenters;

import client.ClientFacade;
import client.ServerProxy;
import client.interfaces.ILoginPresenter;
import client.views.LoginView;
import shared.model_classes.Account;

public class LoginPresenter implements ILoginPresenter{

    private static LoginPresenter instance = new LoginPresenter();
    private ClientFacade clientFacade = new ClientFacade();

    public static LoginPresenter getInstance()
    {
        return instance;
    }

    LoginPresenter(){}

    @Override
    public boolean Register() {
        LoginView loginView = LoginView.getInstance();

        String registerUN = loginView.getRegisUsername();
        String registerPW = loginView.getRegisPassword();
        //clear fields
        loginView.clearRegisUsername();
        loginView.clearRegisPassword();

        boolean successful = false;
        if(!registerPW.equals("") && !registerUN.equals("")) {
            successful = clientFacade.register(registerUN, registerPW);
        }

        return successful;
    }

    @Override
    public boolean Login()
    {
        LoginView loginView = LoginView.getInstance();

        Account account = null;

        String username = loginView.getUsername();
        String password = loginView.getPassword();

        String IP = loginView.getIPAddress();
        ServerProxy.setURL(IP);
        //clear login fields
        loginView.clearloginUsername();
        loginView.clearLoginPassword();

        if(!username.equals("") && !password.equals("")) {
            account = clientFacade.login(username, password);
        }

        return account != null;
    }
}

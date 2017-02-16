package client.presenters;


import android.util.Log;

import client.ClientFacade;
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
            successful = clientFacade.Register(registerUN, registerPW);
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

        //clear login fields
        loginView.clearloginUsername();
        loginView.clearLoginPassword();

        if(!username.equals("") && !password.equals("")) {
            account = clientFacade.Login(username, password);
        }

        return account != null;
    }
}

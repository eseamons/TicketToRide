package client.presenters;


import client.ClientFacade;
import client.interfaces.ILoginPresenter;
import client.views.LoginView;

public class LoginPresenter implements ILoginPresenter{

    private static LoginPresenter instance;
    private ClientFacade clientFacade = new ClientFacade();

    public static LoginPresenter getInstance()
    {
        return instance;
    }


    @Override
    public boolean Register() {
        LoginView loginView = LoginView.getInstance();

        String registerUN = loginView.getRegisUsername();
        String registerPW = loginView.getRegisPassword();

        boolean successful = clientFacade.Register(registerUN, registerPW);
        return successful;
    }

    @Override
    public boolean Login()
    {
        LoginView loginView = LoginView.getInstance();

        String username = loginView.getUsername();
        String password = loginView.getPassword();

        boolean successful = clientFacade.Register(username, password);
        return successful;
    }
}

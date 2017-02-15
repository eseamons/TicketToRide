package client.presenters;

import client.ClientFacade;
import client.interfaces.ILoginPresenter;
import client.views.LoginView;
import shared.Result;

public class LoginPresenter implements ILoginPresenter{

    private static LoginPresenter instance;

    public static LoginPresenter getInstance()
    {
        return instance;
    }

    @Override
    public Result Register() {
        ClientFacade cf = new ClientFacade();

        LoginView LV = LoginView.getInstance();

        String regisUN = LV.getRegisUsername();
        String regisPW = LV.getRegisPassword();

        String resultStr;
        boolean resultBool;
        if (cf.Register(regisUN, regisPW))
        {
            resultStr = "Registered!";
            resultBool = true;
        }
        else{
            resultStr = "Failed to Register :/";
            resultBool = false;
        }

        Result result = new Result(resultBool,resultStr);
        return result;
    }

    @Override
    public Result Login() {

        ClientFacade cf = new ClientFacade();

        LoginView LV = LoginView.getInstance();

        String Username = LV.getUsername();
        String Password = LV.getPassword();

        String resultStr;
        boolean resultBool;
        if (cf.Register(Username, Password))
        {
            resultStr = "Logged in!";
            resultBool = true;
        }
        else{
            resultStr = "Failed to Login :/";
            resultBool = false;
        }

        Result result = new Result(resultBool,resultStr);
        return result;




    }
}

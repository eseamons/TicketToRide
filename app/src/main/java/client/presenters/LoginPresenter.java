package client.presenters;

import client.interfaces.ILoginPresenter;
import shared.Result;

public class LoginPresenter implements ILoginPresenter{

    private static LoginPresenter instance;

    public static LoginPresenter getInstance()
    {
        return instance;
    }

    @Override
    public Result Register() {
        return null;
    }

    @Override
    public Result Login() {
        return null;
    }
}

package client.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import client.interfaces.ILoginView;

public class LoginView extends AppCompatActivity implements ILoginView {

    private EditText loginUN;
    private EditText loginPW;
    private EditText registerUN;
    private EditText registerPW;

    private static LoginView instance;

    public static LoginView getInstance()
    {
        return instance;
    }

    public LoginView()
    {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instance = this;

    }

    @Override
    public String getUsername() {
        return loginUN.getText().toString();
    }

    @Override
    public String getPassword() {
        return loginPW.getText().toString();
    }

    @Override
    public String getRegisUsername() {
        return registerUN.getText().toString();
    }

    @Override
    public String getRegisPassword() {
        return registerPW.getText().toString();
    }
}

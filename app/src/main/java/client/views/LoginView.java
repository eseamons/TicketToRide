package client.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.erics.tickettoride.R;

import client.interfaces.ILoginView;
import client.presenters.GameLobbyPresenter;
import client.presenters.LoginPresenter;

public class LoginView extends AppCompatActivity implements ILoginView {

    private EditText loginUN;
    private EditText loginPW;
    private EditText registerUN;
    private EditText registerPW;
    private Button loginButton;
    private Button registerButton;

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
        setContentView(R.layout.activity_login);

        instance = this;

        loginUN = (EditText) findViewById(R.id.editText3);
        loginPW = (EditText) findViewById(R.id.editText4);
        registerUN = (EditText) findViewById(R.id.editText5);
        registerPW = (EditText) findViewById(R.id.editText6);

        loginButton = (Button) findViewById(R.id.button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: if successful switch to Game List View
                LoginPresenter loginPresenter = LoginPresenter.getInstance();
                boolean successful = loginPresenter.Login();

                if(successful)
                {}
                else
                {Toast.makeText(getBaseContext(), "Login Unsuccessful",Toast.LENGTH_SHORT).show();}

            }
        });
        registerButton = (Button) findViewById(R.id.button2);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginPresenter loginPresenter = LoginPresenter.getInstance();
                boolean successful = loginPresenter.Register();

                if(successful)
                {Toast.makeText(getBaseContext(), "You have Successfully Registered!",Toast.LENGTH_SHORT).show();}
                else
                {Toast.makeText(getBaseContext(), "Register Unsuccessful",Toast.LENGTH_SHORT).show();}
            }
        });
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

package client.views;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erics.tickettoride.R;

import client.interfaces.ILoginView;
import client.presenters.LoginPresenter;

public class LoginView extends AppCompatActivity implements ILoginView {

    private TextView ttrTitle;

    private EditText loginUN;
    private EditText loginPW;
    private EditText registerUN;
    private EditText registerPW;
    private Button loginButton;
    private Button registerButton;

    private EditText IPAdressEditText;

    private static LoginView instance = new LoginView();

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

        ttrTitle = (TextView) findViewById(R.id.ttrTitle);
        Typeface HOWDY_font = Typeface.createFromAsset(getAssets(), "fonts/RioGrande.ttf");
        ttrTitle.setTypeface(HOWDY_font);

        loginUN = (EditText) findViewById(R.id.editText3);
        loginPW = (EditText) findViewById(R.id.editText4);
        registerUN = (EditText) findViewById(R.id.editText5);
        registerPW = (EditText) findViewById(R.id.editText6);

        IPAdressEditText = (EditText) findViewById(R.id.ipAddress);

        loginButton = (Button) findViewById(R.id.button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginPresenter loginPresenter = LoginPresenter.getInstance();
                boolean successful = loginPresenter.Login();

                if(successful)
                {
                    if(loginPresenter.isInGame())
                    {
                        startActivity(new Intent(LoginView.this, MapViewActivity.class));
                    }
                    else
                    {
                        startActivity(new Intent(LoginView.this, GameListView.class));
                    }

                }
                else {
                    Toast.makeText(getBaseContext(), "Login Unsuccessful",Toast.LENGTH_SHORT).show();
                }

            }
        });
        registerButton = (Button) findViewById(R.id.button2);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginPresenter loginPresenter = LoginPresenter.getInstance();
                boolean successful = loginPresenter.Register();

                if(successful) {
                    Toast.makeText(getBaseContext(), "You have Successfully Registered!",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getBaseContext(), "Register Unsuccessful",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public String getIPAddress() { return IPAdressEditText.getText().toString();}
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


    public void clearloginUsername() {
        loginUN.setText("");
    }

    public void clearLoginPassword() {
        loginPW.setText("");
    }

    public void clearRegisUsername() {
        registerUN.setText("");
    }

    public void clearRegisPassword() {
        registerPW.setText("");
    }
}

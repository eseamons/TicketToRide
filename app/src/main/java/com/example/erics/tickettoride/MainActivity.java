package com.example.erics.tickettoride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import client.presenters.GameLobbyPresenter;
import client.views.LoginView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(MainActivity.this, LoginView.class));
        setContentView(R.layout.activity_login);

        GameLobbyPresenter lobbyPresenter = new GameLobbyPresenter();
    }

    public void goToGameList()
    {

    }
}

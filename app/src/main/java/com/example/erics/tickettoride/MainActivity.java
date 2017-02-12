package com.example.erics.tickettoride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import client.presenters.GameLobbyPresenter;
import client.views.GameLobbyView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // start with this
        //setContentView(R.layout.activity_login);
        startActivity(new Intent(MainActivity.this, GameLobbyView.class));
        setContentView(R.layout.activity_newgamelobby);

        //This area for testing GameLobbyView
        EditText msgText = (EditText) findViewById(R.id.msgEditText);
        //Create View first
        GameLobbyView lobbyView = new GameLobbyView(msgText);

        GameLobbyPresenter lobbyPresenter = new GameLobbyPresenter();
    }
}

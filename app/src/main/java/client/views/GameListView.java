package client.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erics.tickettoride.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import client.ClientFacade;
import client.Poller;
import client.interfaces.IGameListView;
import client.presenters.GameListPresenter;
import shared.model_classes.*;
import shared.model_classes.GameLobby;

public class GameListView extends AppCompatActivity implements IGameListView  {

    EditText gameName;
    EditText maxPlayers;
    //String gameName = null;
    //int numPlayers = -1;
    Button joinGameButton;
    Button createGameButton;
    GameLobby selectedGame;
    List<GameLobby> availableGames = new ArrayList<>();
    AvaliableGamesAdapter expAdapter;

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;

    private static GameListView instance = new GameListView();


    public static GameListView getInstance() {
        return instance;
    }


    public GameListView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        instance = this;

        Poller poller = new Poller();
        poller.runGetNonGameCommands();
        availableGames = GameListPresenter.getInstance().getClientGames();


        //text fields
        gameName = (EditText) findViewById(R.id.gameName);
        maxPlayers = (EditText) findViewById(R.id.numberofPlayers);


        //buttons
        createGameButton = (Button)findViewById(R.id.creatGameButton);
        createGameButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                GameListPresenter gameListPresenter = GameListPresenter.getInstance();
                boolean successful = gameListPresenter.createGame();
                if(successful)
                {Toast.makeText(getBaseContext(),"SUCCESSFULLY CREATED",Toast.LENGTH_SHORT).show();}
                else
                {Toast.makeText(getBaseContext(),"failed",Toast.LENGTH_SHORT).show();}
            }
        });

        joinGameButton = (Button)findViewById(R.id.joinGameButton);
        joinGameButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                GameListPresenter gameListPresenter = GameListPresenter.getInstance();
                boolean success = gameListPresenter.joinGame();

                if(success) {
                    startActivity(new Intent(GameListView.this, GameLobbyView.class));
                }
                else {
                    Toast.makeText(getBaseContext(), "Could not join game",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public String getGameName() {
        String name = gameName.getText().toString();
        return name;
    }

    @Override
    public int getNumberOfPlayers() {
        String numOfPlayers = maxPlayers.getText().toString();
        int num = Integer.parseInt(numOfPlayers);
        return num;
    }

    @Override
    public GameLobby getSelectedGame() {
        return selectedGame;
    }

    public void setAvaliableGames(List<GameLobby> games) {
        availableGames = games;
        update();
    }

    public void update() {

        ClientFacade cf = new ClientFacade();

        if (availableGames.size() >= 1)
            textView1.setText(availableGames.get(0).getName());
        if (availableGames.size() >= 2)
            textView2.setText(availableGames.get(1).getName());
        if (availableGames.size() >= 3)
            textView3.setText(availableGames.get(2).getName());
        if (availableGames.size() >= 4)
            textView4.setText(availableGames.get(3).getName());
        if (availableGames.size() >= 5)
            textView5.setText(availableGames.get(4).getName());

    }
}

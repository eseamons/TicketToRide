package client.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private EditText gameName;
    private EditText maxPlayers;
    private Button joinGameButton;
    private Button createGameButton;

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private GameLobby selectedGame;
    private List<GameLobby> availableGames = new ArrayList<>();

    private static GameListView instance = new GameListView();


    public static GameListView getInstance() {
        return instance;
    }


    public GameListView() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        instance = this;

        Poller poller = new Poller();
        poller.runGetLobbyCommands();
        availableGames = GameListPresenter.getInstance().getClientGames();

        //Code for RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerAdapter(availableGames);
        mRecyclerView.setAdapter(mAdapter);

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

                GameListPresenter.getInstance().getServerGames();
                //TODO:we have to set this game to the current game and then join the game!
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
                    //TODO: should i start the new activity or does it do this somewhere else?
                    //startActivity(new Intent(GameListView.this, GameLobbyView.class));
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

    @Override
    public void setSelectedGame(GameLobby gameLobby){selectedGame = gameLobby;}

    @Override
    public void setAvailableGames(List<GameLobby> games) {
        availableGames = games;

        mAdapter = new RecyclerAdapter(availableGames);
        mRecyclerView.setAdapter(mAdapter);
     }
}

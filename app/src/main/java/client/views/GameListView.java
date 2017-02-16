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
import android.widget.Toast;

import com.example.erics.tickettoride.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import client.Poller;
import client.interfaces.IGameListView;
import client.presenters.GameListPresenter;
import shared.model_classes.*;
import shared.model_classes.GameLobby;

public class GameListView extends AppCompatActivity implements IGameListView  {

    List<String> avaliableGames;

    String gameName = null;
    int numPlayers = -1;
    Button joinGameButton;
    Button createGameButton;
    GameLobby selectedGame;
    ArrayList<GameLobby> availableGames = new ArrayList<>();
    AvaliableGamesAdapter expAdapter;

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
        GameListPresenter.getInstance().getServerGames();
        //availableGames = (ArrayList<GameLobby>)GameListPresenter.getInstance().getClientGames();


        //System.out.println("might not work...");


        expAdapter = new AvaliableGamesAdapter(getBaseContext(),availableGames);
        populateGamesList();
        /*
        List<GameLobby> avaliableGamesArray = availableGames;
        ExpandableListAdapter listAdapter = new AvaliableGamesAdapter(getBaseContext(), avaliableGamesArray);
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.gameList);
        listView.setAdapter(listAdapter);
        listView.expandGroup(0);

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
            {
                Object game = (Object) parent.getExpandableListAdapter().getChild(groupPosition, childPosition);
                GameLobby gameLobby = new GameLobby();

                if(game.getClass() == gameLobby.getClass())
                {
                    gameLobby = (GameLobby) game;
                    selectedGame = gameLobby;
                    return true;
                }
                return false;
            }

        });
*/


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

        EditText gameNameText = (EditText)findViewById(R.id.gameName);
        gameNameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {gameName = s.toString();}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {gameName = s.toString();}

            @Override
            public void afterTextChanged(Editable s)
            {gameName = s.toString();}
        });

        EditText maxPlayerText = (EditText) findViewById(R.id.numberofPlayers);
        maxPlayerText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                String maxPlayerString = charSequence.toString();
                numPlayers = Integer.parseInt(maxPlayerString);
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                String maxPlayerString = editable.toString();
                numPlayers = Integer.parseInt(maxPlayerString);
            }
        });

        joinGameButton = (Button)findViewById(R.id.joinGameButton);
        joinGameButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                GameListPresenter gameListPresenter = GameListPresenter.getInstance();
                gameListPresenter.joinGame();
            }
        });
    }


    @Override
    public String getGameName() {
        return gameName;
    }

    @Override
    public int getNumberOfPlayers() {
        return numPlayers;
    }

    @Override
    public GameLobby getSelectedGame() {
        return selectedGame;
    }

    public void setAvaliableGames(List<GameLobby> games)
    {availableGames = (ArrayList)games;}


    public void populateGamesList()
    {
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.gameList);
        listView.setAdapter(expAdapter);
        listView.expandGroup(0);

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
            {
                Object game = (Object) parent.getExpandableListAdapter().getChild(groupPosition, childPosition);
                GameLobby gameLobby = new GameLobby();

                if(game.getClass() == gameLobby.getClass())
                {
                    gameLobby = (GameLobby) game;
                    selectedGame = gameLobby;
                    return true;
                }
                return false;
            }

        });

        expAdapter.notifyDataSetChanged();
    }


}

package client.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.erics.tickettoride.R;

import java.util.List;

import client.interfaces.IGameListView;
import client.presenters.GameListPresenter;
import shared.model_classes.*;
import shared.model_classes.GameLobby;

public class GameListView extends AppCompatActivity implements IGameListView  {

    List<String> avaliableGames;
    String gameName;
    int numPlayers;
    Button joinGameButton;
    Button createGameButton;
    GameListPresenter gameListPresenter = new GameListPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

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
            {
                String maxPlayerString = charSequence.toString();
                numPlayers = Integer.parseInt(maxPlayerString);
            }

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
                gameListPresenter.joinGame();
            }
        });

        createGameButton = (Button)findViewById(R.id.creatGameButton);
        createGameButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                gameListPresenter.createGame();
            }
        });








    }






    @Override
    public String getGameName() {
        return gameName;
    }

    @Override
    public int getNumberOfPlayers() {
        return 0;
    }

    @Override
    public GameLobby getSelectedGame() {
        return null;
    }


}

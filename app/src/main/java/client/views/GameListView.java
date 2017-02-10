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
import shared.model_classes.Game;

public class GameListView extends AppCompatActivity implements IGameListView  {

    List<String> avaliableGames;
    String gameName;
    int numPlayers;
    Button joinGame;
    Button createGame;

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
    public Game getSelectedGame() {
        return null;
    }


}

package client.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.example.erics.tickettoride.R;

import client.interfaces.IGameLobbyView;
import shared.model_classes.Player;

public class GameLobbyView extends AppCompatActivity implements IGameLobbyView{

    private Player[] players;

    private String chat;

    private EditText msgEditText;

    private Button startBtn;

    private Button msgButton;

    private static GameLobbyView instance;

    public static GameLobbyView getInstance()
    {
        return instance;
    }

    public GameLobbyView (EditText msg)
    {
        msgEditText = msg;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamelobby);

        msgEditText = (EditText) findViewById(R.id.msgEditText);
//        msgEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });


    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] p)
    {
        players = p;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getMessage()
    {
        return msgEditText.getText().toString();
    }

    public void setMessage(EditText msgEditText) {
        this.msgEditText = msgEditText;
    }

    public Player getPlayer(int index)
    {
        return players[index];
    }




    public int getColor()
    {

        return 0;
    }

}

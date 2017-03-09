package client.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.erics.tickettoride.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import client.ClientFacade;
import client.ClientModel;
import client.Poller;
import client.interfaces.IGameLobbyView;
import client.presenters.GameLobbyPresenter;
import shared.model_classes.Player;

public class GameLobbyView extends AppCompatActivity implements IGameLobbyView{

    private Player[] players;

    private EditText msgEditText;
    private ImageView player1Color;
    private ImageView player2Color;
    private ImageView player3Color;
    private ImageView player4Color;
    private ImageView player5Color;

    private TextView player1Text;
    private TextView player2Text;
    private TextView player3Text;
    private TextView player4Text;
    private TextView player5Text;


    private ListView ChatList;

    private Button startBtn;

    private Button msgButton;

    private static GameLobbyView instance = new GameLobbyView();

    public static GameLobbyView getInstance()
    {
        return instance;
    }

    public GameLobbyView (EditText msg)
    {
        msgEditText = msg;
    }

    public GameLobbyView(){}

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgamelobby);
        instance = this;


        msgEditText = (EditText) findViewById(R.id.msgEditText);


        startBtn = (Button) findViewById(R.id.StartBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GameLobbyPresenter.getInstance().beginGame())
                {startActivity(new Intent(GameLobbyView.this, MapViewActivity.class));
                }
            }
        });

        msgButton = (Button) findViewById(R.id.MessageBtn);
        msgButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                msgEditText.setText("");
                GameLobbyPresenter.getInstance().sendMessage();
            }
        });

        player1Color = (ImageView) findViewById(R.id.player1Color);
        player1Color.setImageResource(R.drawable.red);
        player2Color = (ImageView) findViewById(R.id.player2Color);
        player2Color.setImageResource(R.drawable.green);
        player3Color = (ImageView) findViewById(R.id.player3Color);
        player3Color.setImageResource(R.drawable.blue);
        player4Color = (ImageView) findViewById(R.id.player4Color);
        player4Color.setImageResource(R.drawable.yellow);
        player5Color = (ImageView) findViewById(R.id.player5Color);
        player5Color.setImageResource(R.drawable.black);


        player1Text = (TextView) findViewById(R.id.player1Text);
        player1Text.setText("Empty");
        player2Text = (TextView) findViewById(R.id.player2Text);
        player2Text.setText("Empty");
        player3Text = (TextView) findViewById(R.id.player3Text);
        player3Text.setText("Empty");
        player4Text = (TextView) findViewById(R.id.player4Text);
        player4Text.setText("Empty");
        player5Text = (TextView) findViewById(R.id.player5Text);
        player5Text.setText("Empty");

        ChatList = (ListView) findViewById(R.id.ChatList);
        List<String> chatArray = getChat();
        ArrayAdapter<String> chatAdapter = new ArrayAdapter<String>(this, R.layout.simple_list_item_1, chatArray);
        ChatList.setAdapter(chatAdapter);


        //POLLER
//        GameLobbyPresenter.getInstance().getPlayers();
//        ClientModel model = ClientModel.getInstance();
    }


    public void update(Observable o, Object arg) {

        if (ClientModel.getInstance().getCurrent_game_lobby() != null)
        {

            ClientFacade cf = new ClientFacade();
            List<Player> players = cf.getPlayers();

            if (players.size() >= 1)
                player1Text.setText(players.get(0).getAccount().getUsername());
            if (players.size() >= 2)
                player1Text.setText(players.get(1).getAccount().getUsername());
            if (players.size() >= 3)
                player1Text.setText(players.get(2).getAccount().getUsername());
            if (players.size() >= 4)
                player1Text.setText(players.get(3).getAccount().getUsername());
            if (players.size() >= 5)
                player1Text.setText(players.get(4).getAccount().getUsername());


            //TODO: update chat?

        }
    }

    public class SimpleImageArrayAdapter extends ArrayAdapter<Integer> {
        private Integer[] images;

        public SimpleImageArrayAdapter(Context context, Integer[] images) {
            super(context, android.R.layout.simple_spinner_item, images);
            this.images = images;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getImageForPosition(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getImageForPosition(position);
        }

        private View getImageForPosition(int position) {
            ImageView imageView = new ImageView(getContext());
            imageView.setBackgroundResource(images[position]);
            imageView.setMaxWidth(20);
            imageView.setMaxHeight(20);
            imageView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return imageView;
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] p)
    {
        players = p;
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

    public int getColor() {
        return 0;
    }

    public ArrayList<String> getChat() {
        ClientFacade cf = new ClientFacade();
        ArrayList<String> chatArray = cf.getLobbyChat();

        return chatArray;
    }

}

package client.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.erics.tickettoride.R;

import org.w3c.dom.Text;

import client.interfaces.IGameStatsView;

/**
 * Created by sirla on 3/3/2017.
 */

public class GameStatsView extends AppCompatActivity implements IGameStatsView {

    TextView player1Name;
    TextView player1TrainCardsNum;
    TextView player1DestinationCardsNum;
    TextView player1TrainsRemainingNum;
    TextView player1PointsNum;
    TextView player1TurnMarker;
    TextView player2Name;
    TextView player2TrainCardsNum;
    TextView player2DestinationCardsNum;
    TextView player2TrainsRemainingNum;
    TextView player2PointsNum;
    TextView player2TurnMarker;
    TextView player3Name;
    TextView player3TrainCardsNum;
    TextView player3DestinationCardsNum;
    TextView player3TrainsRemainingNum;
    TextView player3PointsNum;
    TextView player3TurnMarker;
    TextView player4Name;
    TextView player4TrainCardsNum;
    TextView player4DestinationCardsNum;
    TextView player4TrainsRemainingNum;
    TextView player4PointsNum;
    TextView player4TurnMarker;
    TextView player5Name;
    TextView player5TrainCardsNum;
    TextView player5DestinationCardsNum;
    TextView player5TrainsRemainingNum;
    TextView player5PointsNum;
    TextView player5TurnMarker;
    EditText chatMessage;
    Button deckButton;
    Button mapButton;
    Button sendMessageButton;
    TextView chatBox;


    private static GameStatsView instance = new GameStatsView();

    public static GameStatsView getInstance(){
        return instance;
    }

    public GameStatsView(){}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_stats);

        player1Name = (TextView) findViewById(R.id.player1Text);
        player1TrainCardsNum = (TextView) findViewById(R.id.player1TrainCardsNum);
        player1DestinationCardsNum = (TextView) findViewById(R.id.player1DestinationTicketsNum);
        player1TrainsRemainingNum = (TextView) findViewById(R.id.player1TrainsRemainingNum);
        player1PointsNum = (TextView) findViewById(R.id.player1PointsNum);
        player1TurnMarker = (TextView) findViewById(R.id.player1TurnMarker);
        player2Name = (TextView) findViewById(R.id.player2Text);
        player2TrainCardsNum = (TextView) findViewById(R.id.player2TrainCardsNum);
        player2DestinationCardsNum = (TextView) findViewById(R.id.player2DestinationTicketsNum);
        player2TrainsRemainingNum = (TextView) findViewById(R.id.player2TrainsRemainingNum);
        player2PointsNum = (TextView) findViewById(R.id.player2PointsNum);
        player2TurnMarker = (TextView) findViewById(R.id.player2TurnMarker);
        player3Name = (TextView) findViewById(R.id.player3Text);
        player3TrainCardsNum = (TextView) findViewById(R.id.player3TrainCardsNum);
        player3DestinationCardsNum = (TextView) findViewById(R.id.player3DestinationTicketsNum);
        player3TrainsRemainingNum = (TextView) findViewById(R.id.player3TrainsRemainingNum);
        player3PointsNum = (TextView) findViewById(R.id.player3PointsNum);
        player3TurnMarker = (TextView) findViewById(R.id.player3TurnMarker);
        player4Name = (TextView) findViewById(R.id.player4Text);
        player4TrainCardsNum = (TextView) findViewById(R.id.player4TrainCardsNum);
        player4DestinationCardsNum = (TextView) findViewById(R.id.player4DestinationTicketsNum);
        player4TrainsRemainingNum = (TextView) findViewById(R.id.player4TrainsRemainingNum);
        player4PointsNum = (TextView) findViewById(R.id.player4PointsNum);
        player4TurnMarker = (TextView) findViewById(R.id.player4TurnMarker);
        player5Name = (TextView) findViewById(R.id.player5Text);
        player5TrainCardsNum = (TextView) findViewById(R.id.player5TrainCardsNum);
        player5DestinationCardsNum = (TextView) findViewById(R.id.player5DestinationTicketsNum);
        player5TrainsRemainingNum = (TextView) findViewById(R.id.player5TrainsRemainingNum);
        player5PointsNum = (TextView) findViewById(R.id.player5PointsNum);
        player5TurnMarker = (TextView) findViewById(R.id.player5TurnMarker);
        chatMessage = (EditText) findViewById(R.id.editText);
        deckButton = (Button) findViewById(R.id.deckButton);
        mapButton = (Button) findViewById(R.id.mapButton);
        sendMessageButton = (Button) findViewById(R.id.sendButton);
        chatBox = (TextView) findViewById(R.id.chatBox);

        deckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameStatsView.this, GameDeckView.class));
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameStatsView.this, MapViewActivity.class));
            }
        });

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: add functionality
            }
        });
    }

    @Override
    public String getChatMessage() {
        return chatMessage.getText().toString();
    }

    @Override
    public void setPlayer1Name(String s) {
        player1Name.setText(s);
    }

    @Override
    public void setPlayer1TrainCardsNum(int i) {
        player1TrainCardsNum.setText(Integer.toString(i));
    }

    @Override
    public void setPlayer1DestinationCardsNum(int i) {
        player1DestinationCardsNum.setText(Integer.toString(i));
    }

    @Override
    public void setPlayer1TrainsRemainingNum(int i) {
        player1TrainsRemainingNum.setText(Integer.toString(i));
    }

    @Override
    public void setPlayer1PointsNum(int i) {
        player1PointsNum.setText(Integer.toString(i));
    }

    @Override
    public void setPlayer1TurnMarker(boolean b) {
        if(b) player1TurnMarker.setVisibility(View.VISIBLE);
        else player1TurnMarker.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setPlayer2Name(String s) {
        player2Name.setText(s);
    }

    @Override
    public void setPlayer2TrainCardsNum(int i) {
        player2TrainCardsNum.setText(Integer.toString(i));
    }

    @Override
    public void setPlayer2DestinationCardsNum(int i) {
        player2DestinationCardsNum.setText(Integer.toString(i));
    }

    @Override
    public void setPlayer2TrainsRemainingNum(int i) {
        player2TrainsRemainingNum.setText(Integer.toString(i));
    }

    @Override
    public void setPlayer2PointsNum(int i) {
        player2PointsNum.setText(Integer.toString(i));
    }

    @Override
    public void setPlayer2TurnMarker(boolean b) {
        if(b) player2TurnMarker.setVisibility(View.VISIBLE);
        else player2TurnMarker.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setPlayer3Name(String s) {
        player3Name.setText(s);
    }

    @Override
    public void setPlayer3TrainCardsNum(int i) {
        player3TrainCardsNum.setText(Integer.toString(i));
    }

    @Override
    public void setPlayer3DestinationCardsNum(int i) {
        player3DestinationCardsNum.setText(Integer.toString(i));
    }

    @Override
    public void setPlayer3TrainsRemainingNum(int i) {
        player3TrainsRemainingNum.setText(Integer.toString(i));
    }

    @Override
    public void setPlayer3PointsNum(int i) {
        player3PointsNum.setText(Integer.toString(i));
    }

    @Override
    public void setPlayer3TurnMarker(boolean b) {
        if(b) player3TurnMarker.setVisibility(View.VISIBLE);
        else player3TurnMarker.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setPlayer4Name(String s) {
        player4Name.setText(s);
    }

    @Override
    public void setPlayer4TrainCardsNum(int i) {
        player4TrainCardsNum.setText(Integer.toString(i));
    }

    @Override
    public void setPlayer4DestinationCardsNum(int i) {
        player4DestinationCardsNum.setText(Integer.toString(i));
    }

    @Override
    public void setPlayer4TrainsRemainingNum(int i) {
        player4TrainsRemainingNum.setText(Integer.toString(i));
    }

    @Override
    public void setPlayer4PointsNum(int i) {
        player4PointsNum.setText(Integer.toString(i));
    }

    @Override
    public void setPlayer4TurnMarker(boolean b) {
        if(b) player4TurnMarker.setVisibility(View.VISIBLE);
        else player4TurnMarker.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setPlayer5Name(String s) {
        player5Name.setText(s);
    }

    @Override
    public void setPlayer5TrainCardsNum(int i) {
        player5TrainCardsNum.setText(Integer.toString(i));
    }

    @Override
    public void setPlayer5DestinationCardsNum(int i) {
        player5DestinationCardsNum.setText(Integer.toString(i));
    }

    @Override
    public void setPlayer5TrainsRemainingNum(int i) {
        player5TrainsRemainingNum.setText(Integer.toString(i));
    }

    @Override
    public void setPlayer5PointsNum(int i) {
        player5PointsNum.setText(Integer.toString(i));
    }

    @Override
    public void setPlayer5TurnMarker(boolean b) {
        if(b) player5TurnMarker.setVisibility(View.VISIBLE);
        else player5TurnMarker.setVisibility(View.INVISIBLE);
    }
}

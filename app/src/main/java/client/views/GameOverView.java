package client.views;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erics.tickettoride.R;

import client.interfaces.IGameOverView;
import client.presenters.GameOverPresenter;
import shared.model_classes.Player;

public class GameOverView extends AppCompatActivity implements IGameOverView{

    ImageView victory;
    ImageView defeat;

    ImageView ribbon1;
    ImageView ribbon2;
    ImageView ribbon3;
    ImageView ribbon4;
    ImageView ribbon5;

    TextView player1Name;
    TextView player2Name;
    TextView player3Name;
    TextView player4Name;
    TextView player5Name;
    TextView player1Score;
    TextView player2Score;
    TextView player3Score;
    TextView player4Score;
    TextView player5Score;
    TextView player2;
    TextView player3;
    TextView player4;
    TextView player5;

    Button QuitBtn;
    Button MapBtn;

    public boolean GameOver = false;

    GameOverPresenter presenter;


    private static GameOverView instance = new GameOverView();

    public static boolean viewFinished = false;
    public static GameOverView getInstance()
    {
        return instance;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        GameOver = true;

        instance = this;

        victory = (ImageView) findViewById(R.id.victoryPic);
        defeat = (ImageView) findViewById(R.id.defeatPic);

        ribbon1 = (ImageView) findViewById(R.id.ribbon1);
        ribbon2 = (ImageView) findViewById(R.id.ribbon2);
        ribbon3 = (ImageView) findViewById(R.id.ribbon3);
        ribbon4 = (ImageView) findViewById(R.id.ribbon4);
        ribbon5 = (ImageView) findViewById(R.id.ribbon5);

        player1Name = (TextView) findViewById(R.id.player1Name);
        player2Name = (TextView) findViewById(R.id.player2Name);
        player3Name = (TextView) findViewById(R.id.player3Name);
        player4Name = (TextView) findViewById(R.id.player4Name);
        player5Name = (TextView) findViewById(R.id.player5Name);
        player1Score = (TextView) findViewById(R.id.player1Points);
        player2Score = (TextView) findViewById(R.id.player2Points);
        player3Score = (TextView) findViewById(R.id.player3Points);
        player4Score = (TextView) findViewById(R.id.player4Points);
        player5Score = (TextView) findViewById(R.id.player5Points);

        player2 = (TextView) findViewById(R.id.player2Place);
        player3 = (TextView) findViewById(R.id.player3Place);
        player4 = (TextView) findViewById(R.id.player4Place);
        player5 = (TextView) findViewById(R.id.player5Place);

        //presenter = new GameOverPresenter();
        presenter = GameOverPresenter.getInstance();

        QuitBtn = (Button) findViewById(R.id.QuitBtn);
        QuitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.Quit();
                startActivity(new Intent(GameOverView.this, GameListView.class));
            }
        });

        MapBtn = (Button) findViewById(R.id.MapBtn);
        MapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.ToFinishedMap();
                Intent intent = new Intent(GameOverView.this, MapViewActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });


        viewFinished = true;

    }

    @Override
    public String getPlayer1() {
        return player1Name.toString();
    }

    @Override
    public String getPlayer2() {
        return player2Name.toString();
    }

    @Override
    public String getPlayer3() {
        return player3Name.toString();
    }

    @Override
    public String getPlayer4() {
        return player4Name.toString();
    }

    @Override
    public String getPlayer5() {
        return player5Name.toString();
    }

    @Override
    public void setPlayer1Name(String s) {

        player1Name.setText(s);
    }

    @Override
    public void setPlayer2Name(String s) {
        player2Name.setText(s);
    }

    @Override
    public void setPlayer3Name(String s) {
        player3Name.setText(s);
    }

    @Override
    public void setPlayer4Name(String s) {
        player4Name.setText(s);
    }

    @Override
    public void setPlayer5Name(String s) {
        player5Name.setText(s);
    }

    @Override
    public void setPlayer1Score(int points) {
        player1Score.setText(Integer.toString(points));
    }

    @Override
    public void setPlayer2Score(int points) {
        player2Score.setText(Integer.toString(points));

    }

    @Override
    public void setPlayer3Score(int points) {
        player3Score.setText(Integer.toString(points));

    }

    @Override
    public void setPlayer4Score(int points) {
        player4Score.setText(Integer.toString(points));

    }

    @Override
    public void setPlayer5Score(int points) {
        player5Score.setText(Integer.toString(points));

    }

    public void player2Invis()
    {
        player2.setText("");
        player2Name.setText("");
        player2Score.setText("");
    }

    @Override
    public void player3Invis() {
        player3.setText("");
        player3Name.setText("");
        player3Score.setText("");
    }

    @Override
    public void player4Invis() {
        player4.setText("");
        player4Name.setText("");
        player4Score.setText("");
    }

    @Override
    public void player5Invis() {
        player5.setText("");
        player5Name.setText("");
        player5Score.setText("");
    }

    @Override
    public void setVictory() {
        defeat.setVisibility(View.GONE);
        victory.setVisibility(View.VISIBLE);
    }

    @Override
    public void setDefeat() {
        defeat.setVisibility(View.VISIBLE);
        victory.setVisibility(View.GONE);
    }

    @Override
    public void setRibbon1() {
        ribbon1.setVisibility(View.VISIBLE);
        ribbon2.setVisibility(View.INVISIBLE);
        ribbon3.setVisibility(View.INVISIBLE);
        ribbon4.setVisibility(View.INVISIBLE);
        ribbon5.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setRibbon2() {
        ribbon1.setVisibility(View.INVISIBLE);
        ribbon2.setVisibility(View.VISIBLE);
        ribbon3.setVisibility(View.INVISIBLE);
        ribbon4.setVisibility(View.INVISIBLE);
        ribbon5.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setRibbon3() {
        ribbon1.setVisibility(View.INVISIBLE);
        ribbon2.setVisibility(View.INVISIBLE);
        ribbon3.setVisibility(View.VISIBLE);
        ribbon4.setVisibility(View.INVISIBLE);
        ribbon5.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setRibbon4() {
        ribbon1.setVisibility(View.INVISIBLE);
        ribbon2.setVisibility(View.INVISIBLE);
        ribbon3.setVisibility(View.INVISIBLE);
        ribbon4.setVisibility(View.VISIBLE);
        ribbon5.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setRibbon5() {
        ribbon1.setVisibility(View.INVISIBLE);
        ribbon2.setVisibility(View.INVISIBLE);
        ribbon3.setVisibility(View.INVISIBLE);
        ribbon4.setVisibility(View.INVISIBLE);
        ribbon5.setVisibility(View.VISIBLE);
    }
}

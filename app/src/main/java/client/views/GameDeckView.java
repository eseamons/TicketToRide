package client.views;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.erics.tickettoride.R;

import client.interfaces.IGameDeckView;
import client.presenters.GameDeckPresenter;
import shared.CardColor;

import static com.example.erics.tickettoride.R.drawable.black_card;
import static com.example.erics.tickettoride.R.drawable.blue_card;
import static com.example.erics.tickettoride.R.drawable.green_card;
import static com.example.erics.tickettoride.R.drawable.orange_card;
import static com.example.erics.tickettoride.R.drawable.purple_card;
import static com.example.erics.tickettoride.R.drawable.red_card;
import static com.example.erics.tickettoride.R.drawable.white_card;
import static com.example.erics.tickettoride.R.drawable.wild_card;
import static com.example.erics.tickettoride.R.drawable.yellow_card;

/**
 * Created by sirla on 3/3/2017.
 */

public class GameDeckView extends AppCompatActivity implements IGameDeckView {

    GameDeckPresenter presenter;

    TextView purple;
    TextView white;
    TextView blue;
    TextView yellow;
    TextView orange;
    TextView black;
    TextView red;
    TextView green;
    TextView wild;
    TextView trainsRemaining;
    TextView destinationTickets;
    ImageButton deckButton;
    ImageButton faceUpCard0Button;
    ImageButton faceUpCard1Button;
    ImageButton faceUpCard2Button;
    ImageButton faceUpCard3Button;
    ImageButton faceUpCard4Button;
    Button mapButton;
    Button statsButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_deck);

        purple = (TextView) findViewById(R.id.purpleNum);
        white = (TextView) findViewById(R.id.whiteNum);
        blue = (TextView) findViewById(R.id.blueNum);
        yellow = (TextView) findViewById(R.id.yellowNum);
        orange = (TextView) findViewById(R.id.orangeNum);
        black = (TextView) findViewById(R.id.blackNum);
        red = (TextView) findViewById(R.id.redNum);
        green = (TextView) findViewById(R.id.greenNum);
        wild = (TextView) findViewById(R.id.wildNum);
        trainsRemaining = (TextView) findViewById(R.id.trainsRemainingNum);
        deckButton = (ImageButton) findViewById(R.id.deckButton);
        faceUpCard0Button = (ImageButton) findViewById(R.id.faceUpButton0);
        faceUpCard1Button = (ImageButton) findViewById(R.id.faceUpButton1);
        faceUpCard2Button = (ImageButton) findViewById(R.id.faceUpButton2);
        faceUpCard3Button = (ImageButton) findViewById(R.id.faceUpButton3);
        faceUpCard4Button = (ImageButton) findViewById(R.id.faceUpButton4);
        mapButton = (Button) findViewById(R.id.mapButton);
        statsButton = (Button) findViewById(R.id.statsButton);

        destinationTickets = (TextView) findViewById(R.id.destinationTicketView);
        destinationTickets.setMovementMethod(new ScrollingMovementMethod());


        deckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.DeckCardClicked();
            }
        });

        faceUpCard0Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.FaceUp0Clicked();
            }
        });

        faceUpCard1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.FaceUp1Clicked();
            }
        });

        faceUpCard2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.FaceUp2Clicked();
            }
        });

        faceUpCard3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.FaceUp3Clicked();
            }
        });

        faceUpCard4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.FaceUp4Clicked();
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameDeckView.this, MapViewActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameDeckView.this, GameStatsView.class));
            }
        });

        presenter = new GameDeckPresenter(this);
    }

    @Override
    public void setPurpleNum(int i) {
        purple.setText(Integer.toString(i));
    }

    @Override
    public void setWhiteNum(int i) {
        white.setText(Integer.toString(i));
    }

    @Override
    public void setBlueNum(int i) {
        blue.setText(Integer.toString(i));
    }

    @Override
    public void setYellowNum(int i) {
        yellow.setText(Integer.toString(i));
    }

    @Override
    public void setOrangeNum(int i) {
        orange.setText(Integer.toString(i));
    }

    @Override
    public void setBlackNum(int i) {
        black.setText(Integer.toString(i));
    }

    @Override
    public void setRedNum(int i) {
        red.setText(Integer.toString(i));
    }

    @Override
    public void setGreenNum(int i) {
        green.setText(Integer.toString(i));
    }

    @Override
    public void setWildNum(int i) {
        wild.setText(Integer.toString(i));
    }

    @Override
    public void setTrainsRemainingNum(int i) {
        trainsRemaining.setText(Integer.toString(i));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setFaceUpCard0(CardColor c) {
        switch (c){
            case RED: faceUpCard0Button.setImageDrawable(getDrawable(red_card)); break;
            case ORANGE: faceUpCard0Button.setImageDrawable(getDrawable(orange_card)); break;
            case YELLOW: faceUpCard0Button.setImageDrawable(getDrawable(yellow_card)); break;
            case GREEN: faceUpCard0Button.setImageDrawable(getDrawable(green_card)); break;
            case BLUE: faceUpCard0Button.setImageDrawable(getDrawable(blue_card)); break;
            case PURPLE: faceUpCard0Button.setImageDrawable(getDrawable(purple_card)); break;
            case WHITE: faceUpCard0Button.setImageDrawable(getDrawable(white_card)); break;
            case BLACK: faceUpCard0Button.setImageDrawable(getDrawable(black_card)); break;
            case WILD: faceUpCard0Button.setImageDrawable(getDrawable(wild_card)); break;
            default:
                faceUpCard0Button.setVisibility(View.INVISIBLE); break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setFaceUpCard1(CardColor c) {
        switch (c){
            case RED: faceUpCard1Button.setImageDrawable(getDrawable(red_card)); break;
            case ORANGE: faceUpCard1Button.setImageDrawable(getDrawable(orange_card)); break;
            case YELLOW: faceUpCard1Button.setImageDrawable(getDrawable(yellow_card)); break;
            case GREEN: faceUpCard1Button.setImageDrawable(getDrawable(green_card)); break;
            case BLUE: faceUpCard1Button.setImageDrawable(getDrawable(blue_card)); break;
            case PURPLE: faceUpCard1Button.setImageDrawable(getDrawable(purple_card)); break;
            case WHITE: faceUpCard1Button.setImageDrawable(getDrawable(white_card)); break;
            case BLACK: faceUpCard1Button.setImageDrawable(getDrawable(black_card)); break;
            case WILD: faceUpCard1Button.setImageDrawable(getDrawable(wild_card)); break;
            default:
                faceUpCard1Button.setVisibility(View.INVISIBLE); break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setFaceUpCard2(CardColor c) {
        switch (c){
            case RED: faceUpCard2Button.setImageDrawable(getDrawable(red_card)); break;
            case ORANGE: faceUpCard2Button.setImageDrawable(getDrawable(orange_card)); break;
            case YELLOW: faceUpCard2Button.setImageDrawable(getDrawable(yellow_card)); break;
            case GREEN: faceUpCard2Button.setImageDrawable(getDrawable(green_card)); break;
            case BLUE: faceUpCard2Button.setImageDrawable(getDrawable(blue_card)); break;
            case PURPLE: faceUpCard2Button.setImageDrawable(getDrawable(purple_card)); break;
            case WHITE: faceUpCard2Button.setImageDrawable(getDrawable(white_card)); break;
            case BLACK: faceUpCard2Button.setImageDrawable(getDrawable(black_card)); break;
            case WILD: faceUpCard2Button.setImageDrawable(getDrawable(wild_card)); break;
            default:
                faceUpCard2Button.setVisibility(View.INVISIBLE); break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setFaceUpCard3(CardColor c) {
        switch (c){
            case RED: faceUpCard3Button.setImageDrawable(getDrawable(red_card)); break;
            case ORANGE: faceUpCard3Button.setImageDrawable(getDrawable(orange_card)); break;
            case YELLOW: faceUpCard3Button.setImageDrawable(getDrawable(yellow_card)); break;
            case GREEN: faceUpCard3Button.setImageDrawable(getDrawable(green_card)); break;
            case BLUE: faceUpCard3Button.setImageDrawable(getDrawable(blue_card)); break;
            case PURPLE: faceUpCard3Button.setImageDrawable(getDrawable(purple_card)); break;
            case WHITE: faceUpCard3Button.setImageDrawable(getDrawable(white_card)); break;
            case BLACK: faceUpCard3Button.setImageDrawable(getDrawable(black_card)); break;
            case WILD: faceUpCard3Button.setImageDrawable(getDrawable(wild_card)); break;
            default:
                faceUpCard3Button.setVisibility(View.INVISIBLE); break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setFaceUpCard4(CardColor c) {
        switch (c){
            case RED: faceUpCard4Button.setImageDrawable(getDrawable(red_card)); break;
            case ORANGE: faceUpCard4Button.setImageDrawable(getDrawable(orange_card)); break;
            case YELLOW: faceUpCard4Button.setImageDrawable(getDrawable(yellow_card)); break;
            case GREEN: faceUpCard4Button.setImageDrawable(getDrawable(green_card)); break;
            case BLUE: faceUpCard4Button.setImageDrawable(getDrawable(blue_card)); break;
            case PURPLE: faceUpCard4Button.setImageDrawable(getDrawable(purple_card)); break;
            case WHITE: faceUpCard4Button.setImageDrawable(getDrawable(white_card)); break;
            case BLACK: faceUpCard4Button.setImageDrawable(getDrawable(black_card)); break;
            case WILD: faceUpCard4Button.setImageDrawable(getDrawable(wild_card)); break;
            default:
                faceUpCard4Button.setVisibility(View.INVISIBLE); break;
        }
    }

    @Override
    public void setDestinationTickets(String s) {
        destinationTickets.setText(s);
    }
}

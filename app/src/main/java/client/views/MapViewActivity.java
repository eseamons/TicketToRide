package client.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.erics.tickettoride.R;

import java.util.ArrayList;
import java.util.List;

import client.ClientFacade;
import client.interfaces.IMapView;
import client.presenters.MapViewPresenter;
import shared.CardColor;
import shared.model_classes.DestinationCard;
import shared.model_classes.Route;
import shared.model_classes.model_list_classes.RoutesList;

public class MapViewActivity extends AppCompatActivity implements View.OnTouchListener, IMapView
{

    MapViewPresenter presenter;

    ImageView map;
    TextView purpleNum;
    TextView whiteNum;
    TextView blueNum;
    TextView yellowNum;
    TextView orangeNum;
    TextView blackNum;
    TextView redNum;
    TextView greenNum;
    TextView wildNum;

    Route selectedRoute = null;
    boolean canClaimSelected = false;

    public void setCanClaimSelected(boolean set)
    {
        canClaimSelected = set;
    }

    public Route getSelectedRoute()
    {
        return selectedRoute;
    }

    public void setSelectedRoute(Route r)
    {
        selectedRoute = r;
    }

    public void setPurpleNumText(String set) {
        purpleNum.setText(set);
    }

    public void setWhiteNumText(String set) {
        whiteNum.setText(set);;
    }

    public void setBlueNumText(String set) {
        blueNum.setText(set);;
    }

    public void setYellowNumText(String set) {
         yellowNum.setText(set);;
    }

    public void setOrangeNumText(String set) {
         orangeNum.setText(set);;
    }

    public void setBlackNumText(String set) {
            blackNum.setText(set);;
    }

    public void setRedNumText(String set) {
         redNum.setText(set);;
    }

    public void setGreenNumText(String set) {
         greenNum.setText(set);;
    }

    public void setWildNumText(String set) {
         wildNum.setText(set);;
    }

    public void setDestViewText(String set) {
         destView.setText(set);;
    }

    @Override
    public void onBackPressed() {
    }

    TextView destView;

    Button toDeck;
    Button toStats;

    Button StupidButton;
    Button claimRouteButton;
    Button drawDestinationCardButton;
    Button gameOverButton;

    Button[] DestinationCardButton;
    Button DestinationConfirmButton;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        map = (ImageView) findViewById(R.id.map);
        map.setOnTouchListener(this);

        purpleNum = (TextView) findViewById(R.id.purpleNum1);
        purpleNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("YUP, THAT TASTED PURPLE");
                presenter.setDesiredToUseCardColor(CardColor.PURPLE);
            }
        });

        whiteNum = (TextView) findViewById(R.id.whiteNum);
        whiteNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setDesiredToUseCardColor(CardColor.WHITE);
            }
        });

        blueNum = (TextView) findViewById(R.id.blueNum);
        blueNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setDesiredToUseCardColor(CardColor.BLUE);
            }
        });

        yellowNum = (TextView) findViewById(R.id.yellowNum);
        yellowNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setDesiredToUseCardColor(CardColor.YELLOW);
            }
        });

        orangeNum = (TextView) findViewById(R.id.orangeNum);
        orangeNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setDesiredToUseCardColor(CardColor.ORANGE);
            }
        });

        blackNum = (TextView) findViewById(R.id.blackNum);
        blackNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setDesiredToUseCardColor(CardColor.BLACK);
            }
        });

        redNum = (TextView) findViewById(R.id.redNum);
        redNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setDesiredToUseCardColor(CardColor.RED);
            }
        });

        greenNum = (TextView) findViewById(R.id.greenNum);
        greenNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setDesiredToUseCardColor(CardColor.GREEN);
            }
        });

        wildNum = (TextView) findViewById(R.id.wildNum);

        destView = (TextView) findViewById(R.id.map_destinationView);
        destView.setMovementMethod(new ScrollingMovementMethod());

        toStats = (Button) findViewById(R.id.button_TOSTATS);
        toStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapViewActivity.this, GameStatsView.class));
            }
        });
        toDeck = (Button) findViewById(R.id.button_TODECK);
        toDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapViewActivity.this, GameDeckView.class));
            }
        });

        StupidButton = (Button) findViewById(R.id.STUPIDBUTTON);
        StupidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.stupidButtonPressed();
            }
        });

        claimRouteButton = (Button) findViewById(R.id.claimRouteButton);
        claimRouteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.claimRouteButtonPressed();
            }
        });

        drawDestinationCardButton = (Button) findViewById(R.id.drawDestinationButton);
        drawDestinationCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.drawDestinationCardButtonPressed();
            }
        });

        gameOverButton = (Button) findViewById(R.id.button_QUIT);
        gameOverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapViewActivity.this, GameOverView.class));
            }
        });

        presenter = new MapViewPresenter(this);

        DestinationCardButton = new Button[3];

        DestinationCardButton[0] = (Button) findViewById(R.id.destinationCard1Button);
        DestinationCardButton[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.destinationCard1ButtonClicked();
            }
        });

        DestinationCardButton[1] = (Button) findViewById(R.id.destinationCard2Button);
        DestinationCardButton[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.destinationCard2ButtonClicked();
            }
        });

        DestinationCardButton[2] = (Button) findViewById(R.id.destinationCard3Button);
        DestinationCardButton[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.destinationCard3ButtonClicked();
            }
        });

        DestinationConfirmButton = (Button) findViewById(R.id.ConfirmDestinationButton);
        DestinationConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.destinationConfirmButtonClicked();
            }
        });

        setDestinationCardsAcceptanceVisibility(false);

        //if this causes problems, just comment this part out. Testing GameOver scheisse
        /*if (GameOverView.getInstance().GameOver)
        {
            toDeck.setVisibility(View.GONE);
            DestinationCardButton[0].setVisibility(View.GONE);
            DestinationCardButton[1].setVisibility(View.GONE);
            DestinationCardButton[2].setVisibility(View.GONE);
            DestinationConfirmButton.setVisibility(View.GONE);
            StupidButton.setVisibility(View.GONE);
            toStats.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MapViewActivity.this, GameOverView.class));
                }
            });
        }*/

    }

    public void goToGameOverView()
    {
        startActivity(new Intent(MapViewActivity.this, GameOverView.class));
    }

    public void setGameOverMapView()
    {
        claimRouteButton.setVisibility(View.INVISIBLE);
        drawDestinationCardButton.setVisibility(View.INVISIBLE);
        toDeck.setVisibility(View.INVISIBLE);
        toStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MapViewActivity.this, GameOverView.class));
            }
        });
    }

    public void setStupidButtonText(String set)
    {
        StupidButton.setText(set);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            Point clicked = new Point((int) event.getX(), (int) event.getY());
            presenter.onTouch(clicked);
        }
        return true;
    }

    public void drawRoutes(RoutesList routes)
    {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ttrmap);
        int width = map.getWidth();
        int height = map.getHeight();
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        Canvas canvas = new Canvas(mutableBitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        //draw the selected route as white or pink depending on if they can claim it
        if(selectedRoute != null)
        {
            if(canClaimSelected)
                paint.setColor(Color.WHITE);
            else
                paint.setColor(Color.MAGENTA);
            paint.setStrokeWidth(10);
            canvas.drawLine(selectedRoute.start_x,selectedRoute.start_y,selectedRoute.end_x,selectedRoute.end_y,paint);
        }

        //draw all other routes with their ownership color
        for(int i = 0; i < routes.getSize(); i++)
        {
            Route route = routes.getRoute(i);
            if(route.ownership != 0)
            {
                paint.setColor(getColorByOwnerShip(route.ownership));
                paint.setStrokeWidth(10);
                canvas.drawLine(route.start_x,route.start_y,route.end_x,route.end_y,paint);
            }
        }
        map.setImageBitmap(mutableBitmap);
    }

    public int getColorByOwnerShip(int ownership)
    {
        int ret = Color.RED;
        switch(ownership)
        {
            case 1: ret = Color.RED; break;
            case 2: ret = Color.GREEN; break;
            case 3: ret = Color.BLUE; break;
            case 4: ret = Color.YELLOW; break;
            case 5: ret = Color.BLACK; break;
        }
        return ret;
    }

    public void testCardNums()
    {
        List<CardColor> cards = new ArrayList<>();
        cards.add(CardColor.RED);
        cards.add(CardColor.RED);
        cards.add(CardColor.BLUE);
        cards.add(CardColor.ORANGE);
        cards.add(CardColor.BLACK);
        cards.add(CardColor.BLACK);
        //presenter.setPlayerCardViews(cards);
    }

    public void createFakeRoutes()
    {

        int cur_done = 3;
        RoutesList routes;
        routes = new RoutesList();
        cur_done = routes.CUR_DONE;
        for(int i = 0; i < cur_done; i++)
        {
            int owner = i%5 + 1;
            routes.getRoute(i).ownership = owner;
        }
    }

    int test_num = 0;
    public void getCoords(View v, MotionEvent event)
    {

        if(event.getAction() != MotionEvent.ACTION_DOWN)
            return;
        float x = event.getX();
        float y = event.getY();
        test_num++;
        purpleNum.setText("" + test_num);

        //System.out.println("X: " + x + " Y: " + y);
        System.out.println("X: " + x + " Y: " + y);

        if(test_num % 2 == 0)
        {
            Log.d("SPACE", "ROUTE END");
        }
    }


    public void setDestinationCardsAcceptanceVisibility(boolean vis)
    {
        for(int i = 0; i < DestinationCardButton.length; i++)
        {
            if(!vis)
                DestinationCardButton[i].setVisibility(View.INVISIBLE);
            else
                DestinationCardButton[i].setVisibility(View.VISIBLE);
        }
        if(!vis)
            DestinationConfirmButton.setVisibility(View.INVISIBLE);
        else
            DestinationConfirmButton.setVisibility(View.VISIBLE);
    }

    public void setDestinationCardText(DestinationCard[] dests, boolean[] acceptance)
    {
        for(int i = 0; i < dests.length; i++)
        {
            String text = dests[i].toString();
            if(acceptance[i])
                text += " (KEEP)";
            else
                text += " (DISCARD)";
            DestinationCardButton[i].setText(text);
        }
    }



}

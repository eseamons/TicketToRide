package client.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        map = (ImageView) findViewById(R.id.map);
        map.setOnTouchListener(this);

        purpleNum = (TextView) findViewById(R.id.purpleNum1);
        whiteNum = (TextView) findViewById(R.id.whiteNum);
        blueNum = (TextView) findViewById(R.id.blueNum);
        yellowNum = (TextView) findViewById(R.id.yellowNum);
        orangeNum = (TextView) findViewById(R.id.orangeNum);
        blackNum = (TextView) findViewById(R.id.blackNum);
        redNum = (TextView) findViewById(R.id.redNum);
        greenNum = (TextView) findViewById(R.id.greenNum);
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

        presenter = new MapViewPresenter(this);

    }

    public void setStupidButtonText(String set)
    {
        StupidButton.setText(set);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
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
        presenter.setPlayerCardViews(cards);
    }

    public void createFakeRoutes()
    {

        int cur_done = 3;
        RoutesList routes;
        routes = new RoutesList();
        cur_done = routes.cur_done;
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


}

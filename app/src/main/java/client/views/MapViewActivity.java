package client.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
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

import shared.CardColor;
import shared.model_classes.DestinationCard;
import shared.model_classes.Route;
import shared.model_classes.model_list_classes.RoutesList;

public class MapViewActivity extends AppCompatActivity implements View.OnTouchListener
{

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

    TextView destView;

    Button toDeck;
    Button toStats;

    int test_num = 0;
    int cur_done = 3;
    RoutesList routes;
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

        routes = new RoutesList();
        cur_done = routes.cur_done;
        for(int i = 0; i < cur_done; i++)
        {
            int owner = i%5 + 1;
            routes.getRoute(i).ownership = owner;
        }



    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        if(event.getAction() != MotionEvent.ACTION_DOWN)
            return true;
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
        drawRoutes(routes);
        testCardNums();
        setDestinationBox(new ArrayList<DestinationCard>());
        return true;
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
        setPlayerCardViews(cards);
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

        if(test_num % 2 == 0)
            canvas.drawLine(212,793,626,685,paint);
        for(int i = 0; i < cur_done; i++)
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
            case 1: ret = Color.BLUE; break;
            case 2: ret = Color.RED; break;
            case 3: ret = Color.GREEN; break;
            case 4: ret = Color.YELLOW; break;
            case 5: ret = Color.BLACK; break;
        }
        return ret;
    }

    public void setPlayerCardViews(List<CardColor> playerCards)
    {
        int redCards = 0;
        int orangeCards = 0;
        int yellowCards = 0;
        int greenCards = 0;
        int blueCards = 0;
        int purpleCards = 0;
        int whiteCards = 0;
        int blackCards = 0;
        int wildCards = 0;

        for (int i = 0; i < playerCards.size(); i++) {
            switch (playerCards.get(i)) {
                case RED:
                    redCards++;
                    break;
                case ORANGE:
                    orangeCards++;
                    break;
                case YELLOW:
                    yellowCards++;
                    break;
                case GREEN:
                    greenCards++;
                    break;
                case BLUE:
                    blueCards++;
                    break;
                case PURPLE:
                    purpleCards++;
                    break;
                case WHITE:
                    whiteCards++;
                    break;
                case BLACK:
                    blackCards++;
                    break;
                case WILD:
                    wildCards++;
                    break;
            }
        }
        purpleNum.setText("" + purpleCards);
        whiteNum.setText("" + whiteCards);
        blueNum.setText("" + blueCards);
        yellowNum.setText("" + yellowCards);
        orangeNum.setText("" + orangeCards);
        blackNum.setText("" + blackCards);
        redNum.setText("" + redCards);
        greenNum.setText("" + greenCards);
        wildNum.setText("" + wildCards);

    }

    public void setDestinationBox(List<DestinationCard> destinationCards)
    {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < destinationCards.size(); i++)
        {
            DestinationCard dc = destinationCards.get(i);
            sb.append(dc.toString() + "\n");
        }
        destView.setText(sb.toString());
        destView.setText("HI\nNO\nLANCE\nPLEASE\nSAVEme\nNO316ENGLISHPLS");
    }


}

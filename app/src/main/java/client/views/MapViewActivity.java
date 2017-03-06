package client.views;

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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erics.tickettoride.R;

import shared.model_classes.Route;
import shared.model_classes.model_list_classes.RoutesList;

public class MapViewActivity extends AppCompatActivity implements View.OnTouchListener
{

    ImageView map;
    TextView purpleNum;

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
        purpleNum.setText("BAKA");

        routes = new RoutesList();
        cur_done = routes.cur_done;
        for(int i = 0; i < cur_done; i++)
        {
            routes.getRoute(i).ownership = 1;
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

        if(test_num % 2 == 0)
            canvas.drawLine(212,793,626,685,paint);
        for(int i = 0; i < cur_done; i++)
        {
            Route route = routes.getRoute(i);
            if(route.ownership != 0)
            {
                paint.setColor(Color.RED);
                paint.setStrokeWidth(10);
                canvas.drawLine(route.start_x,route.start_y,route.end_x,route.end_y,paint);
            }
        }
        map.setImageBitmap(mutableBitmap);
    }
}

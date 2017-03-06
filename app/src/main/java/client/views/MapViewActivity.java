package client.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erics.tickettoride.R;

public class MapViewActivity extends AppCompatActivity implements View.OnClickListener
{

    ImageView map;
    TextView purpleNum;

    int test_num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        map = (ImageView) findViewById(R.id.map);
        map.setOnClickListener(this);

        purpleNum = (TextView) findViewById(R.id.purpleNum1);
        purpleNum.setText("BAKA");
        System.out.println("no work right");
        Log.i("does log work", "work");
    }


    @Override
    public void onClick(View v)
    {
        float x = v.getX();
        float y = v.getY();
        test_num++;
        purpleNum.setText("" + test_num);

        System.out.println("X: " + x + " Y: " + y);
        Log.i("1", ("X: " + x + " Y: " + y));
    }
}

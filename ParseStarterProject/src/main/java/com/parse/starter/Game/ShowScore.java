package com.parse.starter.Game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.starter.R;

/**
 * Created by Piyapong on 2/1/2559.
 */
public class ShowScore extends ActionBarActivity {

    Button btnOK;
    TextView txtPoint,txtTime;

    String receive_catName;
    int point;
    private int total_time;
    private int time_sec;
    private int time_mil;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_score);

        Bundle bundle = getIntent().getExtras();
        receive_catName =  bundle.getString("TrueFalse");
        point = bundle.getInt("true_false_score");
        total_time = bundle.getInt("true_false_time");
        time_mil = total_time%100;
        time_sec = total_time/100;

        Log.d("Point Receive", String.valueOf(point));
        Log.d("Time Total", String.valueOf(total_time));
        Log.d("chk catName@ShScr", receive_catName);

//        ArrayList<String> list = getIntent().getStringArrayListExtra("key");
        Toast.makeText(getApplicationContext(), "" + point, Toast.LENGTH_LONG).show();
        // Get the message from the intent

        btnOK = (Button) findViewById(R.id.btnOK);
        txtPoint = (TextView) findViewById(R.id.txtPoint);
        txtTime = (TextView) findViewById(R.id.txtTime);

        txtPoint.setText("Your score" + point);
        txtTime.setText("Time : " + time_sec + "." + time_mil);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sentGame = receive_catName;
                Intent Intent = new Intent(ShowScore.this, ChooseGames.class);
                Intent.putExtra("SENT_Game", sentGame);
//        Log.d("SENT_CAT_ID FROM Add cat", sentCatName);
                startActivity(Intent);
                Log.d("Point after return", String.valueOf(point));

            }
        });


    }

}

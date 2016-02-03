package com.parse.starter.Game;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.starter.Cards.MainActivity;
import com.parse.starter.DatabaseHelp;
import com.parse.starter.R;
import com.parse.starter.Cards.setSingleCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piyapong on 24/12/2558.
 */
public class ChooseGames extends ActionBarActivity implements OnClickListener{
    private Context context = this;

    String receive_catName;
    String return_game;

    ImageButton btnGameMatching;
    ImageButton btnGameTrueOrFalse;
    ImageButton btnGameAnswer;
    Button btnBackCat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_all);

        btnGameMatching = (ImageButton) findViewById(R.id.btnGameMatching);
        btnGameMatching.setOnClickListener(this);
        btnGameTrueOrFalse = (ImageButton) findViewById(R.id.btnGameTrueOrFalse);
        btnGameTrueOrFalse.setOnClickListener(this);
        btnGameAnswer = (ImageButton) findViewById(R.id.btnGameAnswer);
        btnGameAnswer.setOnClickListener(this);
        btnBackCat = (Button) findViewById(R.id.btnBackCat);
        btnBackCat.setOnClickListener(this);



        Bundle bundle = getIntent().getExtras();
        receive_catName =  bundle.getString("SENT_Game");



        //Log.d("name11",receive_catName);

        String receive = receive_catName;
        Log.d("chk recv catName@ChsGm",receive+"");
        final List<setSingleCard> datalist = new ArrayList<>();
        DatabaseHelp PHelp = new DatabaseHelp();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(PHelp.TABLE_NAME_CARDS);
        query.whereEqualTo(PHelp.COLUMN_CARD_CARDSETNAME, receive);



    }

    @Override
    public void onClick(View v) {
        if (v == btnGameMatching){
            selectMatching();
        }
        if (v == btnGameTrueOrFalse){
            selectTrueOrFalse();
        }
        if (v == btnBackCat){
            backToCardset();
        }
        if (v == btnGameAnswer){
            selectAnswer();
        }
    }


    public void selectMatching() {
        String sentGameMatching = receive_catName;
        Intent Intent = new Intent(getApplicationContext(), GameMatching.class);
        Intent.putExtra("Matching", sentGameMatching);
//        Log.d("SENT_CAT_ID FROM Add cat", sentCatName);
        startActivity(Intent);
    }
    public void selectTrueOrFalse() {
        String sentGameTrueOrFalse = receive_catName;
        Intent Intent = new Intent(getApplicationContext(), GameTrueOrFalse.class);
        Intent.putExtra("TrueFalse", sentGameTrueOrFalse);
//        Log.d("SENT_CAT_ID FROM Add cat", sentCatName);
        startActivity(Intent);
    }
    public void selectAnswer() {
        String sentGameAnswer = receive_catName;
        Intent Intent = new Intent(getApplicationContext(), GameAnswer.class);
        Intent.putExtra("Game_Answer", sentGameAnswer);
//        Log.d("SENT_CAT_ID FROM Add cat", sentCatName);
        startActivity(Intent);
    }
    public void backToCardset() {
        String backCard = receive_catName;
        Intent Intent = new Intent(getApplicationContext(), MainActivity.class);
        Intent.putExtra("back_card_set", backCard);
//        Log.d("SENT_CAT_ID FROM Add cat", sentCatName);
        startActivity(Intent);
    }


}

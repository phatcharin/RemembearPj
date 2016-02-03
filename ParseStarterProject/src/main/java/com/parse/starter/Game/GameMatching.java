package com.parse.starter.Game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.starter.Cards.setSingleCard;
import com.parse.starter.DatabaseHelp;
import com.parse.starter.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piyapong on 24/12/2558.
 */
//implements OnClickListener

public class GameMatching extends ActionBarActivity {

    private Context context = this;


    String receive_catName;

    ImageView img_card1,img_card2,img_card3,img_card4;
    ImageView img_card5,img_card6,img_card7,img_card8;
    ImageView img_card9,img_card10,img_card11,img_card12;
    ImageView img_card13,img_card14,img_card15,img_card16;
    TextView txt_card1,txt_card2,txt_card3,txt_card4;
    TextView txt_card5,txt_card6,txt_card7,txt_card8;
    TextView txt_card9,txt_card10,txt_card11,txt_card12;
    TextView txt_card13,txt_card14,txt_card15,txt_card16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_matching);

        img_card1 = (ImageView) findViewById(R.id.img_card1);    txt_card1 = (TextView) findViewById(R.id.txt_card1);
        img_card2 = (ImageView) findViewById(R.id.img_card2);    txt_card2 = (TextView) findViewById(R.id.txt_card2);
        img_card3 = (ImageView) findViewById(R.id.img_card3);    txt_card3 = (TextView) findViewById(R.id.txt_card3);
        img_card4 = (ImageView) findViewById(R.id.img_card4);    txt_card4 = (TextView) findViewById(R.id.txt_card4);
        img_card5 = (ImageView) findViewById(R.id.img_card5);    txt_card5 = (TextView) findViewById(R.id.txt_card5);
        img_card6 = (ImageView) findViewById(R.id.img_card6);    txt_card6 = (TextView) findViewById(R.id.txt_card6);
        img_card7 = (ImageView) findViewById(R.id.img_card7);    txt_card7 = (TextView) findViewById(R.id.txt_card7);
        img_card8 = (ImageView) findViewById(R.id.img_card8);    txt_card8 = (TextView) findViewById(R.id.txt_card8);
        img_card9 = (ImageView) findViewById(R.id.img_card9);    txt_card9 = (TextView) findViewById(R.id.txt_card9);
        img_card10 = (ImageView) findViewById(R.id.img_card10);  txt_card10 = (TextView) findViewById(R.id.txt_card10);
        img_card11 = (ImageView) findViewById(R.id.img_card11);  txt_card11 = (TextView) findViewById(R.id.txt_card11);
        img_card12 = (ImageView) findViewById(R.id.img_card12);  txt_card12 = (TextView) findViewById(R.id.txt_card12);
        img_card13 = (ImageView) findViewById(R.id.img_card13);  txt_card13 = (TextView) findViewById(R.id.txt_card13);
        img_card14 = (ImageView) findViewById(R.id.img_card14);  txt_card14 = (TextView) findViewById(R.id.txt_card14);
        img_card15 = (ImageView) findViewById(R.id.img_card15);  txt_card15 = (TextView) findViewById(R.id.txt_card15);
        img_card16 = (ImageView) findViewById(R.id.img_card16);  txt_card16 = (TextView) findViewById(R.id.txt_card16);


        Bundle bundle = getIntent().getExtras();
        receive_catName =  bundle.getString("Matching");


        //Log.d("name11",receive_catName);

        String receive = receive_catName;
        Log.d("check receive catName",receive);
        final List<setItem> datalist_f = new ArrayList<>();
        final List<setItem> datalist_b = new ArrayList<>();
        final DatabaseHelp PHelp = new DatabaseHelp();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(PHelp.TABLE_NAME_CARDS);
        query.whereEqualTo(PHelp.COLUMN_CARD_CARDSETNAME, receive);

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objectslist, ParseException e) {
                Log.d("check objectslist.size", String.valueOf(objectslist.size()));
                if (e == null) {
                    Log.d("obj size", String.valueOf(objectslist.size()));
                    for (int i = 0; i < objectslist.size(); i++) {
                        String catName = objectslist.get(i).getString(PHelp.COLUMN_CARD_CARDSETNAME);
                        String wFront = objectslist.get(i).getString(PHelp.COLUMN_CARD_TEXTFRONT);
                        ParseFile picF = objectslist.get(i).getParseFile(PHelp.COLUMN_CARD_IMGFRONTFILE);
                        String pathImgF = objectslist.get(i).getString(PHelp.COLUMN_CARD_IMGFRONTPATH);

                        String wBack = objectslist.get(i).getString(PHelp.COLUMN_CARD_TEXTBACK);
                        ParseFile picB = objectslist.get(i).getParseFile(PHelp.COLUMN_CARD_IMGBACKFILE);
                        String pathImgB = objectslist.get(i).getString(PHelp.COLUMN_CARD_IMGBACKPATH);

                        Log.d("check catName", catName + " " + wFront + " " + wBack);

                        setItem cWord = new setItem();
                        setItem fWord = new setItem();
                        setItem bWord = new setItem();
                        // cWord.setItemID(id);
                        cWord.setItemcatName(catName);
                        fWord.setItemWordFront(wFront);
                        fWord.setItemImgFileFront(picF);
                        fWord.setItemPathImgFront(pathImgF);
                        bWord.setItemWordBack(wBack);
                        bWord.setItemImgFileBack(picB);
                        bWord.setItemPathImgBack(pathImgB);

                        datalist_f.add(fWord);
                        datalist_b.add(bWord);
                    }
                    setItem itemFront = datalist_f.get(0);

                    txt_card1.setText(datalist_f.get(0)._itemWordFront);
                    txt_card2.setText(datalist_f.get(1)._itemWordFront);
                    txt_card3.setText(datalist_f.get(2)._itemWordFront);
                    txt_card4.setText(datalist_f.get(3)._itemWordFront);
                    txt_card5.setText(datalist_f.get(4)._itemWordFront);
                    txt_card6.setText(datalist_f.get(5)._itemWordFront);
                    txt_card7.setText(datalist_f.get(6)._itemWordFront);
                    txt_card8.setText(datalist_f.get(7)._itemWordFront);

                    txt_card9.setText(datalist_b.get(0)._itemWordBack);
                    txt_card10.setText(datalist_b.get(1)._itemWordBack);
                    txt_card11.setText(datalist_b.get(2)._itemWordBack);
                    txt_card12.setText(datalist_b.get(3)._itemWordBack);
                    txt_card13.setText(datalist_b.get(4)._itemWordBack);
                    txt_card14.setText(datalist_b.get(5)._itemWordBack);
                    txt_card15.setText(datalist_b.get(6)._itemWordBack);
                    txt_card16.setText(datalist_b.get(7)._itemWordBack);

                    Bitmap bm_1 = BitmapFactory.decodeFile(datalist_f.get(0).getItemPathImgFront());
                    Bitmap bm_2 = BitmapFactory.decodeFile(datalist_f.get(1).getItemPathImgFront());
                    Bitmap bm_3 = BitmapFactory.decodeFile(datalist_f.get(2).getItemPathImgFront());
                    Bitmap bm_4 = BitmapFactory.decodeFile(datalist_f.get(3).getItemPathImgFront());
                    Bitmap bm_5 = BitmapFactory.decodeFile(datalist_f.get(4).getItemPathImgFront());
                    Bitmap bm_6 = BitmapFactory.decodeFile(datalist_f.get(5).getItemPathImgFront());
                    Bitmap bm_7 = BitmapFactory.decodeFile(datalist_f.get(6).getItemPathImgFront());
                    Bitmap bm_8 = BitmapFactory.decodeFile(datalist_f.get(7).getItemPathImgFront());

                    Bitmap bm_9 = BitmapFactory.decodeFile(datalist_b.get(0).getItemPathImgBack());
                    Bitmap bm_10 = BitmapFactory.decodeFile(datalist_b.get(1).getItemPathImgBack());
                    Bitmap bm_11 = BitmapFactory.decodeFile(datalist_b.get(2).getItemPathImgBack());
                    Bitmap bm_12 = BitmapFactory.decodeFile(datalist_b.get(3).getItemPathImgBack());
                    Bitmap bm_13 = BitmapFactory.decodeFile(datalist_b.get(4).getItemPathImgBack());
                    Bitmap bm_14 = BitmapFactory.decodeFile(datalist_b.get(5).getItemPathImgBack());
                    Bitmap bm_15 = BitmapFactory.decodeFile(datalist_b.get(6).getItemPathImgBack());
                    Bitmap bm_16 = BitmapFactory.decodeFile(datalist_b.get(7).getItemPathImgBack());

                    img_card1.setImageBitmap(bm_1);
                    img_card2.setImageBitmap(bm_2);
                    img_card3.setImageBitmap(bm_3);
                    img_card4.setImageBitmap(bm_4);
                    img_card5.setImageBitmap(bm_5);
                    img_card6.setImageBitmap(bm_6);
                    img_card7.setImageBitmap(bm_7);
                    img_card8.setImageBitmap(bm_8);
                    img_card9.setImageBitmap(bm_9);
                    img_card10.setImageBitmap(bm_10);
                    img_card11.setImageBitmap(bm_11);
                    img_card12.setImageBitmap(bm_12);
                    img_card13.setImageBitmap(bm_13);
                    img_card14.setImageBitmap(bm_14);
                    img_card15.setImageBitmap(bm_15);
                    img_card16.setImageBitmap(bm_16);


                }

            }
        });


    }

//    public void onClick(View v) {
//
//    }

    public void intent() {
        String sentCatName = receive_catName;
        Intent Intent = new Intent(getApplicationContext(), ChooseGames.class);
        Intent.putExtra("SENT_CatName", sentCatName);
//        Log.d("SENT_CAT_ID FROM Add cat", sentCatName);
        startActivity(Intent);
    }

}

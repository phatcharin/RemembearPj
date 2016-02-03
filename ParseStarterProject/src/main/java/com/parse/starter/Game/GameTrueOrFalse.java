package com.parse.starter.Game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.starter.DatabaseHelp;
import com.parse.starter.R;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Piyapong on 31/12/2558.
 */

//implements OnClickListener

public class  GameTrueOrFalse extends ActionBarActivity {
    private Context context = this;
//    private List<SingleItem> itemsFront;
//    private List<SingleItem> itemsBack;

    final List<setItem> datalist_f = new ArrayList<>();
    final List<setItem> datalist_b = new ArrayList<>();
    final List<setItem> data_random = new ArrayList<>();

    public setItem itemBack ;
    public setItem itemFront;
    private setItem itemRandom;

    public int numRand;
    private int index = 0;
    private int score = 0;
    private int sizeTotal;

    private int seconds;
    private int millis_s;

    private int total_second;
    private int total_millis;

    int random_01[] = new int[10];

    private int random_play[];

    private int update_time, update_Cnt_Play, update_Cnt_Correct, update_Probability;


    ArrayList<Integer> RandList = new ArrayList<Integer>();
    ArrayList<Integer> RandList_f = new ArrayList<Integer>();
    ArrayList<Integer> RandList_b = new ArrayList<Integer>();

    private String receive_catName;

    TextView showtime1, showtime2, showtime3;
    Button btnTime;
    long starttime = 0;

    ImageView img_f;
    TextView name_f;
    ImageView img_b;
    TextView name_b;
    ImageView btnTrue;
    ImageView btnFalse;


    //  TIMER

    final Handler h = new Handler(new Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            long millis = System.currentTimeMillis() - starttime;
            seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds     = seconds % 60;
            showtime1.setVisibility(View.VISIBLE);
            showtime1.setText(String.format("%02d", minutes));
            return false;
        }
    });

    //runs without timer be reposting self
    Handler h2 = new Handler();
    Runnable run = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - starttime;
            millis_s = (int)(millis%100) ;
            seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds     = seconds % 60;


            showtime3.setVisibility(View.VISIBLE);
            showtime3.setText(String.format(":%02d", millis_s));

            h2.postDelayed(this, 10 );
        }
    };

    public void onPause() {
        super.onPause();
        timer.cancel();
        timer.purge();
        h2.removeCallbacks(run);
        total_second = total_second + seconds;
        total_millis = total_millis + millis_s;


    }

    public void onStart() {
        seconds = 0;
        millis_s = 0;
        super.onStart();
        starttime = System.currentTimeMillis();
        timer = new Timer();
        timer.schedule(new secondTask(), 0, 500);
        h2.postDelayed(run, 0);

    }

    //tells handler to send a message
    class firstTask extends TimerTask {

        @Override
        public void run() {
            h.sendEmptyMessage(0);
        }
    };

    //tells activity to run on ui thread
    class secondTask extends TimerTask {

        @Override
        public void run() {
            GameTrueOrFalse.this.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    long millis = System.currentTimeMillis() - starttime;
                    seconds = (int) (millis / 1000);
//                    int minutes = seconds / 60;
//                    seconds     = seconds % 60;
                    showtime2.setVisibility(View.VISIBLE);
                    showtime2.setText(String.format("Time : %d", seconds));

                }
            });
        }
    };


    Timer timer = new Timer();



    // TIMER

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_trueorfalse);

        img_f = (ImageView) findViewById(R.id.ivImg_truefalse);
        img_b = (ImageView) findViewById(R.id.ivImg_truefalse2);
        name_f = (TextView) findViewById(R.id.tvName_truefalse);
        name_b = (TextView) findViewById(R.id.tvName_truefalse2);
        btnTrue = (ImageView) findViewById(R.id.btnTrue);
        btnFalse = (ImageView) findViewById(R.id.btnFalse);

        showtime1 = (TextView)findViewById(R.id.showtime1);
        showtime2 = (TextView)findViewById(R.id.showtime2);
        showtime3 = (TextView)findViewById(R.id.showtime3);
//        btnTime = (Button)findViewById(R.id.btnTime);

        final MediaPlayer sound_true = MediaPlayer.create(this, R.raw.sound_true);
        final MediaPlayer sound_false = MediaPlayer.create(this, R.raw.sound_false);

        Bundle bundle = getIntent().getExtras();
        receive_catName =  bundle.getString("TrueFalse");

//        btnTime.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Button b = (Button)v;
//                if(b.getText().equals("stop")){
//                    timer.cancel();
//                    timer.purge();
//                    h2.removeCallbacks(run);
//                    b.setText("start");
//                }else{
//                    starttime = System.currentTimeMillis();
//                    timer = new Timer();
//                    timer.schedule(new firstTask(), 0,500);
//                    timer.schedule(new secondTask(),  0,500);
//                    h2.postDelayed(run, 0);
//                    b.setText("stop");
//                }
//            }
//        });


        btnTrue.setOnClickListener(new OnClickListener(){

            public void onClick(View v) {

                onPause();

                itemFront = datalist_f.get(RandList_f.get(index));
                if (random_01[index] == 0) {
                    if (RandList_f.get(index) == RandList_b.get(index)) {
                        if (index == 0) {
                            itemBack = datalist_b.get(RandList_b.get(index+1));
                        }else{
                            itemBack = datalist_b.get(RandList_b.get(index-1));
                        }
                    }else {
                        itemBack = datalist_b.get(RandList_b.get(index));
                    }
                }else {
                    itemBack = datalist_b.get(RandList_f.get(index));
                }



                    if (itemFront.getItemID().equalsIgnoreCase(itemBack.getItemID())){
                        score++;
                        index++;


                        int time_all = (itemFront.getItemTimeAvr()*itemFront.getItemCountPlay()) + millis_s + (seconds*100) ;
                        int prob_old = Math.round(itemFront.getItemProb()*100f) ;

                        Log.d("Update time", time_all + "" );


                        update_Cnt_Play = itemFront.getItemCountPlay() + 1 ;
                        update_Cnt_Correct = itemFront.getItemCountCorr() + 1 ;
                        update_time =   time_all / update_Cnt_Play ;
                        update_Probability = ((prob_old*itemFront.getItemCountPlay()) + 100)/update_Cnt_Play;

                        final DatabaseHelp PHelp = new DatabaseHelp();
                        ParseQuery<ParseObject> query = ParseQuery.getQuery(PHelp.TABLE_NAME_CARDS);
                        // Retrieve the object by id
                        query.getInBackground( itemFront.getItemID() , new GetCallback<ParseObject>() {
                            public void done(ParseObject update_prob , ParseException e) {
                                if (e == null) {
                                    Log.d("ID Update", itemFront.getItemID() );
                                    // Now let's update it with some new data. In this case, only cheatMode and score
                                    // will get sent to the Parse Cloud. playerName hasn't changed.
                                    update_prob.put(PHelp.COLUMN_CARD_Probability_Ans, update_Probability);
                                    update_prob.put(PHelp.COLUMN_CARD_CountPlay_Ans, update_Cnt_Play);
                                    update_prob.put(PHelp.COLUMN_CARD_CountCorrect_Ans, update_Cnt_Correct);
                                    update_prob.put(PHelp.COLUMN_CARD_TimeAvr_Ans, update_time);
                                    update_prob.saveInBackground();
                                }
                            }
                        });

                        sound_true.start();
                        nextQuestion();
                    }else{
                        index++;

                        int time_all = (itemFront.getItemTimeAvr()*itemFront.getItemCountPlay()) + millis_s + (seconds*100) ;
                        int prob_old = Math.round(itemFront.getItemProb()*100f) ;


                        update_Cnt_Play = itemFront.getItemCountPlay() + 1 ;
//                        update_Cnt_Correct = itemFront.getItemCountCorr() + 1 ;
                        update_time =   time_all / update_Cnt_Play ;
                        update_Probability = ((prob_old*itemFront.getItemCountPlay()) )/update_Cnt_Play;

                        final DatabaseHelp PHelp = new DatabaseHelp();
                        ParseQuery<ParseObject> query = ParseQuery.getQuery(PHelp.TABLE_NAME_CARDS);
                        // Retrieve the object by id
                        query.getInBackground( itemFront.getItemID() , new GetCallback<ParseObject>() {
                            public void done(ParseObject update_prob , ParseException e) {
                                if (e == null) {
                                    Log.d("ID Update", itemFront.getItemID() );
                                    // Now let's update it with some new data. In this case, only cheatMode and score
                                    // will get sent to the Parse Cloud. playerName hasn't changed.
                                    update_prob.put(PHelp.COLUMN_CARD_Probability_Ans, update_Probability);
                                    update_prob.put(PHelp.COLUMN_CARD_CountPlay_Ans, update_Cnt_Play);
                                    update_prob.put(PHelp.COLUMN_CARD_CountCorrect_Ans, update_Cnt_Correct);
                                    update_prob.put(PHelp.COLUMN_CARD_TimeAvr_Ans, update_time);
                                    update_prob.saveInBackground();
                                }
                            }
                        });

                        sound_false.start();
                        nextQuestion();
                    }

            }
        });

        btnFalse.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                onPause();

                itemFront = datalist_f.get(RandList_f.get(index));
                if (random_01[index] == 0) {
                    if (RandList_f.get(index) == RandList_b.get(index)) {
                        if (index == 0) {
                            itemBack = datalist_b.get(RandList_b.get(index + 1));
                        } else {
                            itemBack = datalist_b.get(RandList_b.get(index - 1));
                        }
                    } else {
                        itemBack = datalist_b.get(RandList_b.get(index));
                    }
                } else {
                    itemBack = datalist_b.get(RandList_f.get(index));
                }


                if (!itemFront.getItemID().equalsIgnoreCase(itemBack.getItemID())) {

                    int time_all = (itemFront.getItemTimeAvr()*itemFront.getItemCountPlay()) + millis_s + (seconds*100) ;
                    int prob_old = Math.round(itemFront.getItemProb()*100f) ;

                    Log.d("count correct retri",itemFront.getItemCountCorr()+"");
                    Log.d("time now",millis_s + (seconds*100)+"");

                    update_Cnt_Play = itemFront.getItemCountPlay() + 1 ;
                    update_Cnt_Correct = itemFront.getItemCountCorr() + 1 ;
                    update_time =   time_all / update_Cnt_Play ;
                    update_Probability = ((prob_old*itemFront.getItemCountPlay()) + 100)/update_Cnt_Play;

                    final DatabaseHelp PHelp = new DatabaseHelp();
                    ParseQuery<ParseObject> query = ParseQuery.getQuery(PHelp.TABLE_NAME_CARDS);
                    // Retrieve the object by id
                    query.getInBackground( itemFront.getItemID() , new GetCallback<ParseObject>() {
                        public void done(ParseObject update_prob , ParseException e) {
                            if (e == null) {
                                Log.d("ID Update", itemFront.getItemID() );
                                // Now let's update it with some new data. In this case, only cheatMode and score
                                // will get sent to the Parse Cloud. playerName hasn't changed.
                                update_prob.put(PHelp.COLUMN_CARD_Probability_Ans, update_Probability);
                                update_prob.put(PHelp.COLUMN_CARD_CountPlay_Ans, update_Cnt_Play);
//                                    update_prob.put("CountCorrect_TF", update_Cnt_Correct);
                                update_prob.put(PHelp.COLUMN_CARD_TimeAvr_Ans, update_time);
                                update_prob.saveInBackground();
                            }
                        }
                    });


                    sound_true.start();
                    score++;
                    index++;
                    nextQuestion();
                } else {

                    int time_all = (itemFront.getItemTimeAvr()*itemFront.getItemCountPlay()) + millis_s + (seconds*100) ;
                    int prob_old = Math.round(itemFront.getItemProb()*100f) ;


                    update_Cnt_Play = itemFront.getItemCountPlay() + 1 ;
//                        update_Cnt_Correct = itemFront.getItemCountCorr() + 1 ;
                    update_time =   time_all / update_Cnt_Play ;
                    update_Probability = ((prob_old*itemFront.getItemCountPlay()) )/update_Cnt_Play;

                    final DatabaseHelp PHelp = new DatabaseHelp();
                    ParseQuery<ParseObject> query = ParseQuery.getQuery(PHelp.TABLE_NAME_CARDS);
                    // Retrieve the object by id
                    query.getInBackground( itemFront.getItemID() , new GetCallback<ParseObject>() {
                        public void done(ParseObject update_prob , ParseException e) {
                            if (e == null) {
                                Log.d("ID Update", itemFront.getItemID() );
                                // Now let's update it with some new data. In this case, only cheatMode and score
                                // will get sent to the Parse Cloud. playerName hasn't changed.
                                update_prob.put(PHelp.COLUMN_CARD_Probability_Ans, update_Probability);
                                update_prob.put(PHelp.COLUMN_CARD_CountPlay_Ans, update_Cnt_Play);
//                                    update_prob.put("CountCorrect_TF", update_Cnt_Correct);
                                update_prob.put(PHelp.COLUMN_CARD_TimeAvr_Ans, update_time);
                                update_prob.saveInBackground();
                            }
                        }
                    });

                    sound_false.start();
                    index++;
                    nextQuestion();
                }

            }
        });




        String receive = receive_catName;
        Log.d("check receive catName",receive);

        final DatabaseHelp PHelp = new DatabaseHelp();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(PHelp.TABLE_NAME_CARDS);
        query.whereEqualTo(PHelp.COLUMN_CARD_CARDSETNAME, receive);

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objectslist, ParseException e) {
                Log.d("check objectslist.size", String.valueOf(objectslist.size()));

                for (numRand = 0; numRand < objectslist.size(); numRand++) {
                    RandList.add(numRand);
                }
                Collections.shuffle(RandList);
                if (objectslist.size() > 9) {
                    for (numRand = 0; numRand < 10; numRand++) {
                        RandList_f.add(RandList.get(numRand));
                        RandList_b.add(RandList.get(numRand));
//                    Log.d("Random Number ", RandList.get(numRand).toString());
                    }
                } else {
                    for (numRand = 0; numRand < objectslist.size(); numRand++) {
                        RandList_f.add(RandList.get(numRand));
                        RandList_b.add(RandList.get(numRand));
//                    Log.d("Random Number ", RandList.get(numRand).toString());
                    }
                }


                Collections.shuffle(RandList_f);
                Collections.shuffle(RandList_b);
                Log.d("Random Number F", RandList_f.get(0).toString());
                Log.d("Random Number B", RandList_b.get(0).toString());

                Random rn = new Random();

                for (int i = 0; i < 10; i++) {
                    random_01[i] = rn.nextInt(2) + 0;

                }
                for (int t = 0; t < 10; t++) {
                    Log.d("Random 01 :", random_01[t] + "");
                }

                if (e == null) {
                    Log.d("obj size", String.valueOf(objectslist.size()));
                    sizeTotal = objectslist.size();
                    for (int i = 0; i < objectslist.size(); i++) {
                        String catName = objectslist.get(i).getString(PHelp.COLUMN_CARD_CARDSETNAME);
                        int prob = objectslist.get(i).getInt(PHelp.COLUMN_CARD_Probability_TF);
                        float prob100 = prob / 100f;
                        Log.d("Prob100", prob100 + "");
                        int countPlay = objectslist.get(i).getInt(PHelp.COLUMN_CARD_CountPlay_TF);
                        int countCorrect = objectslist.get(i).getInt(PHelp.COLUMN_CARD_CountCorrect_TF);
                        int timeAvr = objectslist.get(i).getInt(PHelp.COLUMN_CARD_TimeAvr_TF);


                        String wFront = objectslist.get(i).getString(PHelp.COLUMN_CARD_TEXTFRONT);
                        ParseFile picF = objectslist.get(i).getParseFile(PHelp.COLUMN_CARD_IMGFRONTFILE);
                        String pathImgF = objectslist.get(i).getString(PHelp.COLUMN_CARD_IMGFRONTPATH);
                        String idF = objectslist.get(i).getObjectId();

                        String wBack = objectslist.get(i).getString(PHelp.COLUMN_CARD_TEXTBACK);
                        ParseFile picB = objectslist.get(i).getParseFile(PHelp.COLUMN_CARD_IMGBACKFILE);
                        String pathImgB = objectslist.get(i).getString(PHelp.COLUMN_CARD_IMGBACKPATH);
                        String idB = objectslist.get(i).getObjectId();

                        Log.d("check catName", catName + " " + wFront + " " + wBack);

                        setItem cWord = new setItem();
                        setItem fWord = new setItem();
                        setItem bWord = new setItem();
                        // cWord.setItemID(id);
                        cWord.setItemcatName(catName);
                        cWord.setItemProb(prob100);
                        cWord.setItemCount(countPlay);
                        cWord.setItemCountCorr(countCorrect);
                        cWord.setItemTimeAvr(timeAvr);

                        fWord.setItemcatName(catName);
                        fWord.setItemProb(prob100);
                        fWord.setItemCount(countPlay);
                        fWord.setItemCountCorr(countCorrect);
                        fWord.setItemTimeAvr(timeAvr);


                        fWord.setItemWordFront(wFront);
                        fWord.setItemImgFileFront(picF);
                        fWord.setItemPathImgFront(pathImgF);
                        fWord.setItemID(idF);


                        bWord.setItemWordBack(wBack);
                        bWord.setItemImgFileBack(picB);
                        bWord.setItemPathImgBack(pathImgB);
                        bWord.setItemID(idB);

                        datalist_f.add(fWord);
                        datalist_b.add(bWord);
                        data_random.add(cWord);

//                        Log.d("IDf", fWord._itemID  + "");
//                        Log.d("wf", fWord._itemWordFront  + "");
//                        Log.d("IDb", bWord._itemID + "");
//                        Log.d("wb", bWord._itemWordBack + "");

                    }

                    for (int c = 0; c < data_random.size(); c++) {
                        Log.d("Prob", data_random.get(c).getItemProb() + "");
                        Log.d("Count", data_random.get(c).getItemCountPlay() + "");
                    }

                    img_f.setVisibility(View.INVISIBLE);
                    img_b.setVisibility(View.INVISIBLE);
                    name_f.setVisibility(View.INVISIBLE);
                    name_b.setVisibility(View.INVISIBLE);
                    showtime1.setVisibility(View.INVISIBLE);
                    showtime2.setVisibility(View.INVISIBLE);
                    showtime3.setVisibility(View.INVISIBLE);


                    // Random  pri

                    float countPlay[] = new float[objectslist.size()];
                    float prob[] = new float[objectslist.size()];
                    float prob100[] = new float[objectslist.size()];
                    float sumProb100[] = new float[objectslist.size()];

                    if (objectslist.size() >= 10) {
                        random_play = new int[10];
                    } else {
                        random_play = new int[objectslist.size()];
                    }
                    float sumProb = 0;

                    for (int r = 0; r < objectslist.size(); r++) {
                        itemRandom = data_random.get(r);
                        countPlay[r] = itemRandom.getItemCountPlay();
                        prob[r] = itemRandom.getItemProb();
                        Log.d("Each prop", String.valueOf(prob[r]));
                        sumProb = sumProb + prob[r];
                    }

                    Log.d("sum all prop", String.valueOf(sumProb));


                    for (int r = 0; r < objectslist.size(); r++) {
                        if (r == 0) {
                            prob100[r] = prob[r] / sumProb;
                            sumProb100[r] = prob100[r];
                        } else {
                            prob100[r] = prob[r] / sumProb;
                            sumProb100[r] = sumProb100[r - 1] + prob100[r];
                        }
                        Log.d("sum Prop 100%", String.valueOf(r) + "   " + String.valueOf(sumProb100[r]));

                    }


                    Random random_flt = new Random();


                    int itemNum;
                    if (objectslist.size() >= 10) {
                        itemNum = 10;
                    } else {
                        itemNum = objectslist.size();
                    }

                    for (int r = 0; r < itemNum; r++) {

                        int find;
                        float random_temp = random_flt.nextFloat() * (1.0000f - 0.0000f) + 0.0000f;
                        Log.d("Random float", String.valueOf(random_temp));

                        for (find = 0; find < objectslist.size(); find++) {

                            if (random_temp < sumProb100[find]) {
                                random_play[r] = find;
                                Log.d("get rand num", String.valueOf(find));
                                break;
                            }

//                            for (int check = 0; check < itemNum; check++) {
//
//                                if (r == 0 && find == 0){
//                                    break;
//                                }else if(random_play[check] == find) {
//                                    r = r - 1 ;
//                                }
//                            }
                        }


                    }

                    //  Random w/ pri

                    Log.d("Index First", String.valueOf(index));

                    itemFront = datalist_f.get(random_play[index]);
                    if (random_01[index] == 0) {
                        if (random_play[index] == RandList_b.get(index)) {
                            if (index == 0) {
                                itemBack = datalist_b.get(RandList_b.get(index + 1));
                            } else {
                                itemBack = datalist_b.get(RandList_b.get(index - 1));
                            }
                        } else {
                            itemBack = datalist_b.get(RandList_b.get(index));
                        }
                    } else {
                        itemBack = datalist_b.get(random_play[index]);
                    }


                    if (itemFront.getItemWordFront() != null) {
                        name_f.setVisibility(View.VISIBLE);
                        name_f.setText(datalist_f.get(random_play[index])._itemWordFront);
                    }
                    if (itemFront.getItemPathImgFront() != null) {
                        img_f.setVisibility(View.VISIBLE);
                        Bitmap bm_f = BitmapFactory.decodeFile(itemFront.getItemPathImgFront());
                        img_f.setImageBitmap(bm_f);
                    }
                    if (itemBack.getItemWordBack() != null) {
                        name_b.setVisibility(View.VISIBLE);
                        if (random_01[index] == 0) {
                            if (random_play[index] == RandList_b.get(index)) {
                                if (index == 0) {
                                    name_b.setText(datalist_b.get(RandList_b.get(index + 1))._itemWordBack);
                                } else {
                                    name_b.setText(datalist_b.get(RandList_b.get(index - 1))._itemWordBack);
                                }
                            } else {
                                name_b.setText(datalist_b.get(RandList_b.get(index))._itemWordBack);
                            }
                        } else {
                            name_b.setText(datalist_b.get(random_play[index])._itemWordBack);
                        }

                    }
                    if (itemBack.getItemPathImgBack() != null) {
                        img_b.setVisibility(View.VISIBLE);
                        Bitmap bm_b = BitmapFactory.decodeFile(itemBack.getItemPathImgBack());
                        img_b.setImageBitmap(bm_b);
                    }
                    Log.d("ID prepare", itemFront.getItemID());

                    onStart();

//                    ParseHelp PHelp = new ParseHelp();
//
//                    ParseQuery<ParseObject> query = ParseQuery.getQuery(PHelp.TABLE_NAME_DATA);
//                        // Retrieve the object by id
//                    query.getInBackground( itemFront.getItemID() , new GetCallback<ParseObject>() {
//                        public void done(ParseObject update_prob , ParseException e) {
//                            if (e == null) {
//                                Log.d("ID Update", itemFront.getItemID() );
//                                // Now let's update it with some new data. In this case, only cheatMode and score
//                                // will get sent to the Parse Cloud. playerName hasn't changed.
//                                update_prob.put("Probability_TF", 2559);
//                                update_prob.put("CountPlay_TF", 1024);
//                                update_prob.put("CountCorrect_TF", 1024);
//                                update_prob.put("TimeAvr_TF", 1024);
//                                update_prob.saveInBackground();
//                            }
//                        }
//                    });


                }


            }
        });



    }

//    public void onClick(View v) {
//
//    }

//    public void intent() {
//        String sentCatName = receive_catName;
//        Intent Intent = new Intent(getApplicationContext(), ChooseGames.class);
//        Intent.putExtra("SENT_CatName", sentCatName);
////        Log.d("SENT_CAT_ID FROM Add cat", sentCatName);
//        startActivity(Intent);
//    }

    private void nextQuestion() {

        img_f.setVisibility(View.INVISIBLE);
        img_b.setVisibility(View.INVISIBLE);
        name_f.setVisibility(View.INVISIBLE);
        name_b.setVisibility(View.INVISIBLE);

        Log.d("Index Now", String.valueOf(index));
        Log.d("Score Now", String.valueOf(score));
        if (sizeTotal > 9){  // word >=10
            if (index > 9) {   //  finish
                index = 0;
                Log.d("Index Finish", String.valueOf(index));
                Log.d("Point sent", String.valueOf(score));

                Intent intent = new Intent(getApplicationContext(), ShowScore.class);
                intent.putExtra("true_false_score", score);
                intent.putExtra("true_false_time", (total_second*100)+total_millis);
                String sentGameTrueOrFalse = receive_catName;
                intent.putExtra("TrueFalse", sentGameTrueOrFalse);
                startActivity(intent);
            } else {  // set word for word >= 10

                onStart();

                itemFront = datalist_f.get(random_play[index]);
                if (random_01[index] == 0) {
                    if (random_play[index] == RandList_b.get(index)) {
                        if (index == 0) {
                            itemBack = datalist_b.get(RandList_b.get(index + 1));
                        } else {
                            itemBack = datalist_b.get(RandList_b.get(index - 1));
                        }
                    } else {
                        itemBack = datalist_b.get(RandList_b.get(index));
                    }
                } else {
                    itemBack = datalist_b.get(random_play[index]);
                }


                if (itemFront.getItemWordFront() != null) {
                    name_f.setVisibility(View.VISIBLE);
                    name_f.setText(datalist_f.get(random_play[index])._itemWordFront);
                }
                if (itemFront.getItemPathImgFront() != null) {
                    img_f.setVisibility(View.VISIBLE);
                    Bitmap bm_f = BitmapFactory.decodeFile(itemFront.getItemPathImgFront());
                    img_f.setImageBitmap(bm_f);
                }
                if (itemBack.getItemWordBack() != null) {
                    name_b.setVisibility(View.VISIBLE);
                    if (random_01[index] == 0) {
                        if (random_play[index] == RandList_b.get(index)) {
                            if (index == 0) {
                                name_b.setText(datalist_b.get(RandList_b.get(index + 1))._itemWordBack);
                            } else {
                                name_b.setText(datalist_b.get(RandList_b.get(index - 1))._itemWordBack);
                            }
                        } else {
                            name_b.setText(datalist_b.get(RandList_b.get(index))._itemWordBack);
                        }
                    } else {
                        name_b.setText(datalist_b.get(random_play[index])._itemWordBack);
                    }

                }
                if (itemBack.getItemPathImgBack() != null) {
                    img_b.setVisibility(View.VISIBLE);
                    Bitmap bm_b = BitmapFactory.decodeFile(itemBack.getItemPathImgBack());
                    img_b.setImageBitmap(bm_b);
                }
            }
        }else{      //  word < 10
            if (index > sizeTotal-1) { // finish
                index = 0;
                Log.d("Index Finish", String.valueOf(index));
                Log.d("Point sent", String.valueOf(score));

                Intent intent = new Intent(getApplicationContext(), ShowScore.class);
                intent.putExtra("true_false_score", score);
                intent.putExtra("true_false_time", (total_second*100)+total_millis);
                String sentGameTrueOrFalse = receive_catName;
                intent.putExtra("TrueFalse", sentGameTrueOrFalse);
                startActivity(intent);
            } else { // set word for  word < 10

                onStart();

                itemFront = datalist_f.get(random_play[index]);
                if (random_01[index] == 0) {
                    if (random_play[index] == RandList_b.get(index)) {
                        if (index == 0) {
                            itemBack = datalist_b.get(RandList_b.get(index + 1));
                        } else {
                            itemBack = datalist_b.get(RandList_b.get(index - 1));
                        }
                    } else {
                        itemBack = datalist_b.get(RandList_b.get(index));
                    }
                } else {
                    itemBack = datalist_b.get(random_play[index]);
                }


                if (itemFront.getItemWordFront() != null) {
                    name_f.setVisibility(View.VISIBLE);
                    name_f.setText(datalist_f.get(random_play[index])._itemWordFront);
                }
                if (itemFront.getItemPathImgFront() != null) {
                    img_f.setVisibility(View.VISIBLE);
                    Bitmap bm_f = BitmapFactory.decodeFile(itemFront.getItemPathImgFront());
                    img_f.setImageBitmap(bm_f);
                }
                if (itemBack.getItemWordBack() != null) {
                    name_b.setVisibility(View.VISIBLE);
                    if (random_01[index] == 0) {
                        if (random_play[index] == RandList_b.get(index)) {
                            if (index == 0) {
                                name_b.setText(datalist_b.get(RandList_b.get(index + 1))._itemWordBack);
                            } else {
                                name_b.setText(datalist_b.get(RandList_b.get(index - 1))._itemWordBack);
                            }
                        } else {
                            name_b.setText(datalist_b.get(RandList_b.get(index))._itemWordBack);
                        }
                    } else {
                        name_b.setText(datalist_b.get(random_play[index])._itemWordBack);
                    }

                }
                if (itemBack.getItemPathImgBack() != null) {
                    img_b.setVisibility(View.VISIBLE);
                    Bitmap bm_b = BitmapFactory.decodeFile(itemBack.getItemPathImgBack());
                    img_b.setImageBitmap(bm_b);
                }
            }

        }

    }

}

/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter.Cards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.starter.DatabaseHelp;
import com.parse.starter.Game.ChooseGames;
import com.parse.starter.MenubarHome;
import com.parse.starter.R;

import java.util.ArrayList;
import java.util.List;


public class ShowCardActivity extends ActionBarActivity {

  private Toolbar mToolbarBottom;

  private Context context = this;
  RecyclerView recyclerView;
  Button btnAdd;

  String SENT_CardsetName = "SENT_CardsetName";
  String cardsetName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_show_card);


    Bundle bundle = getIntent().getExtras();
    String receive_cardsetName =  bundle.getString(SENT_CardsetName);
    cardsetName = receive_cardsetName;

    btnAdd = (Button) findViewById(R.id.btnAdd);
    btnAdd.findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent it = new Intent(getApplicationContext(), AddCardActivity.class);
        it.putExtra(SENT_CardsetName, cardsetName);
        startActivity(it);
        finish();
      }
    });


    mToolbarBottom = (Toolbar) findViewById(R.id.inc_tb_showcard);
    mToolbarBottom.findViewById(R.id.iv_share3).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent it = new Intent(getApplicationContext(), GameHomeActivity.class);
        startActivity(it);
      }
    });
    mToolbarBottom.findViewById(R.id.iv_game3).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent it = new Intent(getApplicationContext(), ChooseGames.class);
        it.putExtra("SENT_Game", cardsetName);
        startActivity(it);
      }
    });
    mToolbarBottom.findViewById(R.id.iv_home3).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent it = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(it);
      }
    });
    mToolbarBottom.findViewById(R.id.iv_static3).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent it = new Intent(getApplicationContext(), GameHomeActivity.class);
        startActivity(it);
      }
    });
    mToolbarBottom.findViewById(R.id.iv_setting3).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent it = new Intent(getApplicationContext(), GameHomeActivity.class);
        startActivity(it);
      }
    });
    mToolbarBottom.inflateMenu(R.menu.menu_bottom);

    ParseAnalytics.trackAppOpenedInBackground(getIntent());

    Log.d("check : ", "OK");
    final List<setSingleCard> datalist = new ArrayList<>();
    final DatabaseHelp PHelp = new DatabaseHelp();
    Log.d("ERROR CARDSETNAME",cardsetName);

    ParseQuery<ParseObject> query = ParseQuery.getQuery(PHelp.TABLE_NAME_CARDS);
    query.whereEqualTo(PHelp.COLUMN_CARD_CARDSETNAME,cardsetName);
    query.findInBackground(new FindCallback<ParseObject>() {
      public void done(List<ParseObject> cardlist, ParseException e) {
        Log.d("check objectslist.size", String.valueOf(cardlist.size()));
        if (e == null) {
          Log.d("name111111", String.valueOf(cardlist.size()));
          for (int i = 0; i < cardlist.size(); i++) {
            String idCard = cardlist.get(i).getObjectId();
            String cardSetName = cardlist.get(i).getString(PHelp.COLUMN_CARD_CARDSETNAME);
            String textFront = cardlist.get(i).getString(PHelp.COLUMN_CARD_TEXTFRONT);
            String textBack = cardlist.get(i).getString(PHelp.COLUMN_CARD_TEXTBACK);
            String pathImgF = cardlist.get(i).getString(PHelp.COLUMN_CARD_IMGFRONTPATH);
            String pathImgB = cardlist.get(i).getString(PHelp.COLUMN_CARD_IMGBACKPATH);
            ParseFile pfImgF = cardlist.get(i).getParseFile(PHelp.COLUMN_CARD_IMGFRONTFILE);
            ParseFile pfImgB = cardlist.get(i).getParseFile(PHelp.COLUMN_CARD_IMGBACKFILE);
            int priority = cardlist.get(i).getInt(PHelp.COLUMN_CARD_PRIORITY);


            Log.d("check textFrint", "textFront");

            setSingleCard setCard = new setSingleCard();
            setCard.set_IdCard(idCard);
            setCard.setCardTextF(textFront);
            setCard.setCardTextB(textBack);
            setCard.setImgF(pfImgF);
            setCard.setImgB(pfImgB);
            setCard.setPathImgF(pathImgF);
            setCard.setPathImgB(pathImgB);
            setCard.setPriority(priority);

            // setCardSet.setPathIcon(iconPath);


            datalist.add(setCard);
          }
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_cardset);
        recyclerView.setAdapter(new AdaptersCard(context, datalist));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        // recyclerView.setLayoutManager(new LinearLayoutManager(context));

        Log.d("vamvam", "test");

      }
    });

  }
}
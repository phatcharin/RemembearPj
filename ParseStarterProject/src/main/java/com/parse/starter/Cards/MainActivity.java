/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter.Cards;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.parse.FindCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.starter.DatabaseHelp;
import com.parse.starter.MenubarAddCardSet;
import com.parse.starter.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

  private Toolbar mToolbarBottom;

  private Context context = this;
  RecyclerView recyclerView;

//  class AAA {
//    public AAA(Activity activity){
//      mToolbarBottom = (Toolbar) activity.findViewById(R.id.inc_tb_bottom);
//      mToolbarBottom.findViewById(R.id.iv_share).setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//          Intent it = new Intent(getApplicationContext(), GameHomeActivity.class);
//          startActivity(it);
//        }
//      });
//      mToolbarBottom.findViewById(R.id.iv_game).setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//          Intent it = new Intent(getApplicationContext(), GameHomeActivity.class);
//          startActivity(it);
//        }
//      });
//      mToolbarBottom.findViewById(R.id.iv_home).setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//          Intent it = new Intent(getApplicationContext(), AddCardSetActivity.class);
//          startActivity(it);
//        }
//      });
//      mToolbarBottom.findViewById(R.id.iv_static).setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//          Intent it = new Intent(getApplicationContext(), GameHomeActivity.class);
//          startActivity(it);
//        }
//      });
//      mToolbarBottom.findViewById(R.id.iv_setting).setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//          Intent it = new Intent(getApplicationContext(), GameHomeActivity.class);
//          startActivity(it);
//        }
//      });
//      mToolbarBottom.inflateMenu(R.menu.menu_bottom);
//
//    }
//  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    MenubarAddCardSet menubar = new MenubarAddCardSet(this);

    ParseAnalytics.trackAppOpenedInBackground(getIntent());

   Log.d("check : ", "OK");
    final List<setSigleCardset> datalist = new ArrayList<>();
    final DatabaseHelp PHelp = new DatabaseHelp();

    ParseQuery<ParseObject> query = ParseQuery.getQuery(PHelp.TABLE_NAME_DATA);
    query.whereNotEqualTo(PHelp.COLUMN_CARDSET_NAME, "%^&*");
    query.findInBackground(new FindCallback<ParseObject>() {
      public void done(List<ParseObject> cardSetlist, ParseException e) {
        Log.d("check objectslist.size", String.valueOf(cardSetlist.size()));
        if (e == null) {
          Log.d("name111111", String.valueOf(cardSetlist.size()));
          for (int i = 0; i < cardSetlist.size(); i++) {
            String cardSetName = cardSetlist.get(i).getString(PHelp.COLUMN_CARDSET_NAME);
            String  cardSetTitle= cardSetlist.get(i).getString(PHelp.COLUMN_CARDSET_TITLE);
            ParseFile iconFile = cardSetlist.get(i).getParseFile(PHelp.COLUMN_CARDSET_ICONFILE);
            //String iconPath = cardSetlist.get(i).getString(PHelp.COLUMN_CARDSET_ICONPATH);

            Log.d("check catName", cardSetName + " " + cardSetTitle + " ");

            setSigleCardset setCardSet = new setSigleCardset();
            // cWord.setItemID(id);
            setCardSet.setCardsetName(cardSetName);
            setCardSet.setCardsetTitle(cardSetTitle);
            setCardSet.setIcon(iconFile);
           // setCardSet.setPathIcon(iconPath);


            datalist.add(setCardSet);
          }
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_cardset);
        recyclerView.setAdapter(new AdaptersCardset(context, datalist));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        // recyclerView.setLayoutManager(new LinearLayoutManager(context));
        Log.d("vamvam", "test");

      }
    });

  }


}
 /* mToolbarBottom.setOnMenuItemClickListener(new OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem menuItem) {
        Intent it = null;
        switch (menuItem.getItemId()){
          case R.id.action_game:
            it = new Intent(getApplicationContext(),GameHomeActivity.class);

            break;
        }
        startActivity(it);
        return true;
      }
    });*/
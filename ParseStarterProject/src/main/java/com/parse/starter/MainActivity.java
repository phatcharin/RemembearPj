/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.parse.ParseAnalytics;


public class MainActivity extends ActionBarActivity {

  private Toolbar mToolbarBottom;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mToolbarBottom = (Toolbar) findViewById(R.id.inc_tb_bottom);
    mToolbarBottom.findViewById(R.id.iv_share).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent it = new Intent(getApplicationContext(), GameHomeActivity.class);
        startActivity(it);
      }
    });
    mToolbarBottom.findViewById(R.id.iv_game).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent it = new Intent(getApplicationContext(), GameHomeActivity.class);
        startActivity(it);
      }
    });
    mToolbarBottom.findViewById(R.id.iv_home).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent it = new Intent(getApplicationContext(), GameHomeActivity.class);
        startActivity(it);
      }
    });
    mToolbarBottom.findViewById(R.id.iv_static).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent it = new Intent(getApplicationContext(), GameHomeActivity.class);
        startActivity(it);
      }
    });
    mToolbarBottom.findViewById(R.id.iv_setting).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent it = new Intent(getApplicationContext(), GameHomeActivity.class);
        startActivity(it);
      }
    });
    mToolbarBottom.inflateMenu(R.menu.menu_bottom);

    ParseAnalytics.trackAppOpenedInBackground(getIntent());
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
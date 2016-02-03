package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.parse.starter.Cards.AddCardSetActivity;
import com.parse.starter.Cards.GameHomeActivity;
import com.parse.starter.Cards.MainActivity;

/**
 * Created by vamnoize on 3/2/2559.
 */
public class MenubarHome {
    public MenubarHome(final Activity activity){
        Toolbar mToolbarBottom;

        mToolbarBottom = (Toolbar) activity.findViewById(R.id.inc_tb_homebottom);

        mToolbarBottom.findViewById(R.id.iv_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(activity.getApplicationContext(), GameHomeActivity.class);
                activity.startActivity(it);
            }
        });
        mToolbarBottom.findViewById(R.id.iv_game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(activity.getApplicationContext(), GameHomeActivity.class);
                activity.startActivity(it);
            }
        });
        mToolbarBottom.findViewById(R.id.iv_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(activity.getApplicationContext(), MainActivity.class);
                activity.startActivity(it);
            }
        });
        mToolbarBottom.findViewById(R.id.iv_static).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(activity.getApplicationContext(), GameHomeActivity.class);
                activity.startActivity(it);
            }
        });
        mToolbarBottom.findViewById(R.id.iv_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(activity.getApplicationContext(), GameHomeActivity.class);
                activity.startActivity(it);
            }
        });
        mToolbarBottom.inflateMenu(R.menu.menu_bottom);

    }

}

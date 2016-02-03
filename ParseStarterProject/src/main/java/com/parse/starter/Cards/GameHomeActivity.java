package com.parse.starter.Cards;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.parse.starter.R;

/**
 * Created by vamnoize on 24/12/2558.
 */
public class GameHomeActivity extends ActionBarActivity {
    private Toolbar mToolbar;
    private Toolbar mToolbarBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamehome);

        String strCardSetName;
        strCardSetName = getIntent().getStringExtra("SENT_CardsetName");
        TextView show = (TextView) findViewById(R.id.textShow);
        show.setText(strCardSetName);
    }


}

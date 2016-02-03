package com.parse.starter.Cards;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.starter.DatabaseHelp;
import com.parse.starter.MenubarAddCardSet;
import com.parse.starter.MenubarHome;
import com.parse.starter.R;

import java.io.ByteArrayOutputStream;

/**
 * Created by vamnoize on 31/12/2558.
 */
public class AddCardSetActivity extends ActionBarActivity {
    ImageView iv_showIcon;
   // ImageButton ib_go;
    ImageButton ib_icon1,ib_icon2,ib_icon3;
    EditText et_cardsetName,et_title;
    Button bt_download;
    Button bt_save;
    String strCardsetName,strTitle,strIconPath;
    ParseFile pfIconFile;

    int intIcon;

    DatabaseHelp dbHelp = new DatabaseHelp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_cardset);

        MenubarHome menu = new MenubarHome(this);

        iv_showIcon = (ImageView) findViewById(R.id.iv_showIcon);

        et_cardsetName = (EditText) findViewById(R.id.et_cardsetName);
        et_title = (EditText) findViewById(R.id.et_title);
        bt_save = (Button) findViewById(R.id.bt_save);
        bt_download = (Button) findViewById(R.id.bt_download);

        ib_icon1 = (ImageButton) findViewById(R.id.ib_home);
        ib_icon2 = (ImageButton) findViewById(R.id.ib_game);
        ib_icon3 = (ImageButton) findViewById(R.id.ib_static);


        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        ib_icon1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_showIcon.setImageResource(R.mipmap.ic_home);
                intIcon = R.mipmap.ic_home;
            }
        });
        ib_icon2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_showIcon.setImageResource(R.mipmap.ic_game);
                intIcon = R.mipmap.ic_game;
            }
        });
        ib_icon3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_showIcon.setImageResource(R.mipmap.ic_statistic);
                intIcon = R.mipmap.ic_statistic;
            }
        });
        bt_save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TestOnClick","ib_go Okey");
                strCardsetName = et_cardsetName.getText().toString();
                strTitle = et_title.getText().toString();

                ImageIcon(intIcon);
                if (strCardsetName != null){
                    ParseObject cardsetOjb = new ParseObject(dbHelp.TABLE_NAME_DATA);
                    cardsetOjb.put(dbHelp.COLUMN_CARDSET_NAME, strCardsetName);
                    cardsetOjb.put(dbHelp.COLUMN_CARDSET_TITLE, strTitle);
                    cardsetOjb.put(dbHelp.COLUMN_CARDSET_ICONFILE, pfIconFile);

                    ParseACL acl = new ParseACL();
                    acl.setPublicWriteAccess(true);
                    acl.setPublicReadAccess(true);
                    cardsetOjb.setACL(acl);

                    cardsetOjb.saveInBackground();

                    Toast.makeText(AddCardSetActivity.this, "Save Cardset complete", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(AddCardSetActivity.this, "Save Cardset error. Cardset is null.", Toast.LENGTH_SHORT).show();
                }

                Intent intGoto = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intGoto);
                finish();

            }
        });

    }

    public void ImageIcon(int id){
        // Locate the image in res > drawable-hdpi
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
        // Convert it to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // Compress image to lower quality scale 1 - 100
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] image = stream.toByteArray();
        pfIconFile = new ParseFile(strCardsetName+".PNG",image);
        pfIconFile.saveInBackground();
        Log.d("check image","OK");

    }


}

package com.parse.starter.Cards;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.ParseACL;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.starter.DatabaseHelp;
import com.parse.starter.MenubarAddCardSet;
import com.parse.starter.MenubarHome;
import com.parse.starter.R;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by vamnoize on 19/1/2559.
 */
public class AddCardActivity extends ActionBarActivity implements View.OnClickListener {
    ImageView ivImgF,ivImgB;
    ImageView ivImgF2, ivImgF3, ivImgF4, ivImgF5;
    ImageView ivImgB2, ivImgB3, ivImgB4, ivImgB5;
    ImageView ivImgCameraF,ivImgCameraB;
    ImageView ivImgPhotoF,ivImgPhotoB;

    EditText etTextF,etTextB;

    Button btSave;

    DatabaseHelp dbHelp = new DatabaseHelp();



    String receive_cardsetName;
    String cardsetName;
    String picturePath;

    Boolean CheckImgFront = false;
    Boolean CheckImgBack = false;
    Boolean HavePic = false;

    String textFront,textBack,pathImgF,pathImgB,timeStamp;
    ParseFile pfImgF,pfImgB;

    Object imageFront = null;
    Object imageBack = null;

    String SENT_CardsetName = "SENT_CardsetName";


    private static int RESULT_LOAD_IMG = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        MenubarHome menubar = new MenubarHome(this);

        ivImgF = (ImageView) findViewById(R.id.ivImgFront);
        ivImgF2 = (ImageView) findViewById(R.id.ivImgF2);
        ivImgF3 = (ImageView) findViewById(R.id.ivImgF3);
        ivImgF4 = (ImageView) findViewById(R.id.ivImgF4);
        ivImgF5 = (ImageView) findViewById(R.id.ivImgF5);

        ivImgB = (ImageView) findViewById(R.id.ivImgBack);
        ivImgB2 = (ImageView) findViewById(R.id.ivImgB2);
        ivImgB3 = (ImageView) findViewById(R.id.ivImgB3);
        ivImgB4 = (ImageView) findViewById(R.id.ivImgB4);
        ivImgB5 = (ImageView) findViewById(R.id.ivImgB5);

        ivImgCameraF = (ImageView) findViewById(R.id.ivBtCameraF);
        ivImgCameraB = (ImageView) findViewById(R.id.ivBtCameraB);

        ivImgPhotoF = (ImageView) findViewById(R.id.ivBtPhotpF);
        ivImgPhotoB = (ImageView) findViewById(R.id.ivBtPhotpB);

        btSave = (Button) findViewById(R.id.btnSave);

        etTextF = (EditText) findViewById(R.id.etTextFront);
        etTextB = (EditText) findViewById(R.id.etTextBack);


        ivImgCameraF.setOnClickListener(this);
        ivImgCameraB.setOnClickListener(this);

        ivImgPhotoF.setOnClickListener(this);
        ivImgPhotoB.setOnClickListener(this);

        btSave.setOnClickListener(this);


        Bundle bundle = getIntent().getExtras();
        receive_cardsetName =  bundle.getString(SENT_CardsetName);
        cardsetName = receive_cardsetName;

    }
    @Override
    public void onClick(View v) {
        if (v == ivImgCameraF){}
        if (v == ivImgCameraB){}
        if (v == ivImgPhotoF){
            CheckImgFront = true;
            loadImagefromGallery(v);
        }
        if (v == ivImgPhotoB) {
            CheckImgBack = true;
            loadImagefromGallery(v);
        }
        if (v == btSave) {
            timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

//            Log.d("catName", cardsetName);
//            Log.d("ERRORLOG HavePic", HavePic.toString());
//            Log.d("ERRORLOG Check Img F", CheckImgFront.toString());
//            Log.d("ERRORLOG Check Text F", etTextF.getText().toString());

            if (etTextF.getText() != null || pathImgF.length()!=0) {
                ParseObject cardParseObj = new ParseObject(dbHelp.TABLE_NAME_CARDS);
                cardParseObj.put(dbHelp.COLUMN_CARD_CARDSETNAME, cardsetName);
                   // Log.d("ERRORLOG", "This is loop");
                   // Log.d("ERRORLOG", "IMG : " + CheckImgFront.toString() + " Back : " + CheckImgBack.toString());

                if (etTextF.getText().length() != 0) {

                    textFront = etTextF.getText().toString();
                    cardParseObj.put(dbHelp.COLUMN_CARD_TEXTFRONT, textFront);
                        //Log.d("ERRORLOG", "have text front");

                    if (etTextB.getText().length() != 0) {

                        textBack = etTextB.getText().toString();
                        cardParseObj.put(dbHelp.COLUMN_CARD_TEXTBACK, textBack);
                        //Log.d("ERRORLOG", "have text back");
                    }
                    if (pathImgB != null) {

                        haveImgBack();

                        cardParseObj.put(dbHelp.COLUMN_CARD_IMGBACKPATH, pathImgB);
                        cardParseObj.put(dbHelp.COLUMN_CARD_IMGBACKFILE, pfImgB);

                       // Log.d("ERRORLOG", "have pic Back");
                    }

                }
                if (pathImgF != null) {

                    haveImgFront();

                    cardParseObj.put(dbHelp.COLUMN_CARD_IMGFRONTPATH, pathImgF);
                    cardParseObj.put(dbHelp.COLUMN_CARD_IMGFRONTFILE, pfImgF);
                   // Log.d("ERRORLOG", "have img front");

                    if (etTextB.getText().length() != 0) {
                        textBack = etTextB.getText().toString();
                        cardParseObj.put(dbHelp.COLUMN_CARD_TEXTBACK, textBack);
                    }
                    if (pathImgB != null) {

                        haveImgBack();

                        cardParseObj.put(dbHelp.COLUMN_CARD_IMGBACKPATH, pathImgB);
                        cardParseObj.put(dbHelp.COLUMN_CARD_IMGBACKFILE, pfImgB);

                        //Log.d("ERRORLOG", "have pic Back");
                    }

                }
                cardParseObj.put(dbHelp.COLUMN_CARD_PRIORITY, 1);
                ParseACL acl = new ParseACL();
                acl.setPublicWriteAccess(true);
                acl.setPublicReadAccess(true);
                cardParseObj.setACL(acl);
                cardParseObj.saveInBackground();

                Intent Intent = new Intent(getApplicationContext(), ShowCardActivity.class);
                Intent.putExtra(SENT_CardsetName, cardsetName);
                Log.d("SENT_CAT_ID FROM Add cat", cardsetName);
                startActivity(Intent);

            } else {
                etTextF.setText("error!!!");
                Log.d("ERRORLOG", "Error");
            }

            setText();
        }
    }

    public void setText() {
        etTextF.setText("");
        etTextB.setText("");
        picturePath = "";
        pathImgF = "";
        pathImgB = "";
        pfImgB = null;
        pfImgF = null;
    }

    public void loadImagefromGallery(View view) {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMG);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();

            if (CheckImgFront){

                pathImgF = picturePath;
                ivImgF.setImageBitmap(BitmapFactory.decodeFile(pathImgF));
                CheckImgFront= false;

            }
            if (CheckImgBack){

                pathImgB = picturePath;
                ivImgB.setImageBitmap(BitmapFactory.decodeFile(pathImgB));
                CheckImgBack= false;
            }

            Log.d("ERRORLOG picturePath This",picturePath);
        }
    }
    private byte[] readInFile(String path) throws IOException {
        // TODO Auto-generated method stub
        byte[] data = null;
        File file = new File(path);
        InputStream input_stream = new BufferedInputStream(new FileInputStream(
                file));
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        data = new byte[2048]; // 16K
        int bytes_read;
        while ((bytes_read = input_stream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, bytes_read);
        }
        input_stream.close();
        //Log.d("this",buffer.toByteArray().toString());
        return buffer.toByteArray();

    }

    public void haveImgFront(){
        Object image = null;
        try {
            image = readInFile(pathImgF);

        } catch (Exception e) {
            e.printStackTrace();
        }
        pfImgF = new ParseFile("F"+timeStamp+".png", (byte[]) image);
        pfImgF.saveInBackground();
        CheckImgFront = false;

    }
    public void haveImgBack(){
        Object image = null;
        try {
            image = readInFile(pathImgB);

        } catch (Exception e) {
            e.printStackTrace();
        }
        pfImgB = new ParseFile("B"+timeStamp+".png", (byte[]) image);
        pfImgB.saveInBackground();
        CheckImgBack = false;

    }

}

package com.parse.starter.Cards;

import com.parse.ParseFile;

/**
 * Created by vamnoize on 25/12/2558.
 */
public class SingleCard {
    private ParseFile imgFile;
    private String text;
    private String path;



    SingleCard(String strText, String strPathImg, ParseFile pfImg) {
        this.text = strText;
        this.path = strPathImg;
        this.imgFile = pfImg;
    }

    public  String get_text(){ return text;}
    public String get_path (){return path;}
    public ParseFile get_imgFile (){return imgFile;}

}

package com.parse.starter.Cards;

import com.parse.ParseFile;

/**
 * Created by vamnoize on 25/12/2558.
 */
public class SingleCardset {
    private ParseFile iconFile;
    private String iconPatht;
    private String cardSetName;
    private String title;
    private String id;


    SingleCardset(String strcardSetName ,String strTitle, ParseFile strIconFile,String strIconPath,String strId) {
        this.cardSetName = strcardSetName;
        this.title = strTitle;
        this.iconFile = strIconFile;
        this.iconPatht = strIconPath;
        this.id = strId;
    }

    public  String getCardName(){ return cardSetName;}
    public String getCardSetTitle (){return title;}
    public String getIconPatht (){return iconPatht;}
    public ParseFile getIconFile() {
        return iconFile;
    }
    public String get_id() {
        return id;
    }

}

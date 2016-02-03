package com.parse.starter.Cards;

import com.parse.ParseFile;

/**
 * Created by vamnoize on 25/12/2558.
 */
public class setSigleCardset {
    String _cardsatName,_cardsetID,_cardsetTitle;
    String _pathIconCardset;
    ParseFile iconCardset;

    public void setCardsetName(String vCardsetName){this._cardsatName = vCardsetName;}
    public void setCardsetID(String vCardsetID){this._cardsetID = vCardsetID;}
    public void setCardsetTitle(String vCardsetTitle){this._cardsetTitle = vCardsetTitle;}
    public void setPathIcon(String vPathIcon){this._pathIconCardset = vPathIcon;}
    public void setIcon(ParseFile vIcon){this.iconCardset = vIcon;}

    public String getCardsetName(){return _cardsatName;}
    public String getCardsetID(){return _cardsetID;}
    public String getCardsetTitle(){return _cardsetTitle;}
    public String getPathIcon(){return _pathIconCardset;}
    public ParseFile getIcon(){return iconCardset;}
}

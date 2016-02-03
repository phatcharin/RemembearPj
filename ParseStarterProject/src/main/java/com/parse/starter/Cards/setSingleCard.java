package com.parse.starter.Cards;

import com.parse.ParseFile;

/**
 * Created by vamnoize on 25/12/2558.
 */
public class setSingleCard {
    String _cardTextF,_cardTextB,_cardsetName;
    String _pathImgF,_pathImgB;
    ParseFile imgF,imgB;
    int priority;
    String _IdCard;

    public void setCardsetName(String vCardset){this._cardsetName = vCardset;}
    public void set_IdCard(String vIdCard){this._IdCard = vIdCard;}
    public void setCardTextF(String vCardTextF){this._cardTextF = vCardTextF;}
    public void setCardTextB(String vCardTextB){this._cardTextB = vCardTextB;}
    public void setPathImgF(String vPathImgF){this._pathImgF = vPathImgF;}
    public void setPathImgB(String vPathImgB){this._pathImgB = vPathImgB;}
    public void setImgF(ParseFile vImgF){this.imgF = vImgF;}
    public void setImgB(ParseFile vImgB){this.imgB = vImgB;}
    public void setPriority(int vPriority){this.priority = vPriority;}

    public String get_cardsetName(){return _cardsetName;}
    public String get_cardTextF(){return _cardTextF;}
    public String get_cardTextB(){return _cardTextB;}
    public String get_pathIconImgF(){return _pathImgF;}
    public String get_pathIconImgB(){return _pathImgB;}
    public ParseFile getImgF(){return imgF;}
    public ParseFile getImgB(){return imgB;}
    public int getPriority(){return priority;}
    public String get_IdCard(){return _IdCard;}
}

package com.parse.starter.Game;

import com.parse.ParseFile;

/**
 * Created by vamnoize on 4/9/2558.
 */
public class setItem {

    String _catName,_itemID,_itemWordFront,_itemWordBack;
    String _itemPathFront,_itemPathBack;
    int _itemCountPlay,_itemCountCorr,_itemTimeAvr;
    float _itemProb;
    ParseFile _itemImgFileF,_itemImgFileB;
    // Set Value
    public void setItemcatName(String vItemCatname){this._catName = vItemCatname;}
    public void setItemID(String vItemID){this._itemID = vItemID;}
    public void setItemWordFront(String vItemWordFront){this._itemWordFront = vItemWordFront;}
    public void setItemImgFileFront(ParseFile vItemImgFileF){this._itemImgFileF = vItemImgFileF;}
    public void setItemPathImgFront(String vItemPathImgFront) { this._itemPathFront = vItemPathImgFront;}
    public void setItemWordBack(String vItemWordBack){this._itemWordBack = vItemWordBack;}
    public void setItemImgFileBack(ParseFile vItemImgFileB){this._itemImgFileB = vItemImgFileB;}
    public void setItemPathImgBack(String vItemPathImgBack) { this._itemPathBack = vItemPathImgBack;}

    public void setItemProb(float vItemProb){this._itemProb = vItemProb;}
    public void setItemCount(int vItemCount){this._itemCountPlay = vItemCount;}
    public void setItemCountCorr(int vItemCountCorr){this._itemCountCorr = vItemCountCorr;}
    public void setItemTimeAvr(int vItemTimeAvr){this._itemTimeAvr = vItemTimeAvr;}



    // Get Value
    public String getItemCatName(){ return _catName;}

    public String getItemID(){
        return _itemID;
    }

    public float getItemProb(){ return _itemProb;}
    public int getItemCountPlay(){ return _itemCountPlay;}
    public int getItemCountCorr(){ return _itemCountCorr;}
    public int getItemTimeAvr(){ return _itemTimeAvr;}


    public String getItemWordFront(){
        return _itemWordFront;
    }
    public ParseFile getItemImgFileF(){
        return _itemImgFileF;
    }
    public String getItemPathImgFront() { return _itemPathFront;}

    public String getItemWordBack(){
        return _itemWordBack;
    }
    public ParseFile getItemImgFileB(){
        return _itemImgFileB;
    }
    public String getItemPathImgBack() { return _itemPathBack;}

}

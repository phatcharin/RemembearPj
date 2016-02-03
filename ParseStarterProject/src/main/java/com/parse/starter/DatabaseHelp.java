package com.parse.starter;

import com.parse.ParseFile;

import java.security.PublicKey;

/**
 * Created by vamnoize on 25/12/2558.
 */
public class DatabaseHelp {
    public String TABLE_NAME_DATA = "RememberTable";
    public String TABLE_NAME_CARDSET = "CardSets";
    public String COLUMN_CARDSET_NAME = "CardSetName";
    public String COLUMN_CARDSET_TITLE = "CardSetTiTle";
    public String COLUMN_CARDSET_ICONFILE = "IconFile";
    public String COLUMN_CARDSET_ICONPATH = "IconPath";

    public String TABLE_NAME_CARDS = "Cards";
    public String COLUMN_CARD_ID = "objectID";
    public String COLUMN_CARD_CARDSETNAME = "CardsetName";
    public String COLUMN_CARD_TEXTFRONT = "TextFront";
    public String COLUMN_CARD_TEXTBACK = "TextBack";
    public String COLUMN_CARD_IMGFRONTPATH = "PathImgFront";
    public String COLUMN_CARD_IMGBACKPATH = "PathImgBack";
    public String COLUMN_CARD_IMGFRONTFILE = "FileImgFront";
    public String COLUMN_CARD_IMGBACKFILE = "FileImgBack";
    public String COLUMN_CARD_PRIORITY = "Priority";

    public String COLUMN_CARD_Probability_Ans = "Probability_Ans";
    public String COLUMN_CARD_CountPlay_Ans = "CountPlay_Ans";
    public String COLUMN_CARD_CountCorrect_Ans = "CountCorrect_Ans";
    public String COLUMN_CARD_TimeAvr_Ans = "TimeAvr_Ans";

    public String COLUMN_CARD_Probability_TF = "Probability_TF";
    public String COLUMN_CARD_CountPlay_TF = "CountPlay_TF";
    public String COLUMN_CARD_CountCorrect_TF = "CountCorrect_TF";
    public String COLUMN_CARD_TimeAvr_TF = "TimeAvr_TF";



}

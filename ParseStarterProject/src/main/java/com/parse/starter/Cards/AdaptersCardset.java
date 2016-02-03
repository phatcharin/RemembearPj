package com.parse.starter.Cards;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;
import com.parse.starter.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by vamnoize on 25/12/2558.
 */
public class AdaptersCardset extends RecyclerView.Adapter<AdaptersCardset.ViewHolder> {

    private Activity activity;
    private List<SingleCardset> cardsetItem;

    private LayoutInflater inflater;
    private Context context;

    private ConverseType converseTypeObjToByte = new ConverseType();

    private Boolean haveText = false;
    private Boolean haveImage = false;
    List<setSigleCardset> data = Collections.emptyList();

    private int focusedItem ;

    public AdaptersCardset(Context context,List<setSigleCardset> datalist) {
        Log.d("check","OKEY!!");
        //super();
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.data= datalist;

        //   final ParseHelp parseHelp = new ParseHelp();

        List<setSigleCardset> List = datalist;
        cardsetItem = new ArrayList<>();
        Log.d("check lise.size", String.valueOf(List.size()));
        for (int i = 0 ; i < List.size();i++ ) {
            Log.d("vamvamtest", "Loop Ok");
            setSigleCardset Cardset = List.get(i);
            Cardset.getCardsetID();
            String cardSetName = Cardset.getCardsetName();
            String cardSetTitle = Cardset.getCardsetTitle();
            String iconPath = "test";//Cardset.getPathIcon();
            ParseFile iconFile = Cardset.getIcon();

            cardsetItem.add(new SingleCardset(cardSetName, cardSetTitle, iconFile, iconPath));

        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        Log.d("check","OKEY!!");

        view = inflater.inflate(R.layout.cardset_icon, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SingleCardset sigleCardsetItem = cardsetItem.get(position);

        try {
            Glide.with(context)
                    .load(sigleCardsetItem.getIconFile().getUrl())
                    .into(holder.Image);

            holder.name.setText(sigleCardsetItem.getCardName());

        } catch (Exception e) {
            e.printStackTrace();
        }



        //Log.d("check bm",bm.toString());
    }

    @Override
    public int getItemCount() {

        return  cardsetItem.size();
    }
    @Override
    public int getItemViewType(int position) {
        final SingleCardset item = cardsetItem.get(position);
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnLongClickListener {

        TextView name;
        ImageView  Image;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.tvName);
            Image = (ImageView) itemView.findViewById(R.id.ivImg);
            //Log.d("check have",haveImage+" "+haveText);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getPosition();
            final SingleCardset sigleCardsetItem = cardsetItem.get(position);
            String text = sigleCardsetItem.getCardName();
            Intent intent = new Intent(context, ShowCardActivity.class);
            intent.putExtra("SENT_CardsetName", text);
            context.startActivity(intent);
            ((Activity)context).finish();


            Log.d("check", "Item Run . . .  " + focusedItem);

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
    private void setHave(){
        haveImage = false;
        haveText = false;
    }
}
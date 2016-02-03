package com.parse.starter.Cards;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.parse.DeleteCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.starter.DatabaseHelp;
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

    DatabaseHelp dbHelp = new DatabaseHelp();

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
            String id = Cardset.getCardsetID();
            String cardSetName = Cardset.getCardsetName();
            String cardSetTitle = Cardset.getCardsetTitle();
            String iconPath = "test";//Cardset.getPathIcon();
            ParseFile iconFile = Cardset.getIcon();

            cardsetItem.add(new SingleCardset(cardSetName, cardSetTitle, iconFile, iconPath,id));

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
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getPosition();
            final SingleCardset sigleCardsetItem = cardsetItem.get(position);
            String text = sigleCardsetItem.getCardName();
            Intent intent = new Intent(context, ShowCardActivity.class);
            intent.putExtra("SENT_CardsetName", text);
            context.startActivity(intent);
            //((Activity)context).finish();


            Log.d("check", "Item Run . . .  " + focusedItem);

        }

        @Override
        public boolean onLongClick(View v) {
            Log.wtf("Long","On Long Click");
            final int position = getPosition();
            final SingleCardset cardData = cardsetItem.get(position);


            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(true);
            builder.setTitle("Do you want to delete this item?");
            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            String id = cardsetItem.get(position).get_id();
                            Toast.makeText(context, id, Toast.LENGTH_SHORT).show();

                            ParseQuery<ParseObject> query = ParseQuery.getQuery(dbHelp.TABLE_NAME_DATA);
                            query.getInBackground(id, new GetCallback<ParseObject>() {
                                        public void done(ParseObject object, ParseException e) {
                                            if (e == null) {

                                                //for (ParseObject nameObj : objects) {
                                                Log.e("eiei", object.getObjectId());
                                                Log.e("eiei", object.getACL().getPublicWriteAccess()+"");
                                                object.deleteInBackground(new DeleteCallback() {
                                                    @Override
                                                    public void done(ParseException e) {
                                                        if (e==null) {
                                                            data.remove(position);
                                                            cardsetItem.remove(position);
                                                            AdaptersCardset.this.notifyDataSetChanged();
                                                        }
                                                    }
                                                });

                                            }

                                        }
                                    }

                            );

                        }
                    }

            );
            builder.setNegativeButton("Cancer",new DialogInterface.OnClickListener()

                    {

                        public void onClick (DialogInterface dialog,int which){
                            dialog.dismiss();
                        }

                    }

            );

            AlertDialog alert = builder.create();
            alert.show();

            notifyDataSetChanged();


            return true;
        }

    }
    private void setHave(){
        haveImage = false;
        haveText = false;
    }
}
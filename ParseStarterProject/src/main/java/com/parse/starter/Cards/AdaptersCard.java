package com.parse.starter.Cards;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
public class AdaptersCard extends RecyclerView.Adapter<AdaptersCard.ViewHolder> {

    private List<SingleCard> itemFronts;
    private List<SingleCard> itemBacks;
    private List<SingleIDCard> itemIdCard;

    private LayoutInflater inflater;
    private Context context;


    private Boolean haveText = false;
    private Boolean haveImage = false;
    List<setSingleCard> data = Collections.emptyList();

    private int focusedItem ;

    private AdaptersCard adapter = this;

    DatabaseHelp dbHelp = new DatabaseHelp();

    public AdaptersCard(Context context,List<setSingleCard> datalist) {
        Log.d("check","OKEY!!");
        //super();
        this.data= datalist;
        this.context=context;
        inflater=LayoutInflater.from(context);

        List<setSingleCard> List = datalist;
        itemFronts = new ArrayList<>();
        itemBacks = new ArrayList<>();
        itemIdCard = new ArrayList<>();
        Log.d("check lise.size", String.valueOf(List.size()));
        for (int i = 0 ; i < List.size();i++ ) {
            Log.d("vamvamtest", "Loop Ok");
            setSingleCard word = List.get(i);
            String idCard = word.get_IdCard();
            String textFront = word.get_cardTextF();
            String textBack =  word.get_cardTextB();
            String pathImgFront = word.get_pathIconImgF();
            String pathImgBack = word.get_pathIconImgB();
            ParseFile imgFileF = word.getImgF();
            ParseFile imgFileB = word.getImgB();

            itemFronts.add(new SingleCard(textFront, pathImgFront, imgFileF));
            itemBacks.add(new SingleCard(textBack, pathImgBack, imgFileB));
            itemIdCard.add(new SingleIDCard(idCard));
        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        Log.d("check","OKEY!!");

        view = inflater.inflate(R.layout.card, parent, false);
        ViewHolder holder = new ViewHolder(view,context);
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final SingleCard itemFront = itemFronts.get(position);

        holder.name.setVisibility(View.GONE);
        holder.Image.setVisibility(View.GONE);

        if (itemFront.get_text() != null && itemFront.get_path()==null) {
            holder.Image.setVisibility(View.GONE);
            holder.name.setVisibility(View.VISIBLE);
            holder.name.setText(itemFront.get_text());
        }
        if (itemFront.get_text() != null && itemFront.get_path()!= null) {

            holder.name.setVisibility(View.VISIBLE);
            holder.Image.setVisibility(View.VISIBLE);
            holder.name.setText(itemFront.get_text());

            try {
                Glide.with(context)
                        .load(itemFront.get_imgFile().getUrl())
                        .into(holder.Image);

                holder.name.setText(itemFront.get_text());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (itemFront.get_text() == null && itemFront.get_path()!= null) {

            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)holder.Image.getLayoutParams();
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            holder.Image.setLayoutParams(layoutParams);
            holder.name.setVisibility(View.GONE);
            holder.Image.setVisibility(View.VISIBLE);
           // holder.name.setText(itemFront.get_text());

            try {
                Glide.with(context)
                        .load(itemFront.get_imgFile().getUrl())
                        .into(holder.Image);

                holder.name.setText(itemFront.get_text());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public int getItemCount() {

        return  itemFronts.size();
    }
    @Override
    public int getItemViewType(int position) {
        final SingleCard itemF = itemFronts.get(position);
        final SingleCard itemB = itemBacks.get(position);
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnLongClickListener {

        TextView name;
        ImageView  Image;
        Context context;

        public ViewHolder(View itemView,Context context) {
            super(itemView);
            this.context = context;
            name = (TextView) itemView.findViewById(R.id.tvName);
            Image = (ImageView) itemView.findViewById(R.id.ivImg);
            Log.d("check have", haveImage + " " + haveText);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
//            btDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getPosition();
            focusedItem = 0;

            final Handler handler = new Handler();
            final SingleCard itemBack = itemBacks.get(position);
            name.setVisibility(View.GONE);
            Image.setVisibility(View.GONE);

            name.setVisibility(View.GONE);
            Image.setVisibility(View.GONE);

            if (itemBack.get_text() != null && itemBack.get_path()==null) {
                Image.setVisibility(View.GONE);
              //  name.setGravity(Gravity.CENTER_VERTICAL);
                name.setVisibility(View.VISIBLE);
                name.setText(itemBack.get_text());
            }
            if (itemBack.get_text() != null && itemBack.get_path()!= null) {

                //holder.name.setGravity(Gravity.CENTER_VERTICAL);
                name.setVisibility(View.VISIBLE);
                Image.setVisibility(View.VISIBLE);
                name.setText(itemBack.get_text());

                try {
                    Glide.with(context)
                            .load(itemBack.get_imgFile().getUrl())
                            .into(Image);

                    name.setText(itemBack.get_text());

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            if (itemBack.get_text() == null && itemBack.get_path()!= null) {

                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) Image.getLayoutParams();
                layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                Image.setLayoutParams(layoutParams);
                name.setVisibility(View.GONE);
                Image.setVisibility(View.VISIBLE);

                try {
                    Glide.with(context)
                            .load(itemBack.get_imgFile().getUrl())
                            .into(Image);

                    name.setText(itemBack.get_text());

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    notifyItemChanged(focusedItem);
                    Log.d("CLICK", "Item Run " + getPosition());

                }
            }, 500);
            focusedItem = getLayoutPosition();


            Log.d("check", "Item Run . . .  " + focusedItem);

        }


        @Override
        public boolean onLongClick(View v) {
            final int position = getPosition();
            final SingleCard cardData = itemFronts.get(position);

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(true);
            builder.setTitle("Do you want to delete this item?");
            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    String id = itemIdCard.get(position).get_id();
                    Toast.makeText(context, id, Toast.LENGTH_SHORT).show();

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Cards");
                    query.getInBackground(id, new GetCallback<ParseObject>() {
                                public void done(ParseObject object, ParseException e) {
//
                                    if (e == null) {

                                        //for (ParseObject nameObj : objects) {
                                        Log.e("eiei", object.getObjectId());
                                        Log.e("eiei", object.getACL().getPublicWriteAccess()+"");
                                        object.deleteInBackground(new DeleteCallback() {
                                            @Override
                                            public void done(ParseException e) {
                                                if (e==null) {
                                                    data.remove(position);
                                                    itemFronts.remove(position);
                                                    itemBacks.remove(position);
                                                    itemIdCard.remove(position);
                                                    AdaptersCard.this.notifyDataSetChanged();
                                                }
                                            }
                                        });

                                    }



                                }
//
//                        public void done(List<ParseObject> invites, ParseException e) {
//                            if (e == null) {
//                                Log.d("TEST",invites.toString());
//                                // iterate over all messages and delete them
//                                for (ParseObject invite : invites) {
//                                    invite.deleteInBackground();
//                                }
//                            } else {
//                                //Handle condition here
//                            }
//                        }
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
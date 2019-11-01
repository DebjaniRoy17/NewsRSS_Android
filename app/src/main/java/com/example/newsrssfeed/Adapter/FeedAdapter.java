package com.example.newsrssfeed.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.newsrssfeed.Interface.ItemClickListener;
import com.example.newsrssfeed.Model.Rss;
import com.example.newsrssfeed.Model.RssObject;
import com.example.newsrssfeed.R;
import com.squareup.picasso.Picasso;

class FeedViewHolder extends ViewHolder implements View.OnClickListener,View.OnLongClickListener{

    public TextView txtTitle, txtPubDate, txtDescription;
    public ImageView imgUrl;
    public ItemClickListener itemClickListener;

    public FeedViewHolder(View itemView) {
        super(itemView);
        this.txtTitle = (TextView)itemView.findViewById(R.id.txtTitle);
        this.txtPubDate = (TextView)itemView.findViewById(R.id.txtPubDate);
        this.txtDescription = (TextView)itemView.findViewById(R.id.txtDescription);
        this.imgUrl = (ImageView) itemView.findViewById(R.id.imgUrl);

        //set event
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }


    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {

    private Rss rss;
    private Context mContext;
    private LayoutInflater layoutInflater;

    public FeedAdapter(Rss rss, Context mContext) {
        this.rss = rss;
        this.mContext = mContext;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = layoutInflater.inflate(R.layout.row,parent,false);
      return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {

        holder.txtTitle.setText(rss.getChannel().getItem().get(position).getTitle());
        holder.txtPubDate.setText(rss.getChannel().getItem().get(position).getPubDate());
        holder.txtDescription.setText(rss.getChannel().getItem().get(position).getDescription());
       // holder.imgUrl.setIm(rss.getChannel().getItem().get(position).getContent().getUrl());
        Picasso.with(mContext).load(rss.getChannel().getItem().get(position).getContent().getUrl()).placeholder(R.drawable.ic_launcher_foreground).into(holder.imgUrl);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick){
                    //clicking on a single news feed, redirect it to the actual news feed on Internet
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(rss.getChannel().getItem().get(position).getLink()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);

                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return rss.getChannel().getItem().size();
    }
}

package com.house603.cash.feature.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.house603.cash.R;
import com.house603.cash.feature.model.ArticlesItem;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Admin on 2/25/2017.
 */

public class CommentaryNewsAdapter extends RecyclerView.Adapter<CommentaryNewsAdapter.ViewHolder> {
//    private final CommentaryNewsAdapterListener mClickListener;
    private List<ArticlesItem> mCurrencyModelList;
    private Context mContext;
    private CommentaryNewsAdapterListener mClickListener;

    public CommentaryNewsAdapter(Context context, List<ArticlesItem> mContactModelList, CommentaryNewsAdapterListener listener){
        this.mCurrencyModelList = mContactModelList;
        this.mContext = context;
       this.mClickListener = listener;
    }

    @Override
    public CommentaryNewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_commentary, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CommentaryNewsAdapter.ViewHolder holder, final int position) {
        final ArticlesItem model = mCurrencyModelList.get(position);
        holder.txt_headlineNews.setText(model.getTitle());
        holder.txt_url.setText(model.getUrl());
        holder.mpublished_date.setText(model.getPublishedAt());
        Glide.with(mContext)
            .load(model.getUrlToImage())
            .override(500,500)
            .into( holder.mUrlImage);
        //holder.mUrlImage.setImageBitmap(bmp);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.ItemClick(model,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCurrencyModelList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_headlineNews;
         TextView txt_url;
        TextView mpublished_date;
         ImageView mUrlImage;
         View ItemView;
         ViewHolder(View v) {
            super(v);
            ItemView = v;
            txt_headlineNews = (TextView) v.findViewById(R.id.news_headline);
            txt_url = (TextView) v.findViewById(R.id.news_url);
            mpublished_date = (TextView) v.findViewById(R.id.published_at);
             mUrlImage = (ImageView) v.findViewById(R.id.news_image);

        }
    }

    public void notifyDataSetChanged(List<ArticlesItem> items){
        mCurrencyModelList = items;
        notifyDataSetChanged();

    }
}

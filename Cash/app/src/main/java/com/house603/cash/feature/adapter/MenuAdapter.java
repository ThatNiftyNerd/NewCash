package com.house603.cash.feature.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.house603.cash.R;
import com.house603.cash.feature.model.CurrencyModel;

import java.util.List;

/**
 * Created by Admin on 2/25/2017.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private List<CurrencyModel> mCurrencyModelList;
    private Context mContext;
    MenuAdapterListener mClickListener;

    public MenuAdapter(Context context, List<CurrencyModel> mContactModelList, MenuAdapterListener listener){
        this.mCurrencyModelList = mContactModelList;
        this.mContext = context;
        this.mClickListener = listener;
    }

    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final CurrencyModel model = mCurrencyModelList.get(position);
        holder.txt_item_name.setText(model.getmItemName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.ItemClick(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mCurrencyModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

         TextView txt_item_name;
        View ItemView;
       ViewHolder(View v) {
            super(v);
            ItemView = v;
           txt_item_name = (TextView) v.findViewById(R.id.txt_name);

        }
    }
}

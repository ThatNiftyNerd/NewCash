package com.house603.cashew.feature.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.house603.cashew.R;
import com.house603.cashew.feature.model.CurrencyModel;

import java.util.List;

/**
 * Created by Admin on 2/25/2017.
 */

public class CommodityAdapter extends RecyclerView.Adapter<CommodityAdapter.ViewHolder> {
    private List<CurrencyModel> mCurrencyModelList;
    private Context mContext;
    CommodityAdapterListener mClickListener;
    private List<CurrencyModel> mCurencyListModels;

    public CommodityAdapter(Context context, List<CurrencyModel> mContactModelList, CommodityAdapterListener listener){
        this.mCurrencyModelList = mContactModelList;
        this.mContext = context;
        this.mClickListener = listener;
    }

    @Override
    public CommodityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_commodity_list, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CommodityAdapter.ViewHolder holder, final int position) {
        final CurrencyModel model = mCurrencyModelList.get(position);
        holder.txt_stock_price.setText(model.getmStockPrice());
        holder.txt_stock.setText(model.getmStockName());
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_stock_price;
        public TextView txt_stock;
        ImageView mImg_country;
        public View ItemView;
        public ViewHolder(View v) {
            super(v);
            ItemView = v;
            txt_stock_price = (TextView) v.findViewById(R.id.stock_price);
            txt_stock = (TextView) v.findViewById(R.id.stock_name);


        }
    }
}

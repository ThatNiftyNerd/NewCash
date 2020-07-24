package com.house603.cashew.feature.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.house603.cashew.R;
import com.house603.cashew.feature.model.CurrencyModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2/25/2017.
 */

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.ViewHolder> {
    private List<CurrencyModel> mCurrencyModelList;
    private Context mContext;
    CountryListAdapterListener mClickListener;
    private List<CurrencyModel> mCurencyListModels;

    public CountryListAdapter(Context context, List<CurrencyModel> mContactModelList, CountryListAdapterListener listener){
        this.mCurrencyModelList = mContactModelList;
        this.mContext = context;
        this.mClickListener = listener;
    }

    @Override
    public CountryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_country_list, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CountryListAdapter.ViewHolder holder, final int position) {
        final CurrencyModel model = mCurrencyModelList.get(position);
        Glide.with(mContext)
            .load(model.getFlag())
            .into( holder.mImg_country);
        holder.txt_countryName.setText(model.getmCountryName());
        holder.txt_iso.setText(model.getmCurrencyiso());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.ItemClick(model,position);
            }
        });

    }

    public void setFilter(List<CurrencyModel> mCurrencylist){
        mCurencyListModels = new ArrayList<>();
        mCurencyListModels.addAll(mCurrencylist);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCurrencyModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_countryName;
        public TextView txt_iso;
        ImageView mImg_country;
        public View ItemView;
        public ViewHolder(View v) {
            super(v);
            ItemView = v;
            txt_countryName = (TextView) v.findViewById(R.id.cnt_contact_name);
            txt_iso = (TextView) v.findViewById(R.id.cnt_iso);
            mImg_country = (ImageView) v.findViewById(R.id.cnt_avatar);

        }
    }
}

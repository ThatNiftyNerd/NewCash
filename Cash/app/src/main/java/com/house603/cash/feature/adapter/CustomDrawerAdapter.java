package com.house603.cash.feature.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.house603.cash.R;
import com.house603.cash.feature.model.CustomDrawerModel;

/**
 * Created by ${ENNY} on 6/14/2017.
 */

public class CustomDrawerAdapter extends ArrayAdapter<CustomDrawerModel> {

    Context mContext;
    int layoutResourceId;
    CustomDrawerModel data[] = null;

    public CustomDrawerAdapter(Context mContext, int layoutResourceId, CustomDrawerModel[] data) {

        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);

        ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.imageViewIcon);
        TextView textViewName = (TextView) listItem.findViewById(R.id.textViewName);

        CustomDrawerModel folder = data[position];


        imageViewIcon.setImageResource(folder.icon);
        textViewName.setText(folder.name);

        return listItem;
    }
}

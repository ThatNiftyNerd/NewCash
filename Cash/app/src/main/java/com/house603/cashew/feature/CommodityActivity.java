package com.house603.cashew.feature;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.house603.cashew.R;
import com.house603.cashew.feature.adapter.CommodityAdapter;
import com.house603.cashew.feature.adapter.CommodityAdapterListener;
import com.house603.cashew.feature.model.CurrencyModel;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class CommodityActivity extends AppCompatActivity {

    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    Map<String, Stock>stocks;
    private CommodityAdapter mAdapter;
    private ArrayList<CurrencyModel> mCommodityList;
    private String[] StockNames;
    private String[] StockIso;
    private ArrayList mData;
    String[] symbols;
    ImageView mGraph;
    String intc = "https://chart.finance.yahoo.com/z?s=INTC&t=6m&q=l&l=on&z=s&p=m20,m30";
    String aus = "https://chart.finance.yahoo.com/z?s=AIR.PA&t=6m&q=l&l=on&z=s&p=m50,m200";
    String baba = "https://chart.finance.yahoo.com/z?s=BABA&t=6m&q=l&l=on&z=s&p=m50,m200";
    String tesla = "https://chart.finance.yahoo.com/z?s=TSLA&t=6m&q=l&l=on&z=s&p=m50,m200";
    String yahoo = "https://chart.finance.yahoo.com/z?s=YHOO&t=6m&q=l&l=on&z=s&p=m50,m200";
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        initView();
        initModel();
    }

    private void initModel() {
        StockNames= getResources().getStringArray(R.array.stock_name);
        StockIso= getResources().getStringArray(R.array.stock_iso);
        try {
            YahooTest();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mCommodityList= new ArrayList<>();
         for (int i = 0; i < StockNames.length; i++) {
            CurrencyModel model = new CurrencyModel();
            model.setmStockName(StockNames[i]);
           model.setmStockPrice(symbols[i]);
            mCommodityList.add(model);

//            for(CurrencyModel model : mCountryList){
//                setCurrencyModel(model);
//            }
             SetImage(aus);
            mAdapter = new CommodityAdapter(getApplicationContext(), mCommodityList, new CommodityAdapterListener() {
                @Override
                public void ItemClick(CurrencyModel model, int p) {
                    position = p;
                    switch (position){
                        case 0 :
                            SetImage(intc);
                            break;
                        case 1 :
                            SetImage(aus);
                            break;
                        case 2 :
                            SetImage(baba);
                            break;
                        case 3 :
                            SetImage(tesla);
                            break;
                        case 4 :
                            SetImage(yahoo);
                            break;

                    }
                }
            });

             mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);


        }

    }

    private void initView() {
        mGraph = (ImageView) findViewById(R.id.img_commodity);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView = (RecyclerView) findViewById(R.id.rec_commodity);
    }



    private void YahooTest() throws IOException {
         stocks = YahooFinance.get(StockIso);
  //      String[] result = stocks.values().toArray(new String[0]);
        mData = new ArrayList();
        mData.addAll(stocks.entrySet());
        Stock intel = stocks.get("INTC");
        //Stock airbus = stocks.get("AIR.PA");
        //Stock BABA = stocks.get("BABA");
        //Stock tsla = stocks.get("TSLA");
        //Stock YHOO = stocks.get("YHOO");
        symbols = new String[]{String.valueOf(intel)} ;
            //String.valueOf(airbus), String.valueOf(BABA), String.valueOf(YHOO)
            //    , String.valueOf(tsla)};

    }



    private void SetImage(String strUrl){
        URL url = null;
        try {
            url = new URL(strUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mGraph.setImageBitmap(bmp);

    }
}

package com.house603.cash.feature;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.house603.cash.R;
import com.house603.cash.feature.adapter.CommodityAdapter;
import com.house603.cash.feature.adapter.CommodityAdapterListener;
import com.house603.cash.feature.model.CurrencyModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

            mAdapter = new CommodityAdapter(getApplicationContext(), mCommodityList, new CommodityAdapterListener() {
                @Override
                public void ItemClick(CurrencyModel model, int p) {

                }
            });

             mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);


        }

    }

    private void initView() {
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView = (RecyclerView) findViewById(R.id.rec_commodity);
    }



    private void YahooTest() throws IOException {

         stocks = YahooFinance.get(StockIso);
  //      String[] result = stocks.values().toArray(new String[0]);
        mData = new ArrayList();
        mData.addAll(stocks.entrySet());
        Stock intel = stocks.get("INTC");
        Stock airbus = stocks.get("AIR.PA");
        Stock BABA = stocks.get("BABA");
        Stock tsla = stocks.get("TSLA");
        Stock YHOO = stocks.get("YHOO");
        symbols = new String[]{String.valueOf(intel), String.valueOf(airbus), String.valueOf(BABA), String.valueOf(YHOO)
                , String.valueOf(tsla)};

    }
}

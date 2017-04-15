package com.house603.cash.feature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.house603.cash.R;
import com.house603.cash.feature.adapter.CommentaryNewsAdapter;
import com.house603.cash.feature.model.ArticlesItem;
import com.house603.cash.feature.model.Response;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static java.security.AccessController.getContext;

public class CommenatryActivity extends AppCompatActivity {
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private CommentaryNewsAdapter mAdapter;
    private static final String API_URL = "https://newsapi.org/v1/articles?source=financial-times&sortBy=latest&apiKey=98b4a7a61fae4a9399c401dadeb49607";
    private static final String API_URL1 = "https://newsapi.org/v1/articles?source=business-insider&sortBy=latest&apiKey=98b4a7a61fae4a9399c401dadeb49607";
    private JSONObject jsonObj;
    List<ArticlesItem> mModelList;
    List<ArticlesItem> mNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commenatry);
        initView();
        initModel();
    }

    private void initView() {
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView = (RecyclerView) findViewById(R.id.rec_commentary);
    }

    private void initModel() {
        loadCommentaryExchangeData();
    }

    public void loadCommentaryExchangeData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(API_URL1, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String decodedData = new String(responseBody);
                Log.d("result", "" + decodedData);

                try {
                    jsonObj = new JSONObject(decodedData);
                    mModelList = new ArrayList<>();
                    Gson gson = new Gson();
                    Response response = gson.fromJson(decodedData, Response.class);
                    for(ArticlesItem item : response.getArticles()){
                        //                       Log.d("TAG", "onSuccess: " + item.getDescription());
                    }

//                    for(ArticlesItem item : response.getArticles()){
//                        mModelList.add(item);
//                    }
                    mModelList.addAll(response.getArticles());
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new CommentaryNewsAdapter(getApplicationContext(), mModelList);
                    Toast.makeText(getApplicationContext(),"size" + mModelList.size(), Toast.LENGTH_LONG).show();
                    mRecyclerView.setAdapter(mAdapter);
                    //                  ShowArticles(mModelList);
                    //  mNew.addAll(mModelList);

                    Log.d("TAG", "onSuccess: " + mModelList.size());


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}

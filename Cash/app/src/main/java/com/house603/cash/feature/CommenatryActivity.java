package com.house603.cash.feature;

import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.house603.cash.R;
import com.house603.cash.feature.adapter.CommentaryNewsAdapter;
import com.house603.cash.feature.adapter.CommentaryNewsAdapterListener;
import com.house603.cash.feature.model.ArticleResponse;
import com.house603.cash.feature.model.ArticlesItem;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

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
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
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
                    ArticleResponse response = gson.fromJson(decodedData, ArticleResponse.class);
                    for(ArticlesItem item : response.getArticles()){
                        //                       Log.d("TAG", "onSuccess: " + item.getDescription());
                    }

//                    for(ArticlesItem item : response.getArticles()){
//                        mModelList.add(item);
//                    }
                    mModelList.addAll(response.getArticles());
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new CommentaryNewsAdapter(getApplicationContext(), mModelList, new CommentaryNewsAdapterListener() {
                        @Override
                        public void ItemClick(ArticlesItem model, int p) {
                            Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(model.getUrl()));
                            startActivity(intent);
                        }
                    });
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

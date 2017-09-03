package com.house603.cash.feature;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.house603.cash.R;
import com.house603.cash.feature.adapter.CustomDrawerAdapter;
import com.house603.cash.feature.adapter.MainNavAdapter;
import com.house603.cash.feature.adapter.MainNavListener;
import com.house603.cash.feature.adapter.MenuAdapter;
import com.house603.cash.feature.adapter.MenuAdapterListener;
import com.house603.cash.feature.model.CurrencyModel;
import com.house603.cash.feature.model.CustomDrawerModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    String[] MenuTitles = new String[]{"COMMENTARY", "COMMODITY", "ABOUT US"};
    SlidingPaneLayout mSlidingPanel;
    ListView mMenuList;
    ImageView appImage;
    TextView TitleText;
    private List<CurrencyModel> mMenuItemList;
    MainNavAdapter mAdapter;
    private RecyclerView mRecyclerView;
    CurrencyModel mCurrencymodel;
    private LinearLayoutManager mLayoutManager;
    private LinearLayout mCountryUp;
    private LinearLayout mCountryDown;
    int flag;
    String country;
    String iso;
    private ImageView mFlagCountryUp;
    private ImageView mFlagCountryDown;
    private TextView mCountryNameUp;
    private TextView mCountryNameDown;
    public int to;
    public int from;
    public String s;
    String isoUp, isoDown;
    private EditText mEdCountryUp;
    private EditText mEdCountryDown;
    private Button mCalculate;
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPoint;
    private ImageButton btnCancel, btnSwitch, btnDelete;
    Double IsoUpRate, IsoDownRate;
    private static final String API_URL = "https://openexchangerates.org/api/latest.json?app_id=4691eb36ebce42a9ac5db9d977231aed";
    private String mValueCountryUp;
    private Double mDoubValueCountryUp;
    JSONObject ratesObject;
    JSONObject jsonObj = null;
    private String mValueCountryDown;
    private Double mDoubValueCountryDown;
    private Button mCalculates;
    private DrawerLayout mDrawerLayout;
    String TITLES[] = {"Home","Commentary","About Us"};
    int ICONS[] = { R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    Toolbar toolbar;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initActionbar();
        initModel();


    }

    private void initActionbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.app_name);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initModel() {
        SetUpDrawer();
        SetUpNavView();
       // mMenuItemList = new ArrayList<>();
        //for (int i = 0; i < MenuTitles.length; i++) {
        //    CurrencyModel country = new CurrencyModel();
        //    country.setmItemName(MenuTitles[i]);
        //    mMenuItemList.add(country);
        //
        //    mAdapter = new MenuAdapter(getApplicationContext(), mMenuItemList, new MenuAdapterListener() {
        //        @Override
        //        public void ItemClick(int p) {
        //            switch (p){
        //                case 0:
        //                    Intent mIntent = new Intent(getApplicationContext(), CommenatryActivity.class);
        //                    startActivity(mIntent);
        //                    break;
        //                case 1:
        //                    Intent ntent = new Intent(getApplicationContext(), CommodityActivity.class);
        //                    startActivity(ntent);
        //                    break;
        //            }
        //
        //        }
        //    });
        //
        //    mRecyclerView.setLayoutManager(mLayoutManager);
        //    mRecyclerView.setAdapter(mAdapter);
  //          loadCurrencyExchangeData();
//        getActionBar().setDisplayShowHomeEnabled(true);
//        getActionBar().setHomeButtonEnabled(true);
  //      }
    }

    private void SetUpDrawer(){
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }



        }; // Drawer Toggle Object Made
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    public void SetUpNavView() {
        mAdapter = new MainNavAdapter(getApplicationContext(), TITLES, ICONS, new MainNavListener() {
            @Override
            public void OnItemClick(int Position) {
                selectItemNav(Position);
            }
            });

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }


    private void selectItemNav(int position) {
        switch (position) {
            case 1:
                Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mIntent);
                break;
            case 2:
                Intent ntent = new Intent(getApplicationContext(), CommenatryActivity.class);
                startActivity(ntent);
                break;
            case 3:
                break;

            default:
                break;
        }
    }

    private void initView() {

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
       // mRecyclerView = (RecyclerView) findViewById(R.id.rec_menu_item);
        //mSlidingPanel = (SlidingPaneLayout) findViewById(R.id.SlidingPanel);
        //  mMenuList = (ListView) findViewById(R.id.MenuList);
        appImage = (ImageView) findViewById(android.R.id.home);
        TitleText = (TextView) findViewById(android.R.id.title);

        //  mMenuList.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,MenuTitles));

    //    mSlidingPanel.setPanelSlideListener(panelListener);
        mSlidingPanel.setParallaxDistance(200);

    }

    public void initViews() {
        mCountryUp = (LinearLayout) findViewById(R.id.ln_country1);
        mCountryDown = (LinearLayout) findViewById(R.id.ln_country2);
        mFlagCountryUp = (ImageView) findViewById(R.id.img_country1);
        mFlagCountryDown = (ImageView) findViewById(R.id.img_country2);
        mCountryNameUp = (TextView) findViewById(R.id.txt_country1);
        mCountryNameDown = (TextView) findViewById(R.id.txt_country2);
        mEdCountryUp = (EditText) findViewById(R.id.edt_country_up);
        mEdCountryDown = (EditText) findViewById(R.id.edt_country_down);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mCalculate = (Button) findViewById(R.id.btn_cal);
        mCalculates = (Button) findViewById(R.id.btn_cals);
        mFlagCountryUp.setImageResource(R.mipmap.nigeria);
        mFlagCountryDown.setImageResource(R.mipmap.united_states);
        mCountryUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCountryList();
            }
        });
        mCountryDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCountryList2();
            }
        });
//        mCalculate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setValueToString();
//                if (isoUp != null && isoDown != null) {
//                    try {
//                        IsoUpRate = ratesObject.getDouble(isoUp);
//                        IsoDownRate = ratesObject.getDouble(isoDown);
//
//                        if (!mValueCountryUp.isEmpty() & mValueCountryDown.isEmpty()) {
//                            mDoubValueCountryUp = Double.valueOf(mValueCountryUp);
//                            Double ans1ConVert = mDoubValueCountryUp / IsoUpRate;
//                            Double ans = ans1ConVert * IsoDownRate;
//                            String finalAns = String.valueOf(ans);
//                            mEdCountryDown.setText(finalAns);
//
//                        } else if (!mValueCountryDown.isEmpty() & mValueCountryUp.isEmpty()) {
//                            mDoubValueCountryDown = Double.valueOf(mValueCountryDown);
//                            Double ans1ConVert = mDoubValueCountryDown / IsoDownRate;
//                            Double ans = ans1ConVert * IsoUpRate;
//                            String finalAns = String.valueOf(ans);
//                            mEdCountryUp.setText(finalAns);
//                        } else if (!mValueCountryUp.isEmpty() & !mValueCountryDown.isEmpty()) {
//
//                            Toast.makeText(getApplicationContext(), "Clear screen", Toast.LENGTH_LONG).show();
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                } else {
//                    mCountryNameUp.setText("NGN");
//                    mCountryNameDown.setText("USD");
//                    isoUp = mCountryNameUp.getText().toString();
//                    isoDown = mCountryNameDown.getText().toString();
//                    try {
//                        IsoUpRate = ratesObject.getDouble(isoUp);
//                        IsoDownRate = ratesObject.getDouble(isoDown);
//
//                        if (!mValueCountryUp.isEmpty() & mValueCountryDown.isEmpty()) {
//                            mDoubValueCountryUp = Double.valueOf(mValueCountryUp);
//                            Double ans1ConVert = mDoubValueCountryUp / IsoUpRate;
//                            Double ans = ans1ConVert * IsoDownRate;
//                            String finalAns = String.valueOf(ans);
//                            mEdCountryDown.setText(finalAns);
//
//                        } else if (!mValueCountryDown.isEmpty() & mValueCountryUp.isEmpty()) {
//                            mDoubValueCountryDown = Double.valueOf(mValueCountryDown);
//                            Double ans1ConVert = mDoubValueCountryDown / IsoDownRate;
//                            Double ans = ans1ConVert * IsoUpRate;
//                            String finalAns = String.valueOf(ans);
//                            mEdCountryUp.setText(finalAns);
//                        } else if (!mValueCountryUp.isEmpty() & !mValueCountryDown.isEmpty()) {
//
//                            Toast.makeText(getApplicationContext(), "Clear screen", Toast.LENGTH_LONG).show();
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                    Toast.makeText(getApplicationContext(), "Pick country", Toast.LENGTH_SHORT).show();
//                }
//
//
//
//            }
//
//
//        });
//
//        mCalculates.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent in = new Intent(getApplicationContext(), testActivity.class);
//                startActivity(in);
//            }
//        });

        mEdCountryDown.setEnabled(false);
        mEdCountryUp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence value, int i, int i1, int i2) {
                try {
                    if (isoUp == null && isoDown == null){
                        isoUp = mCountryNameUp.getText().toString();
                        isoDown = mCountryNameDown.getText().toString();
                    }

                    mValueCountryUp = String.valueOf(value);
                    if (!mValueCountryUp.isEmpty()) {
                        IsoUpRate = ratesObject.getDouble(isoUp);
                        IsoDownRate = ratesObject.getDouble(isoDown);
                        mDoubValueCountryUp = Double.valueOf(mValueCountryUp);
                        Double ans1ConVert = mDoubValueCountryUp / IsoUpRate;
                        Double ans = ans1ConVert * IsoDownRate;
                        String finalAns = String.valueOf(ans);
                        mEdCountryDown.setText(finalAns);

                    }else{
                        mEdCountryDown.setText("");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mEdCountryUp.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 int inType = mEdCountryUp.getInputType();       // Backup the input type 
                 mEdCountryUp.setInputType(InputType.TYPE_NULL); // Disable standard keyboard 
                 mEdCountryUp.onTouchEvent(event);               // Call native handler 
                 mEdCountryUp.setInputType(inType);              // Restore input type 
                 return true; // Consume touch event 
             }
        });
        initButtons();
    }

    public void initButtons(){
        btn0 = (Button) findViewById(R.id.btnzero);
        btn1 = (Button) findViewById(R.id.btnone);
        btn2 = (Button) findViewById(R.id.btntwo);
        btn3 = (Button) findViewById(R.id.btnThree);
        btn4 = (Button) findViewById(R.id.btnFour);
        btn5 = (Button) findViewById(R.id.btnfive);
        btn6 = (Button) findViewById(R.id.btnsix);
        btn7 = (Button) findViewById(R.id.btnseven);
        btn8 = (Button) findViewById(R.id.btneight);
        btn9 = (Button) findViewById(R.id.btnNine);
        btnPoint = (Button) findViewById(R.id.btnpoint);
        btnCancel = (ImageButton) findViewById(R.id.btncancel);
        btnDelete = (ImageButton) findViewById(R.id.btndelete);
        btnSwitch = (ImageButton) findViewById(R.id.btnswitch);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
        btnSwitch.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String value = mEdCountryUp.getText().toString();
        int GUI_Id = v.getId();
        switch (GUI_Id){
            case R.id.btnpoint:
                if(value.contains("."))
                    return;
                else
                    value += ".";
                    mEdCountryUp.setText(value);
                break;
            case R.id.btndelete:
                int length = mEdCountryUp.getText().length();
                if (length > 0) {
                    mEdCountryUp.getText().delete(length - 1, length);
                }
                break;
            case R.id.btncancel:
                mEdCountryUp.setText("");
                break;
            case R.id.btnswitch:
                break;
            default:
                Button sender = (Button) v;
                value += sender.getText();
                mEdCountryUp.setText(value);
        }

    }

    private void setValueToString() {
        mValueCountryUp = mEdCountryUp.getText().toString();
        mValueCountryDown = mEdCountryDown.getText().toString();

    }

    public void OpenCountryList() {
        Intent intent = new Intent(getApplicationContext(), CountryListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("flag1", "flag1Country");
        bundle.putInt("id", 5);
        intent.putExtras(bundle);
        startActivityForResult(intent, 2);

    }


    public void OpenCountryList2() {
        Intent intent = new Intent(getApplicationContext(), CountryListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("flag2", "flag2Country");
        bundle.putInt("id", 6);
        intent.putExtras(bundle);
        startActivityForResult(intent, 3);
    }


//    SlidingPaneLayout.PanelSlideListener panelListener = new SlidingPaneLayout.PanelSlideListener() {
//
//        @Override
//        public void onPanelClosed(View arg0) {
//            // TODO Auto-genxxerated method stub        getActionBar().setTitle(getString(R.string.app_name));
////            appImage.animate().rotation(0);
//        }
//
//        @Override
//        public void onPanelOpened(View arg0) {
//            // TODO Auto-generated method stub
////            getActionBar().setTitle("Menu Titles");
////            appImage.animate().rotation(90);
//        }
//
//        @Override
//        public void onPanelSlide(View arg0, float arg1) {
//            // TODO Auto-generated method stub
//
//        }
//
//    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mSlidingPanel.isOpen()) {
                    appImage.animate().rotation(0);
                    mSlidingPanel.closePane();
                    //                   getActionBar().setTitle(getString(R.string.app_name));
                } else {
                    appImage.animate().rotation(90);
                    mSlidingPanel.openPane();
//                    getActionBar().setTitle("Menu Titles");
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadCurrencyExchangeData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(API_URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String decodedData = new String(responseBody);
                Log.d("result", "" + decodedData);

                try {
                    jsonObj = new JSONObject(decodedData);
                    ratesObject = jsonObj.getJSONObject("rates");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if data is null, if it is do nothing
     //   if(data == null) return;
        // check if the request code is same as what is passed here it is 2
        if (data != null){
            if (requestCode == 2) {
                //  country = data.getStringExtra("name");
                flag = data.getIntExtra("map", 1);
                iso = data.getStringExtra("iso");
                mCountryNameUp.setText(iso);
                mFlagCountryUp.setImageResource(flag);
                isoUp = mCountryNameUp.getText().toString();

            } else if (requestCode == 3) {
                //   country = data.getStringExtra("name");
                flag = data.getIntExtra("map", 1);
                iso = data.getStringExtra("iso");
                mCountryNameDown.setText(iso);
                mFlagCountryDown.setImageResource(flag);
                isoDown = mCountryNameDown.getText().toString();

            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
         loadCurrencyExchangeData();
    }

}

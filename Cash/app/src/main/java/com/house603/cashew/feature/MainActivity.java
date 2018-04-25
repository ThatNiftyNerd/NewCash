package com.house603.cashew.feature;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.davidmiguel.numberkeyboard.NumberKeyboard;
import com.davidmiguel.numberkeyboard.NumberKeyboardListener;
import com.google.gson.Gson;
import com.house603.cashew.R;
import com.house603.cashew.feature.adapter.MainNavAdapter;
import com.house603.cashew.feature.adapter.MainNavListener;
import com.house603.cashew.feature.model.CurrencyModel;
import com.house603.cashew.feature.model.Rates;
import com.house603.cashew.feature.utils.PreferenUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
    NumberKeyboardListener {

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
  Double IsoUpRate, IsoDownRate;
  private static final String API_URL = "https://openexchangerates.org/api/latest.json?app_id=4691eb36ebce42a9ac5db9d977231aed";
  private String mValueCountryUp;
  private Double mDoubValueCountryUp;
  JSONObject ratesObject;
  JSONObject jsonObj = null;
  private DrawerLayout mDrawerLayout;
  String TITLES[] = {"Home", "Commentary", "About Us"};
  int ICONS[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
  Toolbar toolbar;
  android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
  private NumberKeyboard numberKeyboard;
  private PreferenUtil mPreference;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initViews();
    initActionbar();
    // initModel();

  }

  private void initActionbar() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    setTitle(R.string.app_name);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
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

  public void initViews() {
    mPreference = PreferenUtil.getInstant(getApplicationContext());
    mCountryUp = (LinearLayout) findViewById(R.id.ln_country1);
    mCountryDown = (LinearLayout) findViewById(R.id.ln_country2);
    mFlagCountryUp = (ImageView) findViewById(R.id.img_country1);
    mFlagCountryDown = (ImageView) findViewById(R.id.img_country2);
    mCountryNameUp = (TextView) findViewById(R.id.txt_country1);
    mCountryNameDown = (TextView) findViewById(R.id.txt_country2);
    mEdCountryUp = (EditText) findViewById(R.id.edt_country_up);
    mEdCountryDown = (EditText) findViewById(R.id.edt_country_down);
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    numberKeyboard = (NumberKeyboard) findViewById(R.id.numberkeyboard);
    findViewById(R.id.rotate).setOnClickListener(this);
    numberKeyboard.setListener(this);
    getSharedPrefence();
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
    mEdCountryUp.setSelection(mEdCountryUp.getText().length());
    final DecimalFormat df = new DecimalFormat("#.##");

    mEdCountryDown.setEnabled(false);
    mEdCountryUp.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence value, int i, int i1, int i2) {
        ConversionOnTextChange(value, df);

      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      mEdCountryUp.setShowSoftInputOnFocus(false);
    } else {
      try {
        final Method method = EditText.class.getMethod(
            "setShowSoftInputOnFocus"
            , new Class[]{boolean.class});
        method.setAccessible(true);
        method.invoke(mEdCountryUp, false);
      } catch (Exception e) {
        // ignore
      }
    }

    //   initCallKeyboardAction();
  }

  private void ConversionOnTextChange(CharSequence value, DecimalFormat df) {
    if (isNetworkAvailable()) {
      mEdCountryUp.setSelection(mEdCountryUp.getText().length());
      try {
        if (isoUp == null && isoDown == null) {
          isoUp = mCountryNameUp.getText().toString();
          isoDown = mCountryNameDown.getText().toString();
        }

        mValueCountryUp = String.valueOf(value);
        if (!mValueCountryUp.isEmpty()) {
          if(ratesObject == null){
            if (mPreference.getRateJsonObj() != null && !mPreference.getRateJsonObj().isEmpty()){
              ratesObject = new JSONObject(mPreference.getRateJsonObj());
            }else {
              Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Loading rates, please wait...", Snackbar.LENGTH_SHORT);
              snackbar.show();
              return;
            }
          }
          IsoUpRate = ratesObject.getDouble(isoUp);
          IsoDownRate = ratesObject.getDouble(isoDown);
          mDoubValueCountryUp = Double.valueOf(mValueCountryUp);
          Double ans1ConVert = mDoubValueCountryUp / IsoUpRate;
          Double ans = ans1ConVert * IsoDownRate;
          Double conDecimal = Double.valueOf(df.format(ans));
          String finalAns = String.valueOf(conDecimal);
          mEdCountryDown.setText(String.format("%.2f", ans));

        } else {
          mEdCountryDown.setText("");
        }

      } catch (JSONException e) {
        e.printStackTrace();
      }
    } else {
      if (mPreference.getRateJsonObj() != null && !mPreference.getRateJsonObj().isEmpty()){
        mEdCountryUp.setSelection(mEdCountryUp.getText().length());
        try {
          ratesObject = new JSONObject(mPreference.getRateJsonObj());
          if (isoUp == null && isoDown == null) {
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
            Double conDecimal = Double.valueOf(df.format(ans));
            String finalAns = String.valueOf(conDecimal);
            mEdCountryDown.setText(String.format("%.2f", ans));

          } else {
            mEdCountryDown.setText("");
          }
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }else {
        Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG)
            .show();
      }

    }
  }

  private boolean isNetworkAvailable() {
    ConnectivityManager connectivityManager
        = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
  }

  private int getDrawableId(ImageView iv) {
    return (Integer) iv.getTag();
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

  private void loadCurrencyExchangeData() {
    final PreferenUtil mPref = PreferenUtil.getInstant(getApplicationContext());
    AsyncHttpClient client = new AsyncHttpClient();
    client.get(API_URL, new AsyncHttpResponseHandler() {
      @Override
      public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        String decodedData = new String(responseBody);
        Log.d("result", "" + decodedData);
        try {
          jsonObj = new JSONObject(decodedData);
          ratesObject = jsonObj.getJSONObject("rates");
          String rateConverted = ratesObject.toString();
          if (rateConverted != null && !rateConverted.isEmpty()){
            mPref.saveRateObjectJson(rateConverted);
          }
 //         Rates rr = new Gson().fromJson(ratesObject.toString(),Rates.class);
//          Double gg = rr.getARS();
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }

      @Override
      public void onFailure(int statusCode, Header[] headers, byte[] responseBody,
          Throwable error) {

      }
    });

  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    // check if data is null, if it is do nothing
    //   if(data == null) return;
    // check if the request code is same as what is passed here it is 2
    if (data != null) {
      if (requestCode == 2) {
        //  country = data.getStringExtra("name");
        flag = data.getIntExtra("map", 1);
        iso = data.getStringExtra("iso");
        mCountryNameUp.setText(iso);
        mFlagCountryUp.setImageResource(flag);
        mFlagCountryUp.setTag(flag);
        isoUp = mCountryNameUp.getText().toString();
        DecimalFormat df = new DecimalFormat("#.##");
        if (isNetworkAvailable()) {
          try {
            if (isoUp == null && isoDown == null) {
              isoUp = mCountryNameUp.getText().toString();
              isoDown = mCountryNameDown.getText().toString();
            }

            mValueCountryUp =  mEdCountryUp.getText().toString();
            if (!mValueCountryUp.isEmpty()) {
              if(ratesObject == null) {
                if (mPreference.getRateJsonObj() != null && !mPreference.getRateJsonObj()
                    .isEmpty()) {
                  ratesObject = new JSONObject(mPreference.getRateJsonObj());
                } else {
                  Snackbar snackbar = Snackbar
                      .make(findViewById(android.R.id.content), "Loading rates, please wait...",
                          Snackbar.LENGTH_SHORT);
                  snackbar.show();
                  return;
                }
              }
              IsoUpRate = ratesObject.getDouble(isoUp);
              IsoDownRate = ratesObject.getDouble(isoDown);
              mDoubValueCountryUp = Double.valueOf(mValueCountryUp);
              Double ans1ConVert = mDoubValueCountryUp / IsoUpRate;
              Double ans = ans1ConVert * IsoDownRate;
              Double conDecimal = Double.valueOf(df.format(ans));
              mEdCountryDown.setText(String.format("%.2f", ans));

            } else {
              mEdCountryDown.setText("");
            }

          } catch (JSONException e) {
            e.printStackTrace();
          }
        }
        else {
            if (mPreference.getRateJsonObj() != null && !mPreference.getRateJsonObj().isEmpty()){
              mEdCountryUp.setSelection(mEdCountryUp.getText().length());
              try {
                ratesObject = new JSONObject(mPreference.getRateJsonObj());
                if (isoUp == null && isoDown == null) {
                  isoUp = mCountryNameUp.getText().toString();
                  isoDown = mCountryNameDown.getText().toString();
                }
                IsoUpRate = ratesObject.getDouble(isoUp);
                IsoDownRate = ratesObject.getDouble(isoDown);
                mDoubValueCountryUp = Double.valueOf(mValueCountryUp);
                Double ans1ConVert = mDoubValueCountryUp / IsoUpRate;
                Double ans = ans1ConVert * IsoDownRate;
                Double conDecimal = Double.valueOf(df.format(ans));
                String finalAns = String.valueOf(conDecimal);
                mEdCountryDown.setText(String.format("%.2f", ans));
              } catch (JSONException e) {
                e.printStackTrace();
              }
            }else {
              Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG)
                  .show();
            }

          }
        }

      } else if (requestCode == 3) {
        //   country = data.getStringExtra("name");
        flag = data.getIntExtra("map", 1);
        iso = data.getStringExtra("iso");
        mCountryNameDown.setText(iso);
        mFlagCountryDown.setImageResource(flag);
        mFlagCountryDown.setTag(flag);
        isoDown = mCountryNameDown.getText().toString();

        DecimalFormat df = new DecimalFormat("#.##");

        if (isNetworkAvailable()) {
          try {
            if (isoUp == null && isoDown == null) {
              isoUp = mCountryNameUp.getText().toString();
              isoDown = mCountryNameDown.getText().toString();
            }

            mValueCountryUp = mEdCountryUp.getText().toString();
            if (!mValueCountryUp.isEmpty()) {
              if (ratesObject == null) {
                if (mPreference.getRateJsonObj() != null && !mPreference.getRateJsonObj()
                    .isEmpty()) {
                  ratesObject = new JSONObject(mPreference.getRateJsonObj());
                } else {
                  Snackbar snackbar = Snackbar
                      .make(findViewById(android.R.id.content), "Loading rates, please wait...",
                          Snackbar.LENGTH_SHORT);
                  snackbar.show();
                  return;
                }
              }
              IsoUpRate = ratesObject.getDouble(isoUp);
              IsoDownRate = ratesObject.getDouble(isoDown);
              mDoubValueCountryUp = Double.valueOf(mValueCountryUp);
              Double ans1ConVert = mDoubValueCountryUp / IsoUpRate;
              Double ans = ans1ConVert * IsoDownRate;
              Double conDecimal = Double.valueOf(df.format(ans));
              String finalAns = String.valueOf(conDecimal);
              mEdCountryDown.setText(String.format("%.2f", ans));

            } else {
              mEdCountryDown.setText("");
            }

          } catch (JSONException e) {
            e.printStackTrace();
          }
        }
           else {
            if (mPreference.getRateJsonObj() != null && !mPreference.getRateJsonObj().isEmpty()){
              mEdCountryUp.setSelection(mEdCountryUp.getText().length());
              try {
                ratesObject = new JSONObject(mPreference.getRateJsonObj());
                if (isoUp == null && isoDown == null) {
                  isoUp = mCountryNameUp.getText().toString();
                  isoDown = mCountryNameDown.getText().toString();
                }
                IsoUpRate = ratesObject.getDouble(isoUp);
                IsoDownRate = ratesObject.getDouble(isoDown);
                mDoubValueCountryUp = Double.valueOf(mValueCountryUp);
                Double ans1ConVert = mDoubValueCountryUp / IsoUpRate;
                Double ans = ans1ConVert * IsoDownRate;
                Double conDecimal = Double.valueOf(df.format(ans));
                mEdCountryDown.setText(String.format("%.2f", ans));
              } catch (JSONException e) {
                e.printStackTrace();
              }
            }else {
              Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG)
                  .show();
            }
        }

      }
    }


  @Override
  protected void onStart() {
    super.onStart();
    loadCurrencyExchangeData();
  }

  @Override
  protected void onResume() {
    super.onResume();
    loadCurrencyExchangeData();
  }

  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);
    if(hasFocus){
      loadCurrencyExchangeData();
    }
  }

  @Override
  public void onNumberClicked(int i) {
    addString(String.valueOf(i));
  }

  @Override
  public void onLeftAuxButtonClicked() {
    if (!mEdCountryUp.getText().toString().isEmpty()) {
      int index = getEditSelection();
      deleteEditValue(index);
    }
  }

  @Override
  public void onRightAuxButtonClicked() {
    mEdCountryUp.getText().clear();
  }

  public int getEditSelection() {
    return mEdCountryUp.getSelectionStart();
  }

  public void deleteEditValue(int index) {
    mEdCountryUp.getText().delete(index - 1, index);
  }

  public void addString(String sequence) {
    int index = getEditSelection();// The location of the cursor
    if (index < 0 || index >= getEditTextViewString().length()) {
      mEdCountryUp.append(sequence);
      //   Log.i(TAG, "str===" + str);
    } else {
      mEdCountryUp.getEditableText().insert(index, sequence);// Insert text cursor position
    }
  }

  // Access to the contents of the text box
  public String getEditTextViewString() {
    return mEdCountryUp.getText().toString();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    isoUp = mCountryNameUp.getText().toString();
    isoDown = mCountryNameDown.getText().toString();

    if (!mEdCountryUp.getText().toString().isEmpty()) {
      mPreference.saveValueCountryUp(mEdCountryUp.getText().toString());
    }

    if (!mEdCountryDown.getText().toString().isEmpty()) {
      mPreference.saveValueCountryDown(mEdCountryDown.getText().toString());
    }

    if (!isoUp.isEmpty()) {
      mPreference.saveCountryUpIso(isoUp);
    }

    if (!isoDown.isEmpty()) {
      mPreference.saveCountryDownIso(isoDown);
    }
    int iconUp = getDrawableId(mFlagCountryUp);
    int iconDown = getDrawableId(mFlagCountryDown);
    mPreference.saveCountryUpMap(iconUp);
    mPreference.saveCountryDownMap(iconDown);
  }

  private void getSharedPrefence() {
    if (!mPreference.getValueCountryUp().isEmpty()) {
      mEdCountryUp.setText(mPreference.getValueCountryUp());
    }

    if (!mPreference.getValueCountryDown().isEmpty()) {
      mEdCountryDown.setText(mPreference.getValueCountryDown());
    }

    if (!mPreference.getCountryUpIso().isEmpty()) {
      mCountryNameUp.setText(mPreference.getCountryUpIso());
    } else {
      mCountryNameUp.setText("NGN");
    }

    if (!mPreference.getCountryDownIso().isEmpty()) {
      mCountryNameDown.setText(mPreference.getCountryDownIso());
    } else {
      mCountryNameDown.setText("USD");
    }

    if (mPreference.getCountryUpMap() != 0) {
      mFlagCountryUp.setImageResource(mPreference.getCountryUpMap());
      mFlagCountryUp.setTag(mPreference.getCountryUpMap());
    } else {
      mFlagCountryUp.setImageResource(R.mipmap.nigeria);
      mFlagCountryUp.setTag(R.mipmap.nigeria);
    }

    if (mPreference.getCountryDownMap() != 0) {
      mFlagCountryDown.setImageResource(mPreference.getCountryDownMap());
      mFlagCountryDown.setTag(mPreference.getCountryDownMap());
    } else {
      mFlagCountryDown.setImageResource(R.mipmap.united_states);
      mFlagCountryDown.setTag(R.mipmap.united_states);
    }

  }

  @Override
  public void onClick(View v) {
    int GUI_Id = v.getId();
    switch (GUI_Id) {

      case R.id.rotate:
        String mUpperIsoValue = mCountryNameUp.getText().toString();
        String mDownIsoValue = mCountryNameDown.getText().toString();
        int iconUp = getDrawableId(mFlagCountryUp);
        int iconDown = getDrawableId(mFlagCountryDown);
        mFlagCountryDown.setImageResource(iconUp);
        mFlagCountryDown.setTag(iconUp);
        mFlagCountryUp.setImageResource(iconDown);
        mFlagCountryUp.setTag(iconDown);
        mCountryNameUp.setText(mDownIsoValue);
        mCountryNameDown.setText(mUpperIsoValue);
        isoUp = mCountryNameUp.getText().toString();
        isoDown = mCountryNameDown.getText().toString();
        DecimalFormat df = new DecimalFormat("#.##");
        if (isNetworkAvailable()) {
          try {
            if (isoUp == null && isoDown == null) {
              isoUp = mCountryNameUp.getText().toString();
              isoDown = mCountryNameDown.getText().toString();
            } else {
              isoUp = mCountryNameUp.getText().toString();
              isoDown = mCountryNameDown.getText().toString();
            }
            mEdCountryUp.setText(mEdCountryDown.getText().toString());
            mValueCountryUp = mEdCountryUp.getText().toString();

            if (!mValueCountryUp.isEmpty()) {
              mEdCountryDown.setText("");
              IsoUpRate = ratesObject.getDouble(isoUp);
              IsoDownRate = ratesObject.getDouble(isoDown);
              mDoubValueCountryUp = Double.valueOf(mValueCountryUp);
              Double ans1ConVert = mDoubValueCountryUp / IsoUpRate;
              Double ans = ans1ConVert * IsoDownRate;
              Double conDecimal = Double.valueOf(df.format(ans));
              String finalAns = String.valueOf(conDecimal);
              mEdCountryDown.setText(String.format("%.2f", ans));

            } else {
              mEdCountryDown.setText("");
            }

          } catch (JSONException e) {
            e.printStackTrace();
          }

        } else {
          if (mPreference.getRateJsonObj() != null && !mPreference.getRateJsonObj().isEmpty()){
            mEdCountryUp.setSelection(mEdCountryUp.getText().length());
            try {
              ratesObject = new JSONObject(mPreference.getRateJsonObj());
              if (isoUp == null && isoDown == null) {
                isoUp = mCountryNameUp.getText().toString();
                isoDown = mCountryNameDown.getText().toString();
              } else {
                isoUp = mCountryNameUp.getText().toString();
                isoDown = mCountryNameDown.getText().toString();
              }
              mEdCountryUp.setText(mEdCountryDown.getText().toString());
              mValueCountryUp = mEdCountryUp.getText().toString();

              if (!mValueCountryUp.isEmpty()) {
                mEdCountryDown.setText("");
                IsoUpRate = ratesObject.getDouble(isoUp);
                IsoDownRate = ratesObject.getDouble(isoDown);
                mDoubValueCountryUp = Double.valueOf(mValueCountryUp);
                Double ans1ConVert = mDoubValueCountryUp / IsoUpRate;
                Double ans = ans1ConVert * IsoDownRate;
                Double conDecimal = Double.valueOf(df.format(ans));
                String finalAns = String.valueOf(conDecimal);
                mEdCountryDown.setText(String.format("%.2f", ans));

              } else {
                mEdCountryDown.setText("");
              }
            } catch (JSONException e) {
              e.printStackTrace();
            }
          }else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG)
                .show();
          }
        }

        break;
    }
  }
}

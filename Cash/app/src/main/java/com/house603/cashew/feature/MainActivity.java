package com.house603.cashew.feature;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
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
import com.house603.cashew.R;
import com.house603.cashew.feature.adapter.MainNavAdapter;
import com.house603.cashew.feature.adapter.MainNavListener;
import com.house603.cashew.feature.model.CurrencyModel;
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
        if (isNetworkAvailable()) {
          mEdCountryUp.setSelection(mEdCountryUp.getText().length());
          try {
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
        } else {
          Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG)
              .show();
        }

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

  private boolean isNetworkAvailable() {
    ConnectivityManager connectivityManager
        = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
  }


  @SuppressLint("ClickableViewAccessibility")
  private void initCallKeyboardAction() {
    ImageButton mBtnBack = (ImageButton) findViewById(R.id.btn_clear);
    ImageButton mDelNum = (ImageButton) findViewById(R.id.btnback);

    Button mBtn1 = (Button) findViewById(R.id.btn1);
    Button mBtn2 = (Button) findViewById(R.id.btn2);
    Button mBtn3 = (Button) findViewById(R.id.btn3);
    Button mBtn4 = (Button) findViewById(R.id.btn4);
    Button mBtn5 = (Button) findViewById(R.id.btn5);
    Button mBtn6 = (Button) findViewById(R.id.btn6);
    Button mBtn7 = (Button) findViewById(R.id.btn7);
    Button mBtn8 = (Button) findViewById(R.id.btn8);
    Button mBtn9 = (Button) findViewById(R.id.btn9);
    Button mBtn0 = (Button) findViewById(R.id.btn0);
    findViewById(R.id.rotate).setOnClickListener(this);
    //     mBtnhas = (Button) findViewById(R.id.btnhas);

    mBtn1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEdCountryUp.setText(mEdCountryUp.getText().insert(mEdCountryUp.getText().length(), "1"));
      }
    });
    mBtn2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEdCountryUp.setText(mEdCountryUp.getText().insert(mEdCountryUp.getText().length(), "2"));
      }
    });
    mBtn3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEdCountryUp.setText(mEdCountryUp.getText().insert(mEdCountryUp.getText().length(), "3"));
      }
    });
    mBtn4.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEdCountryUp.setText(mEdCountryUp.getText().insert(mEdCountryUp.getText().length(), "4"));
      }
    });
    mBtn5.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEdCountryUp.setText(mEdCountryUp.getText().insert(mEdCountryUp.getText().length(), "5"));
      }
    });
    mBtn6.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEdCountryUp.setText(mEdCountryUp.getText().insert(mEdCountryUp.getText().length(), "6"));
      }
    });
    mBtn7.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEdCountryUp.setText(mEdCountryUp.getText().insert(mEdCountryUp.getText().length(), "7"));
      }
    });
    mBtn8.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEdCountryUp.setText(mEdCountryUp.getText().insert(mEdCountryUp.getText().length(), "8"));
      }
    });
    mBtn9.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEdCountryUp.setText(mEdCountryUp.getText().insert(mEdCountryUp.getText().length(), "9"));
      }
    });
    mBtn0.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEdCountryUp.setText(mEdCountryUp.getText().insert(mEdCountryUp.getText().length(), "0"));
      }
    });

    mDelNum.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // Toast.makeText(getApplicationContext(),"click",Toast.LENGTH_SHORT).show();
        if (!mEdCountryUp.getText().toString().isEmpty()) {
          mEdCountryUp.setText(mEdCountryUp.getText()
              .delete(mEdCountryUp.getText().length() - 1,
                  mEdCountryUp.getText().length()));
        }
      }
    });

    mBtnBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEdCountryUp.setText("");
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
  }

  @Override
  public void onClick(View v) {
    String value = mEdCountryUp.getText().toString();
    int GUI_Id = v.getId();
    switch (GUI_Id) {
      case R.id.btnpoint:
        if (value.contains(".")) {
          return;
        } else {
          value += ".";
        }
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
          Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG)
              .show();
        }

        break;
      default:
        Button sender = (Button) v;
        value += sender.getText();
        mEdCountryUp.setText(value);
    }

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
          Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG)
              .show();
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

            mValueCountryUp =  mEdCountryUp.getText().toString();
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
        } else {
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

}

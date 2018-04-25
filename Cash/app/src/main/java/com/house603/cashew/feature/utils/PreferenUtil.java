package com.house603.cashew.feature.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;


/**
 * Created by Tinh Vu on 27/09/2016.
 */

public class PreferenUtil {

  public static final String COUNTRYUPISO = "countryupiso";
  public static final String COUNTRYDOWNISO = "countrydowniso";
  public static final String COUNTRYUPMAP = "countryupmap";
  public static final String COUNTRYDOWNMAP = "countrydownmap";
  public static final String COUNTRYUPVALUE = "countryupvalue";
  public static final String COUNTRYDOWNVALUE = "countrydownvalue";
  public static final String RATEJSONOBJ = "ratejsonobj";

  private static Context mContext;
  @SuppressLint("StaticFieldLeak")
  private static PreferenUtil mInstant = null;

  private PreferenUtil(Context context) {
    mContext = context;
  }

  public static PreferenUtil getInstant(Context context) {
    if (mInstant == null) {
      mInstant = new PreferenUtil(context);
      PreferenceManager.getDefaultSharedPreferences(context)
          .registerOnSharedPreferenceChangeListener(mListener);
    }
    return mInstant;
  }

  private static SharedPreferences.OnSharedPreferenceChangeListener mListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    }
  };


  public void saveValueCountryUp(String valueUp) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
    sharedPreferencesEditor.putString(COUNTRYUPVALUE, valueUp);
    sharedPreferencesEditor.apply();
  }

  public String getValueCountryUp() {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    return sharedPreferences.getString(COUNTRYUPVALUE, "");
  }

  public void saveValueCountryDown(String valueUp) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
    sharedPreferencesEditor.putString(COUNTRYDOWNVALUE, valueUp);
    sharedPreferencesEditor.apply();
  }

  public String getValueCountryDown() {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    return sharedPreferences.getString(COUNTRYDOWNVALUE, "");
  }


  public void saveCountryUpIso(String isoUp) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
    sharedPreferencesEditor.putString(COUNTRYUPISO, isoUp);
    sharedPreferencesEditor.apply();

  }

  public String getCountryUpIso() {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    return sharedPreferences.getString(COUNTRYUPISO, "");
  }

  public void saveCountryDownIso(String isoDown) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
    sharedPreferencesEditor.putString(COUNTRYDOWNISO, isoDown);
    sharedPreferencesEditor.apply();

  }

  public String getCountryDownIso() {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    return sharedPreferences.getString(COUNTRYDOWNISO, "");
  }

  public void saveCountryUpMap(int icon) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
   // String map = setProfilePhoto(imageView);
    sharedPreferencesEditor.putInt(COUNTRYUPMAP, icon);
    sharedPreferencesEditor.apply();

  }

  public int getCountryUpMap() {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    return sharedPreferences.getInt(COUNTRYUPMAP, 0);
  }

  public void saveCountryDownMap(int icon) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
  //  String map = setProfilePhoto(mapDown);
    sharedPreferencesEditor.putInt(COUNTRYDOWNMAP, icon);
    sharedPreferencesEditor.apply();
  }

  public int getCountryDownMap() {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    return sharedPreferences.getInt(COUNTRYDOWNMAP, 0);
  }

  public String setProfilePhoto(ImageView view) {
    BitmapDrawable drawable = (BitmapDrawable) view.getDrawable();
    Bitmap bitmap = drawable.getBitmap();
   // Bitmap bitmap = view.getDrawingCache();
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
    byte[] image = stream.toByteArray();
    return Base64.encodeToString(image, 0);

  }

  // method for base64 to bitmap
  public static Bitmap decodeBase64(String input) {
    byte[] decodedByte = Base64.decode(input, 0);
    return BitmapFactory
        .decodeByteArray(decodedByte, 0, decodedByte.length);
  }

  public void saveRateObjectJson(String jsonString) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
    sharedPreferencesEditor.putString(RATEJSONOBJ, jsonString);
    sharedPreferencesEditor.apply();

  }

  public String getRateJsonObj() {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    return sharedPreferences.getString(RATEJSONOBJ, "");
  }

}

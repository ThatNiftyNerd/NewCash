package com.house603.cash.feature.model;

import java.io.Serializable;

/**
 * Created by Admin on 2/25/2017.
 */

public class CurrencyModel implements Serializable {
    private String mCountryName, mCurrencyiso;

    public String getmItemName() {
        return mItemName;
    }

    public void setmItemName(String mItemName) {
        this.mItemName = mItemName;
    }

    private String mItemName;
    private int flag;
    private double rate;
    private String mHeadlineNews;
    private String mUrlNews;
    private String mDescriptionNews;
    private String mAuthorNews;
    private String mUrlImageNews;


    public String getmCountryName() {
        return mCountryName;
    }

    public void setmCountryName(String mCountryName) {
        this.mCountryName = mCountryName;
    }

    public String getmCurrencyiso() {
        return mCurrencyiso;
    }

    public void setmCurrencyiso(String mCurrencyiso) {
        this.mCurrencyiso = mCurrencyiso;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getmUrlNews() {
        return mUrlNews;
    }

    public void setmUrlNews(String mUrlNews) {
        this.mUrlNews = mUrlNews;
    }

    public String getmHeadlineNews() {
        return mHeadlineNews;
    }

    public void setmHeadlineNews(String mHeadlineNews) {
        this.mHeadlineNews = mHeadlineNews;
    }

    public String getmDescriptionNews() {
        return mDescriptionNews;
    }

    public void setmDescriptionNews(String mDescriptionNews) {
        this.mDescriptionNews = mDescriptionNews;
    }

    public String getmAuthorNews() {
        return mAuthorNews;
    }

    public void setmAuthorNews(String mAuthorNews) {
        this.mAuthorNews = mAuthorNews;
    }

    public String getmUrlImageNews() {
        return mUrlImageNews;
    }

    public void setmUrlImageNews(String mUrlImageNews) {
        this.mUrlImageNews = mUrlImageNews;
    }
}

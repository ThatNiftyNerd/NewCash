package com.house603.cashew.feature.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class HistoricalModel{

	@SerializedName("date")
	private String date;

	@SerializedName("terms")
	private String terms;

	@SerializedName("success")
	private boolean success;

	@SerializedName("privacy")
	private String privacy;

	@SerializedName("historical")
	private boolean historical;

	@SerializedName("source")
	private String source;

	@SerializedName("timestamp")
	private int timestamp;

	@SerializedName("quotes")
	private Quotes quotes;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setTerms(String terms){
		this.terms = terms;
	}

	public String getTerms(){
		return terms;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setPrivacy(String privacy){
		this.privacy = privacy;
	}

	public String getPrivacy(){
		return privacy;
	}

	public void setHistorical(boolean historical){
		this.historical = historical;
	}

	public boolean isHistorical(){
		return historical;
	}

	public void setSource(String source){
		this.source = source;
	}

	public String getSource(){
		return source;
	}

	public void setTimestamp(int timestamp){
		this.timestamp = timestamp;
	}

	public int getTimestamp(){
		return timestamp;
	}

	public void setQuotes(Quotes quotes){
		this.quotes = quotes;
	}

	public Quotes getQuotes(){
		return quotes;
	}

	@Override
 	public String toString(){
		return 
			"HistoricalModel{" + 
			"date = '" + date + '\'' + 
			",terms = '" + terms + '\'' + 
			",success = '" + success + '\'' + 
			",privacy = '" + privacy + '\'' + 
			",historical = '" + historical + '\'' + 
			",source = '" + source + '\'' + 
			",timestamp = '" + timestamp + '\'' + 
			",quotes = '" + quotes + '\'' + 
			"}";
		}
}
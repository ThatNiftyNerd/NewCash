package com.house603.cashew.feature.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;
import org.json.JSONException;
import org.json.JSONObject;

@Generated("com.robohorse.robopojogenerator")
public class ExchangeRate{

	@SerializedName("license")
	public String license;

	@SerializedName("rates")
  public List<Rates> rates;

	@SerializedName("disclaimer")
  public String disclaimer;

	@SerializedName("timestamp")
  public int timestamp;

	@SerializedName("base")
  public String base;


	public String getLicense(){
		return license;
	}

//	public void setRates(Rates rates){
//		this.rates = rates;
//	}
//


  public void setLicense(String license) {
    this.license = license;
  }

  public void setRates(List<Rates> rates) {
    this.rates = rates;
  }

  public void setDisclaimer(String disclaimer) {
    this.disclaimer = disclaimer;
  }

  public void setTimestamp(int timestamp) {
    this.timestamp = timestamp;
  }

  public void setBase(String base) {
    this.base = base;
  }

  public List<Rates> getRates() {
    return rates;
  }


	public String getDisclaimer(){
		return disclaimer;
	}


	public int getTimestamp(){
		return timestamp;
	}


	public String getBase(){
		return base;
	}

	@Override
 	public String toString(){
		return 
			"ExchangeRate{" + 
			"license = '" + license + '\'' + 
			",rates = '" + rates + '\'' + 
			",disclaimer = '" + disclaimer + '\'' + 
			",timestamp = '" + timestamp + '\'' + 
			",base = '" + base + '\'' + 
			"}";
		}
	public static ExchangeRate fromJson(JSONObject jsonObject) {
		ExchangeRate b = new ExchangeRate();
		// Deserialize json into object fields
		try {
			b.license = jsonObject.getString("license");
			b.timestamp = jsonObject.getInt("timestamp");
			b.disclaimer = jsonObject.getString("disclaimer");
      b.rates = (List<Rates>) jsonObject.getJSONArray("rates");
			b.base = jsonObject.getString("base");

		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		// Return new object
		return b;
	}
}

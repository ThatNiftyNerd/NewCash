package com.house603.cash.feature.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Quotes{

	@SerializedName("USDMXN")
	private double uSDMXN;

	@SerializedName("USDPLN")
	private double uSDPLN;

	@SerializedName("USDUSD")
	private int uSDUSD;

	@SerializedName("USDCAD")
	private double uSDCAD;

	public void setUSDMXN(double uSDMXN){
		this.uSDMXN = uSDMXN;
	}

	public double getUSDMXN(){
		return uSDMXN;
	}

	public void setUSDPLN(double uSDPLN){
		this.uSDPLN = uSDPLN;
	}

	public double getUSDPLN(){
		return uSDPLN;
	}

	public void setUSDUSD(int uSDUSD){
		this.uSDUSD = uSDUSD;
	}

	public int getUSDUSD(){
		return uSDUSD;
	}

	public void setUSDCAD(double uSDCAD){
		this.uSDCAD = uSDCAD;
	}

	public double getUSDCAD(){
		return uSDCAD;
	}

	@Override
 	public String toString(){
		return 
			"Quotes{" + 
			"uSDMXN = '" + uSDMXN + '\'' + 
			",uSDPLN = '" + uSDPLN + '\'' + 
			",uSDUSD = '" + uSDUSD + '\'' + 
			",uSDCAD = '" + uSDCAD + '\'' + 
			"}";
		}
}
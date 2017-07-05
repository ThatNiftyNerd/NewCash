package com.house603.cash.feature.model;

public class GlobalStockModel{
	private String jsonMember06LowCurrentTradingDay;
	private String jsonMember01Symbol;
	private String jsonMember07ClosePreviousTradingDay;
	private String jsonMember04OpenCurrentTradingDay;
	private String jsonMember02ExchangeName;
	private String jsonMember11LastUpdated;
	private String jsonMember03LatestPrice;
	private String jsonMember05HighCurrentTradingDay;
	private String jsonMember09PriceChangePercentage;
	private String jsonMember08PriceChange;
	private String jsonMember10VolumeCurrentTradingDay;

	public void setJsonMember06LowCurrentTradingDay(String jsonMember06LowCurrentTradingDay){
		this.jsonMember06LowCurrentTradingDay = jsonMember06LowCurrentTradingDay;
	}

	public String getJsonMember06LowCurrentTradingDay(){
		return jsonMember06LowCurrentTradingDay;
	}

	public void setJsonMember01Symbol(String jsonMember01Symbol){
		this.jsonMember01Symbol = jsonMember01Symbol;
	}

	public String getJsonMember01Symbol(){
		return jsonMember01Symbol;
	}

	public void setJsonMember07ClosePreviousTradingDay(String jsonMember07ClosePreviousTradingDay){
		this.jsonMember07ClosePreviousTradingDay = jsonMember07ClosePreviousTradingDay;
	}

	public String getJsonMember07ClosePreviousTradingDay(){
		return jsonMember07ClosePreviousTradingDay;
	}

	public void setJsonMember04OpenCurrentTradingDay(String jsonMember04OpenCurrentTradingDay){
		this.jsonMember04OpenCurrentTradingDay = jsonMember04OpenCurrentTradingDay;
	}

	public String getJsonMember04OpenCurrentTradingDay(){
		return jsonMember04OpenCurrentTradingDay;
	}

	public void setJsonMember02ExchangeName(String jsonMember02ExchangeName){
		this.jsonMember02ExchangeName = jsonMember02ExchangeName;
	}

	public String getJsonMember02ExchangeName(){
		return jsonMember02ExchangeName;
	}

	public void setJsonMember11LastUpdated(String jsonMember11LastUpdated){
		this.jsonMember11LastUpdated = jsonMember11LastUpdated;
	}

	public String getJsonMember11LastUpdated(){
		return jsonMember11LastUpdated;
	}

	public void setJsonMember03LatestPrice(String jsonMember03LatestPrice){
		this.jsonMember03LatestPrice = jsonMember03LatestPrice;
	}

	public String getJsonMember03LatestPrice(){
		return jsonMember03LatestPrice;
	}

	public void setJsonMember05HighCurrentTradingDay(String jsonMember05HighCurrentTradingDay){
		this.jsonMember05HighCurrentTradingDay = jsonMember05HighCurrentTradingDay;
	}

	public String getJsonMember05HighCurrentTradingDay(){
		return jsonMember05HighCurrentTradingDay;
	}

	public void setJsonMember09PriceChangePercentage(String jsonMember09PriceChangePercentage){
		this.jsonMember09PriceChangePercentage = jsonMember09PriceChangePercentage;
	}

	public String getJsonMember09PriceChangePercentage(){
		return jsonMember09PriceChangePercentage;
	}

	public void setJsonMember08PriceChange(String jsonMember08PriceChange){
		this.jsonMember08PriceChange = jsonMember08PriceChange;
	}

	public String getJsonMember08PriceChange(){
		return jsonMember08PriceChange;
	}

	public void setJsonMember10VolumeCurrentTradingDay(String jsonMember10VolumeCurrentTradingDay){
		this.jsonMember10VolumeCurrentTradingDay = jsonMember10VolumeCurrentTradingDay;
	}

	public String getJsonMember10VolumeCurrentTradingDay(){
		return jsonMember10VolumeCurrentTradingDay;
	}

	@Override
 	public String toString(){
		return 
			"GlobalStockModel{" + 
			"06. Low (Current Trading Day) = '" + jsonMember06LowCurrentTradingDay + '\'' + 
			",01. Symbol = '" + jsonMember01Symbol + '\'' + 
			",07. Close (Previous Trading Day) = '" + jsonMember07ClosePreviousTradingDay + '\'' + 
			",04. Open (Current Trading Day) = '" + jsonMember04OpenCurrentTradingDay + '\'' + 
			",02. Exchange Name = '" + jsonMember02ExchangeName + '\'' + 
			",11. Last Updated = '" + jsonMember11LastUpdated + '\'' + 
			",03. Latest Price = '" + jsonMember03LatestPrice + '\'' + 
			",05. High (Current Trading Day) = '" + jsonMember05HighCurrentTradingDay + '\'' + 
			",09. Price Change Percentage = '" + jsonMember09PriceChangePercentage + '\'' + 
			",08. Price Change = '" + jsonMember08PriceChange + '\'' + 
			",10. Volume (Current Trading Day) = '" + jsonMember10VolumeCurrentTradingDay + '\'' + 
			"}";
		}
}

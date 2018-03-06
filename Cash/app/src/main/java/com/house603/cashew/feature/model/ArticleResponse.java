package com.house603.cashew.feature.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ArticleResponse {

	@SerializedName("sortBy")
	@Expose
	private String sortBy;

	@SerializedName("source")
	@Expose
	private String source;

	@SerializedName("articles")
	@Expose
	private List<ArticlesItem> articles;

	@SerializedName("status")
	@Expose
	private String status;

	public void setSortBy(String sortBy){
		this.sortBy = sortBy;
	}

	public String getSortBy(){
		return sortBy;
	}

	public void setSource(String source){
		this.source = source;
	}

	public String getSource(){
		return source;
	}

	public void setArticles(List<ArticlesItem> articles){
		this.articles = articles;
	}

	public List<ArticlesItem> getArticles(){
		return articles;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ArticleResponse{" +
			"sortBy = '" + sortBy + '\'' + 
			",source = '" + source + '\'' + 
			",articles = '" + articles + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
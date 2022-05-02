package it.vegans.testapp.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class VegansModel{

	@SerializedName("lang")
	private String lang;

	@SerializedName("content")
	private List<ContentItem> content;

	@SerializedName("status")
	private boolean status;

	public String getLang(){
		return lang;
	}

	public List<ContentItem> getContent(){
		return content;
	}

	public boolean isStatus(){
		return status;
	}
}
package it.vegans.testapp.models;

import com.google.gson.annotations.SerializedName;

public class MediaDate{

	@SerializedName("year")
	private String year;

	@SerializedName("dateString")
	private String dateString;

	public String getYear(){
		return year;
	}

	public String getDateString(){
		return dateString;
	}
}
package it.vegans.testapp.models;

import com.google.gson.annotations.SerializedName;

public class ContentItem{

	@SerializedName("mediaUrl")
	private String mediaUrl;

	@SerializedName("mediaUrlBig")
	private String mediaUrlBig;

	@SerializedName("mediaType")
	private String mediaType;

	@SerializedName("mediaId")
	private int mediaId;

	@SerializedName("mediaTitleCustom")
	private String mediaTitleCustom;

	@SerializedName("mediaDate")
	private MediaDate mediaDate;

	public String getMediaUrl(){
		return mediaUrl;
	}

	public String getMediaUrlBig(){
		return mediaUrlBig;
	}

	public String getMediaType(){
		return mediaType;
	}

	public int getMediaId(){
		return mediaId;
	}

	public String getMediaTitleCustom(){
		return mediaTitleCustom;
	}

	public MediaDate getMediaDate(){
		return mediaDate;
	}
}
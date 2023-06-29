package com.example.uas_audio_muhammadarifaditya.model.SurahModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Chapter{

	@SerializedName("chapters")
	private List<ChaptersItem> chapters;

	public List<ChaptersItem> getChapters(){
		return chapters;
	}

	@Override
 	public String toString(){
		return 
			"Chapter{" + 
			"chapters = '" + chapters + '\'' + 
			"}";
		}
}
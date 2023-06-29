package com.example.uas_audio_muhammadarifaditya.model.terjemahan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Terjemahan{

	@SerializedName("translations")
	private List<TranslationsItem> translations;

	@SerializedName("meta")
	private Meta meta;

	public List<TranslationsItem> getTranslations(){
		return translations;
	}

	public Meta getMeta(){
		return meta;
	}

	@Override
 	public String toString(){
		return 
			"Terjemahan{" + 
			"translations = '" + translations + '\'' + 
			",meta = '" + meta + '\'' + 
			"}";
		}
}
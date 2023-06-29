package com.example.uas_audio_muhammadarifaditya.model.AyatModel;

import com.google.gson.annotations.SerializedName;

public class Filters{

    @SerializedName("chapter_number")
    private String chapterNumber;

    public String getChapterNumber(){
        return chapterNumber;
    }

    @Override
    public String toString(){
        return
                "Filters{" +
                        "chapter_number = '" + chapterNumber + '\'' +
                        "}";
    }
}

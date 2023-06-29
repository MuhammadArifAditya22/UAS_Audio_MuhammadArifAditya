package com.example.uas_audio_muhammadarifaditya.model.AyatModel;

import com.google.gson.annotations.SerializedName;

public class Meta{

    @SerializedName("filters")
    private Filters filters;

    public Filters getFilters(){
        return filters;
    }

    @Override
    public String toString(){
        return
                "Meta{" +
                        "filters = '" + filters + '\'' +
                        "}";
    }
}

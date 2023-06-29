package com.example.uas_audio_muhammadarifaditya.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServices {
    private static String BASE_URL = "https://api.quran.com/api/v4/";
    private static Retrofit retrofit;

    public static ApiEndPoint endPoint (){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiEndPoint.class);

    }
}

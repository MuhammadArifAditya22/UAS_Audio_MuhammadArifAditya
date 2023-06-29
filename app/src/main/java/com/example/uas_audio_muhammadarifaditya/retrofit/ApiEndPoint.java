package com.example.uas_audio_muhammadarifaditya.retrofit;

import com.example.uas_audio_muhammadarifaditya.model.Audio.Audio;
import com.example.uas_audio_muhammadarifaditya.model.AyatModel.Verses;
import com.example.uas_audio_muhammadarifaditya.model.SurahModel.Chapter;
import com.example.uas_audio_muhammadarifaditya.model.terjemahan.Terjemahan;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndPoint {

    @GET("chapters?language=id")
    Call<Chapter> getSurah();

    @GET("quran/verses/uthmani")
    Call<Verses> getAyat(@Query("chapter_number") int id);

    @GET("quran/translations/174")
    Call<Terjemahan> getText(@Query("chapter_number") int id);

    @GET("chapter_recitations/33?")
    Call<Audio> getAudio();
}

package com.example.uas_audio_muhammadarifaditya;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.uas_audio_muhammadarifaditya.model.Audio.Audio;
import com.example.uas_audio_muhammadarifaditya.model.Audio.AudioFilesItem;
import com.example.uas_audio_muhammadarifaditya.model.SurahModel.Chapter;
import com.example.uas_audio_muhammadarifaditya.model.SurahModel.ChaptersItem;
import com.example.uas_audio_muhammadarifaditya.retrofit.ApiServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<ChaptersItem> Surah = new ArrayList<>();
    List<ChaptersItem> DaftarSurah;
    private List<AudioFilesItem> Audio = new ArrayList<>();
    List<AudioFilesItem> DaftarAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDataFromApi();
    }

    private void setUpRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        mainAdapter = new MainAdapter(Audio, Surah);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainAdapter);
    }

    private void setUpView() {
        recyclerView = findViewById(R.id.recyclerview);
    }

    private void getDataFromApi() {
        ApiServices.endPoint().getSurah().enqueue(new Callback<Chapter>() {
            @Override
            public void onResponse(@NonNull Call<Chapter> call, @NonNull Response<Chapter> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    MainActivity.this.DaftarSurah = response.body().getChapters();
                    getDataFromApiAudio();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Chapter> call, @NonNull Throwable t) {
                Log.d("ErrorMain", t.toString());
            }
        });
    }

    private void getDataFromApiAudio() {
        ApiServices.endPoint().getAudio().enqueue(new Callback<Audio>() {
            @Override
            public void onResponse(@NonNull Call<Audio> call, @NonNull Response<Audio> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    MainActivity.this.DaftarAudio = response.body().getAudioFiles();
                    setUpView();
                    setUpRecyclerView();
                    mainAdapter.setData(DaftarSurah, DaftarAudio);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Audio> call, @NonNull Throwable t) {

            }
        });
    }
}

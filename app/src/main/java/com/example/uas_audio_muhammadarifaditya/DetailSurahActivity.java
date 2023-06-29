package com.example.uas_audio_muhammadarifaditya;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas_audio_muhammadarifaditya.model.AyatModel.Verses;
import com.example.uas_audio_muhammadarifaditya.model.AyatModel.VersesItem;
import com.example.uas_audio_muhammadarifaditya.model.SurahModel.ChaptersItem;
import com.example.uas_audio_muhammadarifaditya.model.terjemahan.Terjemahan;
import com.example.uas_audio_muhammadarifaditya.model.terjemahan.TranslationsItem;
import com.example.uas_audio_muhammadarifaditya.retrofit.ApiServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSurahActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdapterTerjemahan adapterTerjemahan;
    private MediaPlayer mediaPlayer;
    ImageButton btAudio;
    private final List<VersesItem> results = new ArrayList<>();
    private final List<TranslationsItem> terjemahan = new ArrayList<>();
    private List<ChaptersItem> nomor = new ArrayList<>();
    List<TranslationsItem> result;
    List<VersesItem> versesItems;
    TextView textViewNameSimpleSurah, textViewTempatTurunSurah, textViewJumlahAyatSurah,tvArabic;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_surah);

        String nameSimple = getIntent().getStringExtra("name_simple");
        textViewNameSimpleSurah = findViewById(R.id.tvNamaSurah);
        textViewNameSimpleSurah.setText(nameSimple);

        String namaArab = getIntent().getStringExtra("name_arabic");
        tvArabic = findViewById(R.id.tvNamaSurahJudul);
        tvArabic.setText(namaArab);

        int id = getIntent().getIntExtra("id", 1);

        String revelationPlace = getIntent().getStringExtra("revelation_place");
        textViewTempatTurunSurah = findViewById(R.id.tvTurun);
        textViewTempatTurunSurah.setText("Surah Ini Diturunkan Di " + revelationPlace);

        int versesCount = getIntent().getIntExtra("verses_count", 1);
        textViewJumlahAyatSurah = findViewById(R.id.tvJumlahAyat);
        textViewJumlahAyatSurah.setText(versesCount + " Ayat");

        mediaPlayer = new MediaPlayer();
        String audioUrl = getIntent().getStringExtra("audio_url");
        btAudio = findViewById(R.id.tvAudio);
        btAudio.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()) {
                pauseAudio();
            } else {
                playAudio(audioUrl);
            }
        });

        setUpRecyclerView();
        getTranslationData(id);
    }

    private void getTranslationData(int id) {
        ApiServices.endPoint().getText(id).enqueue(new Callback<Terjemahan>() {
            @Override
            public void onResponse(Call<Terjemahan> call, Response<Terjemahan> response) {
                if (response.isSuccessful()) {
                    DetailSurahActivity.this.result = response.body().getTranslations();
                    getVerseData(getIntent().getIntExtra("id", 1));
                }
            }

            @Override
            public void onFailure(Call<Terjemahan> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void getVerseData(int id) {
        ApiServices.endPoint().getAyat(id).enqueue(new Callback<Verses>() {
            @Override
            public void onResponse(Call<Verses> call, Response<Verses> response) {
                if (response.isSuccessful()) {
                    DetailSurahActivity.this.versesItems = response.body().getVerses();
                    adapterTerjemahan.setData(result, versesItems);
                }
            }

            @Override
            public void onFailure(Call<Verses> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void setUpRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewAyat);
        adapterTerjemahan = new AdapterTerjemahan(results, terjemahan, nomor);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTerjemahan);
    }

    private void pauseAudio() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    private void playAudio(String audio) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(audio);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.example.uas_audio_muhammadarifaditya;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas_audio_muhammadarifaditya.model.AyatModel.VersesItem;
import com.example.uas_audio_muhammadarifaditya.model.SurahModel.ChaptersItem;
import com.example.uas_audio_muhammadarifaditya.model.terjemahan.TranslationsItem;

import java.util.List;

public class AdapterTerjemahan extends RecyclerView.Adapter<AdapterTerjemahan.TerjemahanViewHolder> {

    private List<VersesItem> Ayat;
    private List<TranslationsItem> Arti;


    public AdapterTerjemahan(List<VersesItem> ayat, List<TranslationsItem> arti, List<ChaptersItem> nomor) {
        this.Ayat = ayat;
        this.Arti = arti;
    }

    @NonNull
    @Override
    public TerjemahanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ayat, parent, false);
        return new TerjemahanViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TerjemahanViewHolder holder, int position) {
        TranslationsItem result = Arti.get(position);
        VersesItem Ayatt = Ayat.get(position);

        holder.textViewTerjemahanAyat.setText(result.getText());
        holder.textViewAyat.setText(Ayatt.getTextUthmani());
        holder.NomorSurah.setText(String.valueOf(Ayatt.getId()));
    }

    @Override
    public int getItemCount() {
        return Arti.size();
    }

    public void setData(List<TranslationsItem> Terjemahan, List<VersesItem> result) {
        if (Terjemahan.size() == result.size()) { // Periksa jumlah elemen
            Arti.clear();
            Arti.addAll(Terjemahan);

            Ayat.clear();
            Ayat.addAll(result);
            notifyDataSetChanged();
        } else {
            Log.e("AdapterTerjemahan", "Jumlah elemen dalam Terjemahan dan result tidak sesuai");
        }
    }


    public static class TerjemahanViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTerjemahanAyat, textViewAyat, NomorSurah;

        public TerjemahanViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTerjemahanAyat = itemView.findViewById(R.id.tvTerjemahanAyat);
            textViewAyat = itemView.findViewById(R.id.tvAyat);
            NomorSurah = itemView.findViewById(R.id.NomorSurah);
        }
    }
}

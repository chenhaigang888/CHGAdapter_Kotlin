package com.example.chgadapter_kotlin_demo.VH;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chg.adapter.EventTransmissionListener;
import com.chg.adapter.ViewHolder;
import com.example.chgadapter_kotlin_demo.R;
import com.example.chgadapter_kotlin_demo.model.NestedSongModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NestedSongViewHolder extends ViewHolder<NestedSongModel> {
    private ImageView icon;
    private TextView songName;
    private TextView songer;

    public NestedSongViewHolder(@NotNull View itemView, @Nullable EventTransmissionListener eventTransmissionListener, @Nullable ViewGroup parent) {
        super(itemView, eventTransmissionListener, parent);
    }


    @Override
    public void onCreated() {
        icon = findViewById(R.id.icon);
        songName = findViewById(R.id.songName);
        songer = findViewById(R.id.songer);
    }

    @Override
    public void onBindViewHolder(@Nullable NestedSongModel model) {
        super.onBindViewHolder(model);
        songer.setText(model.getSonger());
        songName.setText(model.getName());
    }
}

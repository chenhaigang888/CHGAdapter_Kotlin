package com.example.chgadapter_kotlin_demo.demo.VH;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chg.adapter.base.EventTransmissionListener;
import com.chg.adapter.recyclerView.ViewHolder;
import com.example.chgadapter_kotlin_demo.R;
import com.example.chgadapter_kotlin_demo.demo.model.NestedAlbumModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NestedAlbumViewHolder extends ViewHolder<NestedAlbumModel> {

    private ImageView icon;
    private TextView name;
    private TextView songer;

    public NestedAlbumViewHolder(@NotNull View itemView, @Nullable EventTransmissionListener eventTransmissionListener, @Nullable ViewGroup parent) {
        super(itemView, eventTransmissionListener, parent);
    }


    @Override
    public void onCreated() {
        icon = findViewById(R.id.icon);
        name = findViewById(R.id.name);
        songer = findViewById(R.id.songer);
    }

    @Override
    public void onBindViewHolder(@Nullable NestedAlbumModel model) {
        super.onBindViewHolder(model);
        name.setText(model.getName());
        songer.setText(model.getSonger());
    }
}
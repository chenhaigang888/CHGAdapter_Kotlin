package com.example.chgadapter_kotlin_demo.demo.model;


import com.example.chgadapter_kotlin_demo.R;
import com.example.chgadapter_kotlin_demo.demo.VH.NestedSongViewHolder;

import org.jetbrains.annotations.Nullable;

/**
 * 这里完全可以在SongModel里写。这里为了看起来清晰所有创建一个新的，以免影响阅读
 */
public class NestedSongModel extends SongModel {


    public NestedSongModel(int iconName, @Nullable String name, @Nullable String songer) {
        super(iconName, name, songer);
    }

    @Override
    public Class getHolderClass(int position) {
        return NestedSongViewHolder.class;
    }


    @Override
    public int getResource(int position) {
        return R.layout.song_item_nested;
    }
}

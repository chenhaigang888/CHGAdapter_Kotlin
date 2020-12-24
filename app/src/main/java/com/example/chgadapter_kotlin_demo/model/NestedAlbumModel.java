package com.example.chgadapter_kotlin_demo.model;


import com.example.chgadapter_kotlin_demo.R;
import com.example.chgadapter_kotlin_demo.VH.NestedAlbumViewHolder;

/**
 * 这里完全可以在AlbumModel里写。这里为了看起来清晰所有创建一个新的，以免影响阅读
 */
public class NestedAlbumModel extends AlbumModel {

    public NestedAlbumModel(String name, String songer) {
        super(name, songer);
    }

    @Override
    public int getResource(int position) {
        return R.layout.album_item_nested;
    }

    @Override
    public Class getHolderClass(int position) {
        return NestedAlbumViewHolder.class;
    }
}

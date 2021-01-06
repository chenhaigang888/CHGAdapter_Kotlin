package com.example.chgadapter_kotlin_demo.weibo.model;

import com.chg.adapter.Model;
import com.example.chgadapter_kotlin_demo.R;
import com.example.chgadapter_kotlin_demo.weibo.VH.HorizontalScrollViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecommendedFriendModel implements Model {

    private List<Model> friends;

    public List<Model> getFriends() {
        return friends;
    }

    public void setFriends(List<Model> friends) {
        this.friends = friends;
    }

    @Override
    public int getResource(int position) {
        return R.layout.horizontal_scroll;
    }

    @NotNull
    @Override
    public Class<?> getHolderClass(int position) {
        return HorizontalScrollViewHolder.class;
    }
}

package com.example.chgadapter_kotlin_demo.demo.model;


import com.chg.adapter.Model;
import com.example.chgadapter_kotlin_demo.R;
import com.example.chgadapter_kotlin_demo.demo.VH.PlayListItemViewHolder;

import org.jetbrains.annotations.NotNull;

public class PlayListItemModel implements Model {
    /*歌曲名称*/
    private String name;
    /*歌手*/
    private String songer;
    /*是否喜欢*/
    private boolean love = false;
    /*是否收藏*/
    private boolean collection = false;
    /*是否正在播放*/
    private boolean play = false;

    public PlayListItemModel(String name, String songer) {
        this.name = name;
        this.songer = songer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSonger() {
        return songer;
    }

    public void setSonger(String songer) {
        this.songer = songer;
    }

    public boolean isLove() {
        return love;
    }

    public void setLove(boolean love) {
        this.love = love;
    }

    public boolean isCollection() {
        return collection;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    @Override
    public int getResource(int position) {
        return R.layout.play_list_item;
    }

    @NotNull
    @Override
    public Class<?> getHolderClass(int position) {
        return PlayListItemViewHolder.class;
    }

}

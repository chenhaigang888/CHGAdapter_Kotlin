package com.example.chgadapter_kotlin_demo.weibo.model;


import com.chg.adapter.Model;
import com.chg.adapter.ViewHolder;

class Circle implements Model {
    @Override
    public int getResource(int position) {
        return 0;
    }

    @Override
    public Class<ViewHolder<Model>> getHolderClass(int position) {
        return null;
    }
}

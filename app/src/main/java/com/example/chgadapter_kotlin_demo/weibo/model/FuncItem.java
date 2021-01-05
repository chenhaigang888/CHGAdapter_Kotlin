package com.example.chgadapter_kotlin_demo.weibo.model;


import com.chg.adapter.Model;
import com.example.chgadapter_kotlin_demo.R;
import com.example.chgadapter_kotlin_demo.weibo.VH.FuncItemViewHolder;

public class FuncItem implements Model {

    private String name;
    private int icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public int getResource( int position) {
        return R.layout.func_item;
    }

    @Override
    public Class getHolderClass(int position) {
        return FuncItemViewHolder.class;
    }
}

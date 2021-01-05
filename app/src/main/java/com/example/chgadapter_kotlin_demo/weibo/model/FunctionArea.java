package com.example.chgadapter_kotlin_demo.weibo.model;


import com.chg.adapter.Model;
import com.example.chgadapter_kotlin_demo.R;
import com.example.chgadapter_kotlin_demo.weibo.VH.FunctionAreaViewHolder;

import java.util.List;

public class FunctionArea implements Model {

    private List<FuncItem> funcItems;

    public List<FuncItem> getFuncItems() {
        return funcItems;
    }

    public void setFuncItems(List<FuncItem> funcItems) {
        this.funcItems = funcItems;
    }

    @Override
    public int getResource(int position) {
        return R.layout.function_area;
    }

    @Override
    public Class getHolderClass(int position) {
        return FunctionAreaViewHolder.class;
    }
}

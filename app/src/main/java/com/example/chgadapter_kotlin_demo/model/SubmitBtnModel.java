package com.example.chgadapter_kotlin_demo.model;

import com.chg.adapter.Model;
import com.example.chgadapter_kotlin_demo.R;
import com.example.chgadapter_kotlin_demo.VH.SubmitBtnViewHolder;

public class SubmitBtnModel implements Model {
    private String btnText;

    public SubmitBtnModel(String btnText) {
        this.btnText = btnText;
    }

    public String getBtnText() {
        return btnText;
    }

    public void setBtnText(String btnText) {
        this.btnText = btnText;
    }

    @Override
    public int getResource(int position) {
        return R.layout.submit_btn_item;
    }

    @Override
    public Class getHolderClass(int position) {
        return SubmitBtnViewHolder.class;
    }
}

package com.example.chgadapter_kotlin_demo.demo.VH;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.chg.adapter.base.EventTransmissionListener;
import com.chg.adapter.recyclerView.ViewHolder;
import com.example.chgadapter_kotlin_demo.demo.Other.LoginData;
import com.example.chgadapter_kotlin_demo.R;
import com.example.chgadapter_kotlin_demo.demo.model.InputBoxModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InputBoxViewHolder extends ViewHolder<InputBoxModel> {

    private TextView title;
    private EditText editText;

    public InputBoxViewHolder(@NotNull View itemView, @Nullable EventTransmissionListener eventTransmissionListener, @Nullable ViewGroup parent) {
        super(itemView, eventTransmissionListener, parent);
    }

    @Override
    public void onCreated() {
        title = findViewById(R.id.title);
        editText = findViewById(R.id.editText);

        //监听键盘
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                LoginData loginData = (LoginData) getCustomData();
                Log.i("chgLog","输入内容："+editText.getText());
                if (getModel().getPassword()) {//如果输入是密码
                    loginData.setPassword(editText.getText().toString());
                } else {
                    loginData.setUsername(editText.getText().toString());
                }
                getModel().setInputResult(editText.getText().toString());//输入内容保存在当前模型中，滚动的时候会更新内容
                //焦点改变后将事件传递到activity中。
                eventTransmission(InputBoxViewHolder.this,editText.getText().toString(),getModel().getPassword()? 1 : 2,null);
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(@Nullable InputBoxModel model) {
        super.onBindViewHolder(model);
        title.setText(model.getTitle());
        editText.setText(model.getInputResult());//这里设置输入的内容主要是当页面滚动的时候更新输入内容
        editText.setHint(model.getHint());
        editText.setTransformationMethod(model.getPassword() ? new PasswordTransformationMethod(): HideReturnsTransformationMethod.getInstance());
    }
}

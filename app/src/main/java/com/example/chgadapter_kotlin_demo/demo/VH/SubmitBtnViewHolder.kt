package com.example.chgadapter_kotlin_demo.demo.VH

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.chg.adapter.EventTransmissionListener
import com.chg.adapter.Extension.longToast
import com.chg.adapter.ViewHolder
import com.example.chgadapter_kotlin_demo.demo.Other.LoginData
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.demo.model.SubmitBtnModel
import kotlinx.android.synthetic.main.submit_btn_item.view.*

class SubmitBtnViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<SubmitBtnModel>(itemView, eventTransmissionListener, parent) {

    private lateinit var btn:Button

    override fun onCreated() {
        btn = findViewById(R.id.btn)
        btn.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                val loginData: LoginData = getCustomData() as LoginData
                getContext().longToast("用户名：${loginData.username}  密码：${loginData.password}")
                //将点击按钮的事件传递到activity中
                eventTransmission(this@SubmitBtnViewHolder,getModel(),1,null)
            }
        })
    }

    override fun onBindViewHolder(model: SubmitBtnModel?) {
        super.onBindViewHolder(model)
        btn.setText(model?.btnText)
    }
}
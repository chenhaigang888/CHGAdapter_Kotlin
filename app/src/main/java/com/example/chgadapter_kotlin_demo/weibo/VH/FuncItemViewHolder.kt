package com.example.chgadapter_kotlin_demo.weibo.VH

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.chg.adapter.EventTransmissionListener
import com.chg.adapter.ViewHolder
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.weibo.model.FuncItem

class FuncItemViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<FuncItem>(itemView, eventTransmissionListener, parent) {

    private lateinit var icon: ImageView
    private lateinit var name: TextView

    override fun onCreated() {
        icon = findViewById<ImageView>(R.id.funcIcon)
        name = findViewById<TextView>(R.id.funcName)
    }

    override fun onBindViewHolder(model: FuncItem?) {
        super.onBindViewHolder(model)
        Log.i("chgLog","model.name:${model?.name}")
        if (model != null) {
            icon.setImageResource(model.icon)
        }
        if (model != null) {
            name.text = model.name
        }


        itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                eventTransmission(this,getModel(),0,null)
            }
        })
    }


}
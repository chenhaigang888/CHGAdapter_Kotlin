package com.example.chgadapter_kotlin_demo.demo.VH

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chg.adapter.base.EventTransmissionListener
import com.chg.adapter.recyclerView.ViewHolder
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.demo.model.MenuItemModel


class MenuItemViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<MenuItemModel>(itemView, eventTransmissionListener, parent) {


    override fun onCreated() {

    }

    override fun onBindViewHolder(model: MenuItemModel?) {
        super.onBindViewHolder(model)
        //为了简单期间就直接在这里获取view，最好的方式是在onCreated方法中findView
        findViewById<TextView>(R.id.name)?.text = model?.title
        findViewById<TextView>(R.id.desc)?.text = model?.desc
    }
}
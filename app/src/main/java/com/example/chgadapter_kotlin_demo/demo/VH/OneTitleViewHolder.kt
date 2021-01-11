package com.example.chgadapter_kotlin_demo.demo.VH

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chg.adapter.base.EventTransmissionListener
import com.chg.adapter.recyclerView.ViewHolder
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.demo.model.OneTitleModel

class OneTitleViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<OneTitleModel>(itemView, eventTransmissionListener, parent) {

    private var title:TextView? = null

    override fun onCreated() {
        title = findViewById<TextView>(R.id.title)
    }

    override fun onBindViewHolder(model: OneTitleModel?) {
        super.onBindViewHolder(model)
        title?.setText(model?.title)
    }
}
package com.example.chgadapter_kotlin_demo.search.VH

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chg.adapter.EventTransmissionListener
import com.chg.adapter.ViewHolder
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.model.AdviceModel

class AdviceViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<AdviceModel>(itemView, eventTransmissionListener, parent) {

    lateinit var title:TextView


    override fun onCreated() {
        title = findViewById(R.id.title)
    }

    override fun onBindViewHolder(model: AdviceModel?) {
        super.onBindViewHolder(model)
        title.setText(model?.title)
    }
}
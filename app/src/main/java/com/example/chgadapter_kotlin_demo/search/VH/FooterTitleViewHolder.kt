package com.example.chgadapter_kotlin_demo.search.VH

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chg.adapter.EventTransmissionListener
import com.chg.adapter.ViewHolder
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.model.FooterTitleModel
import com.example.chgadapter_kotlin_demo.search.model.HeaderTitleModel

class FooterTitleViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<FooterTitleModel>(itemView, eventTransmissionListener, parent) {
    private lateinit var title: TextView

    override fun onCreated() {
        title = findViewById(R.id.title)
    }

    override fun onBindViewHolder(model: FooterTitleModel?) {
        super.onBindViewHolder(model)
        title.setText(model?.title)
    }
}
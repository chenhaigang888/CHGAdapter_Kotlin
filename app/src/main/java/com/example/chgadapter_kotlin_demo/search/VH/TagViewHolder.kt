package com.example.chgadapter_kotlin_demo.search.VH

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chg.adapter.EventTransmissionListener
import com.chg.adapter.ViewHolder
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.model.TagModel

class TagViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<TagModel>(itemView, eventTransmissionListener, parent) {

    private lateinit var tag:TextView

    override fun onCreated() {
        tag = findViewById(R.id.tag)
    }

    override fun onBindViewHolder(model: TagModel?) {
        super.onBindViewHolder(model)
        tag.setText(model?.name)
    }
}
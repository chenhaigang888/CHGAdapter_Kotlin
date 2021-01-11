package com.example.chgadapter_kotlin_demo.search.VH

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chg.adapter.base.EventTransmissionListener
import com.chg.adapter.recyclerView.ViewHolder
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.model.PostModel

class PostViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<PostModel>(itemView, eventTransmissionListener, parent) {

    private lateinit var content:TextView

    override fun onCreated() {
        content = findViewById(R.id.content)
    }

    override fun onBindViewHolder(model: PostModel?) {
        super.onBindViewHolder(model)
        content.setText(model?.text)
    }
}
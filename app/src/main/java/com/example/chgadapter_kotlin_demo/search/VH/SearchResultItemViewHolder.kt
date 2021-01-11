package com.example.chgadapter_kotlin_demo.search.VH

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chg.adapter.base.EventTransmissionListener
import com.chg.adapter.recyclerView.ViewHolder
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.model.SearchResultItemModel

class SearchResultItemViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<SearchResultItemModel>(itemView, eventTransmissionListener, parent) {

    private lateinit var title:TextView

    override fun onCreated() {
        title = findViewById(R.id.title)
    }

    override fun onBindViewHolder(model: SearchResultItemModel?) {
        super.onBindViewHolder(model)
        title.setText(model?.title)
    }
}
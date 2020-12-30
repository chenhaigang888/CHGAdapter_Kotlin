package com.example.chgadapter_kotlin_demo.search.VH

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chg.adapter.EventTransmissionListener
import com.chg.adapter.ViewHolder
import com.chg.adapter.models
import com.chg.adapter.notifyDataSetChanged
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.model.SearchResultModel


class SearchResultViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<SearchResultModel>(itemView, eventTransmissionListener, parent) {

    private lateinit var mRecyclerView:RecyclerView

    override fun onCreated() {
        mRecyclerView = findViewById(R.id.recyclerView)
    }

    override fun onBindViewHolder(model: SearchResultModel?) {
        super.onBindViewHolder(model)
        mRecyclerView.models = model?.resultModels
        mRecyclerView.notifyDataSetChanged()
    }
}
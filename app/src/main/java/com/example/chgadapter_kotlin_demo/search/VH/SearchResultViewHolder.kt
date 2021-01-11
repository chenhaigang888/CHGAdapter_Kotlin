package com.example.chgadapter_kotlin_demo.search.VH

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chg.adapter.*
import com.chg.adapter.base.EventTransmissionListener
import com.chg.adapter.base.Model
import com.chg.adapter.extension.models
import com.chg.adapter.extension.notifyDataSetChanged
import com.chg.adapter.extension.setOnItemClickListener
import com.chg.adapter.recyclerView.Adapter
import com.chg.adapter.recyclerView.ViewHolder
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.model.SearchResultModel


class SearchResultViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<SearchResultModel>(itemView, eventTransmissionListener, parent), Adapter.OnItemClickListener {

    private lateinit var mRecyclerView:RecyclerView

    override fun onCreated() {
        mRecyclerView = findViewById(R.id.recyclerView)
        mRecyclerView.setOnItemClickListener(this)

    }

    override fun onBindViewHolder(model: SearchResultModel?) {
        super.onBindViewHolder(model)
        mRecyclerView.models = model?.resultModels
        mRecyclerView.notifyDataSetChanged()
    }

    override fun onItemClick(parent: RecyclerView?, view: View?, position: Int?, model: Model?) {
        eventTransmission(this@SearchResultViewHolder,model,1,null)
    }
}
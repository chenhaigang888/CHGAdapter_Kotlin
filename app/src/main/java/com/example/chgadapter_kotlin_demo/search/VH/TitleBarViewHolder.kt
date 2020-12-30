package com.example.chgadapter_kotlin_demo.search.VH

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.chg.adapter.EventTransmissionListener
import com.chg.adapter.ViewHolder
import com.chg.adapter.models
import com.chg.adapter.notifyDataSetChanged
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.model.TitleBarModel
import kotlinx.android.synthetic.main.activity_search.view.*

class TitleBarViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<TitleBarModel>(itemView, eventTransmissionListener, parent) {
    private lateinit var mRecyclerView:RecyclerView
    private lateinit var mResultsRecyclerView:RecyclerView

    override fun onCreated() {
        mRecyclerView = findViewById(R.id.recyclerView)
        mResultsRecyclerView = findViewById(R.id.resultsRecyclerView)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(mResultsRecyclerView)
    }

    override fun onBindViewHolder(model: TitleBarModel?) {
        super.onBindViewHolder(model)
        mRecyclerView.models = model?.barItems
        mRecyclerView.notifyDataSetChanged()

        mResultsRecyclerView.models = model?.searchResults
        mResultsRecyclerView.notifyDataSetChanged()
    }
}
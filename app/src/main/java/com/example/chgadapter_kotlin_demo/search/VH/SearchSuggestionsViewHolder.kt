package com.example.chgadapter_kotlin_demo.search.VH

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chg.adapter.base.EventTransmissionListener
import com.chg.adapter.base.Model
import com.chg.adapter.extension.models
import com.chg.adapter.extension.notifyDataSetChanged
import com.chg.adapter.extension.setOnItemClickListener
import com.chg.adapter.recyclerView.Adapter
import com.chg.adapter.recyclerView.ViewHolder
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.model.SearchSuggestionsModel


class SearchSuggestionsViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<SearchSuggestionsModel>(itemView, eventTransmissionListener, parent) {

    private lateinit var title: TextView
    private lateinit var recyclerView: RecyclerView

    override fun onCreated() {
        title = findViewById(R.id.title)
        recyclerView = findViewById(R.id.recyclerView)
        val layoutManager = GridLayoutManager(getContext(), 5) //第二个参数为网格的列数

        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setOnItemClickListener(object : Adapter.OnItemClickListener {
            override fun onItemClick(
                parent: RecyclerView?,
                view: View?,
                position: Int?,
                model: Model?
            ) {
                eventTransmission( model, 1, null)
            }
        })
    }

    override fun onBindViewHolder(model: SearchSuggestionsModel?) {
        super.onBindViewHolder(model)
        title.setText(model?.title)
        recyclerView.models = model?.suggestionsData
        recyclerView.notifyDataSetChanged()
    }
}
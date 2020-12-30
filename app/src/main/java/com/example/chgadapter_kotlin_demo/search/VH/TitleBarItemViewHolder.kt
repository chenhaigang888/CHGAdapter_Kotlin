package com.example.chgadapter_kotlin_demo.search.VH

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chg.adapter.EventTransmissionListener
import com.chg.adapter.ViewHolder
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.model.TitleBarItemModel
import kotlinx.android.synthetic.main.search_suggestions_item.view.*

class TitleBarItemViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<TitleBarItemModel>(itemView, eventTransmissionListener, parent) {

    private lateinit var mTitle:TextView

    override fun onCreated() {
        mTitle = findViewById(R.id.title)
    }

    override fun onBindViewHolder(model: TitleBarItemModel?) {
        super.onBindViewHolder(model)
        mTitle.setText(model?.title)
    }
}
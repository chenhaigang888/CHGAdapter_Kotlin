package com.example.chgadapter_kotlin_demo.VH

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chg.adapter.EventTransmissionListener
import com.chg.adapter.ViewHolder
import com.chg.adapter.models
import com.chg.adapter.notifyDataSetChanged
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.model.MusicModel
import kotlinx.android.synthetic.main.music_item.view.*

class MusicViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<MusicModel>(itemView, eventTransmissionListener, parent) {

    private var recyclerView:RecyclerView? = null
    private var title:TextView? = null

    override fun onCreated() {
        recyclerView = findViewById(R.id.recyclerView)
        title = findViewById(R.id.title)
    }

    override fun onBindViewHolder(model: MusicModel?) {
        super.onBindViewHolder(model)
        recyclerView?.models = model?.data
        title?.text = model?.title
        recyclerView?.notifyDataSetChanged()
    }
}
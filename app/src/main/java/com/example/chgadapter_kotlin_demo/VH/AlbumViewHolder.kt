package com.example.chgadapter_kotlin_demo.VH

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chg.adapter.EventTransmissionListener
import com.chg.adapter.ViewHolder
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.model.AlbumModel
import kotlinx.android.synthetic.main.album_item.view.*

class AlbumViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<AlbumModel>(itemView, eventTransmissionListener, parent) {

    var text:TextView? = null
    var songer:TextView? = null
    override fun onCreated() {
        text = findViewById(R.id.name)
        songer = findViewById(R.id.songer)
    }

    override fun onBindViewHolder(model: AlbumModel?) {
        super.onBindViewHolder(model)
        text?.text = model?.name
        songer?.text = model?.songer
    }
}
package com.example.chgadapter_kotlin_demo.VH

import android.view.View
import android.view.ViewGroup
import com.chg.adapter.EventTransmissionListener
import com.chg.adapter.Model
import com.chg.adapter.ViewHolder

class PersonViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder(itemView, eventTransmissionListener, parent) {

    override fun onBindViewHolder(model: Model?) {
        super.onBindViewHolder(model)
        
    }

}
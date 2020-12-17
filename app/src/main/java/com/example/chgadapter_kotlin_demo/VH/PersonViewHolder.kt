package com.example.chgadapter_kotlin_demo.VH

import android.view.View
import android.view.ViewGroup
import com.chg.adapter.EventTransmissionListener
import com.chg.adapter.Model
import com.chg.adapter.ViewHolder
import com.example.chgadapter_kotlin_demo.model.Person

class PersonViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<Person>(itemView, eventTransmissionListener, parent) {



}
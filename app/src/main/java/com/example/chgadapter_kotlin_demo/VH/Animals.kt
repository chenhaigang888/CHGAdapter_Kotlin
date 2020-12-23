package com.example.chgadapter_kotlin_demo.VH

import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.chg.adapter.EventTransmissionListener
import com.chg.adapter.Model
import com.chg.adapter.ViewHolder
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.model.Animals

class Animals(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<Animals>(itemView, eventTransmissionListener, parent) {

    override fun onBindViewHolder(model: Animals?) {
        super.onBindViewHolder(model)
        findViewById<TextView>(R.id.head)?.setText(model?.name)

        findViewById<Button>(R.id.btn)?.setOnClickListener(object : OnClickListener{
            override fun onClick(v: View?) {
                notifyCurrentItemChanged()
            }
        })
    }
}
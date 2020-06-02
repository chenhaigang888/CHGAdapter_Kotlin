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

class AnimalsViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder(itemView, eventTransmissionListener, parent) {

    override fun onBindViewHolder(model: Model?) {
        super.onBindViewHolder(model)

        var animals:Animals = model as Animals

        findViewById<TextView>(R.id.head)?.setText(animals.name)

        findViewById<Button>(R.id.btn)?.setOnClickListener(object : OnClickListener{
            override fun onClick(v: View?) {
                eventTransmission(this, model, 0, null)
            }
        })

    }

}
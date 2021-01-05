package com.example.chgadapter_kotlin_demo.weibo.VH

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chg.adapter.EventTransmissionListener
import com.chg.adapter.ViewHolder
import com.chg.adapter.extension.eventTransmissionListener
import com.chg.adapter.extension.models
import com.chg.adapter.extension.notifyDataSetChanged
import com.chg.adapter.extension.notifyItemChanged
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.weibo.model.FuncItem
import com.example.chgadapter_kotlin_demo.weibo.model.FunctionArea

class FunctionAreaViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<FunctionArea>(itemView, eventTransmissionListener, parent) {
    private lateinit var recycleView: RecyclerView

    override fun onCreated() {
        recycleView = findViewById<RecyclerView>(R.id.funcRecycleView)
        val manager = LinearLayoutManager(getContext())
        manager.orientation = LinearLayoutManager.HORIZONTAL
        recycleView.layoutManager = manager
    }

    override fun onBindViewHolder(model: FunctionArea?) {
        super.onBindViewHolder(model)
        recycleView.models = model!!.funcItems
        recycleView.eventTransmissionListener = getEventTransmissionListener()
        recycleView.notifyDataSetChanged()
    }
}
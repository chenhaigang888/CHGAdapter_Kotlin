package com.example.chgadapter_kotlin_demo.search.VH

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chg.adapter.EventTransmissionListener
import com.chg.adapter.ViewHolder
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.model.UserModel
import kotlinx.android.synthetic.main.search_result_user.view.*

class UserViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<UserModel>(itemView, eventTransmissionListener, parent) {

    private lateinit var name:TextView
    override fun onCreated() {
        name = findViewById(R.id.name)
    }

    override fun onBindViewHolder(model: UserModel?) {
        super.onBindViewHolder(model)
        name.setText(model?.name)
    }
}
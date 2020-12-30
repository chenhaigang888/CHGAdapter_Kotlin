package com.example.chgadapter_kotlin_demo.demo.VH

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.chg.adapter.EventTransmissionListener
import com.chg.adapter.ViewHolder
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.demo.model.SongModel

class SongViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<SongModel>(itemView, eventTransmissionListener, parent) {

    private var mImageView:ImageView? = null
    private var mNameTextView:TextView? = null
    private var mSongerTextView:TextView? = null

    override fun onCreated() {
        mImageView = findViewById(R.id.imageView)
        mNameTextView = findViewById(R.id.name)
        mSongerTextView = findViewById(R.id.songer)
    }

    override fun onBindViewHolder(model: SongModel?) {
        super.onBindViewHolder(model)
        model?.iconName?.let { mImageView?.setImageResource(it) }
        mNameTextView?.text = model?.name
        mSongerTextView?.text = model?.songer
    }


}
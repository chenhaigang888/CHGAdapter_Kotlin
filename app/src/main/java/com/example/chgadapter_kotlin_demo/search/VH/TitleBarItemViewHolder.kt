package com.example.chgadapter_kotlin_demo.search.VH

import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.chg.adapter.base.EventTransmissionListener
import com.chg.adapter.recyclerView.ViewHolder
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.model.TitleBarItemModel
import com.example.chgadapter_kotlin_demo.search.model.TitleBarModel

class TitleBarItemViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<TitleBarItemModel>(itemView, eventTransmissionListener, parent) {

    private lateinit var mTitle:TextView

    override fun onCreated() {
        mTitle = findViewById(R.id.title)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(model: TitleBarItemModel?) {
        super.onBindViewHolder(model)
        mTitle.setText(model?.title)
        if (getCustomData() is TitleBarModel) {
            var customData: TitleBarModel = getCustomData() as TitleBarModel

            if (customData.currentPosition == adapterPosition) {
                mTitle.setTextColor(getContext().getColor(R.color.tab_selected))
            } else {
                mTitle.setTextColor(getContext().getColor(R.color.tab_normal))
            }
        }

    }
}
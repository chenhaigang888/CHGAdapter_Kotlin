package com.example.chgadapter_kotlin_demo.search.model

import com.chg.adapter.Model
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.VH.HeaderTitleViewHolder

data class HeaderTitleModel(var title:String):Model {

    override fun getResource(position: Int): Int {
        return R.layout.header_title_item
    }

    override fun getHolderClass(position: Int): Class<*> {
        return HeaderTitleViewHolder::class.java
    }
}
package com.example.chgadapter_kotlin_demo.search.model

import com.chg.adapter.base.Model
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.VH.FooterTitleViewHolder

data class FooterTitleModel(var title:String) : Model {

    override fun getResource(position: Int): Int {
        return R.layout.footer_title_item
    }

    override fun getHolderClass(position: Int): Class<*> {
        return FooterTitleViewHolder::class.java
    }
}
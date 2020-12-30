package com.example.chgadapter_kotlin_demo.search.model

import com.chg.adapter.Model
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.VH.TitleBarItemViewHolder

class TitleBarItemModel : Model {
     var title:String

    constructor(title: String) {
        this.title = title
    }


    override fun getResource(position: Int): Int {
        return R.layout.title_bar_item
    }

    override fun getHolderClass(position: Int): Class<*> {
        return TitleBarItemViewHolder::class.java
    }
}
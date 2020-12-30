package com.example.chgadapter_kotlin_demo.demo.model

import com.chg.adapter.Model
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.demo.VH.OneTitleViewHolder

class OneTitleModel: Model {
    var title: String? = null

    constructor(title: String?) {
        this.title = title
    }


    override fun getResource(position: Int): Int {
        return R.layout.one_title_item
    }

    override fun getHolderClass(position: Int): Class<*> {
        return OneTitleViewHolder::class.java
    }

}
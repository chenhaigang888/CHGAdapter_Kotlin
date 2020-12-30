package com.example.chgadapter_kotlin_demo.search.model

import com.chg.adapter.Model
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.VH.TagViewHolder

class TagModel : Model {

    var name:String

    constructor(name: String) {
        this.name = name
    }


    override fun getResource(position: Int): Int {
        return R.layout.tag_item
    }

    override fun getHolderClass(position: Int): Class<*> {
        return TagViewHolder::class.java
    }
}
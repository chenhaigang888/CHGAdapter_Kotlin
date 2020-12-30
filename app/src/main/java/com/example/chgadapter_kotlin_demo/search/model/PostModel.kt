package com.example.chgadapter_kotlin_demo.search.model

import com.chg.adapter.Model
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.VH.PostViewHolder

class PostModel : Model {

    var text:String

    constructor(text: String) {
        this.text = text
    }


    override fun getResource(position: Int): Int {
        return R.layout.found_send_data_item
    }

    override fun getHolderClass(position: Int): Class<*> {
        return PostViewHolder::class.java
    }
}
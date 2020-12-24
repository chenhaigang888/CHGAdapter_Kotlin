package com.example.chgadapter_kotlin_demo.model

import com.chg.adapter.Model
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.VH.MusicViewHolder

class MusicModel : Model {

    var title: String? = null
    var data: List<Model>? = null

    constructor(title: String?, data: List<Model>?) {
        this.title = title
        this.data = data
    }


    override fun getResource(position: Int): Int {
        return R.layout.music_item
    }

    override fun getHolderClass(position: Int): Class<*> {
        return MusicViewHolder::class.java
    }
}
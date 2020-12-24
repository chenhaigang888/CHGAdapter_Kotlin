package com.example.chgadapter_kotlin_demo.model

import com.chg.adapter.Model
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.VH.AlbumViewHolder

open class AlbumModel : Model {
     var name: String? = null
     var songer: String? = null

    constructor(name: String?, songer: String?) {
        this.name = name
        this.songer = songer
    }


    override fun getResource(position: Int): Int {
        return R.layout.album_item
    }

    override fun getHolderClass(position: Int): Class<*> {
        return AlbumViewHolder::class.java
    }
}
package com.example.chgadapter_kotlin_demo.demo.model

import com.chg.adapter.base.Model
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.demo.VH.SongViewHolder

open class SongModel : Model {

    var iconName:Int = 0
    var name:String? = null
    var songer:String? = null

    constructor(iconName: Int, name: String?, songer: String?) {
        this.iconName = iconName
        this.name = name
        this.songer = songer
    }


    override fun getResource(position: Int): Int {
        return R.layout.song_item
    }

    override fun getHolderClass(position: Int): Class<*> {
        return SongViewHolder::class.java
    }
}
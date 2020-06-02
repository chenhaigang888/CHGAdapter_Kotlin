package com.example.chgadapter_kotlin_demo.model

import com.chg.adapter.Model
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.VH.AnimalsViewHolder

/**
 * 动物
 */
open class Animals() : Model {

    var name: String? = null

    constructor(name: String?) : this() {
        this.name = name
    }

    override fun getResource(position: Int): Int {
        return R.layout.animals_item_layout
    }

    override fun getHolderClass(position: Int): Class<*> {
        return AnimalsViewHolder::class.java
    }
}
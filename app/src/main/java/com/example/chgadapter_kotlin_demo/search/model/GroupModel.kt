package com.example.chgadapter_kotlin_demo.search.model

import com.chg.adapter.base.Model
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.VH.GroupViewHolder

class GroupModel : Model {

    var name:String

    constructor(name: String) {
        this.name = name
    }


    override fun getResource(position: Int): Int {
        return R.layout.search_result_group
    }

    override fun getHolderClass(position: Int): Class<*> {
        return GroupViewHolder::class.java
    }
}
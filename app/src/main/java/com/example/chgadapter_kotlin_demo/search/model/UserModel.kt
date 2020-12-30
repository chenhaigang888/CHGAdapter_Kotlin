package com.example.chgadapter_kotlin_demo.search.model

import com.chg.adapter.Model
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.VH.UserViewHolder

class UserModel :Model {
    lateinit var name:String

    constructor(name: String) {
        this.name = name
    }


    override fun getResource(position: Int): Int {
        return R.layout.search_result_user
    }

    override fun getHolderClass(position: Int): Class<*> {
        return UserViewHolder::class.java
    }
}
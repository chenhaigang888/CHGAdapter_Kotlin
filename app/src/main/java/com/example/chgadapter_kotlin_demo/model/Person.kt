package com.example.chgadapter_kotlin_demo.model

import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.VH.PersonViewHolder

class Person() : Animals() {

    constructor(name: String?) : this() {
        this.name = name
    }

    override fun getResource(position: Int): Int {
        return R.layout.person_item_layout
    }

    override fun getHolderClass(position: Int): Class<*> {
        return PersonViewHolder::class.java
    }
}
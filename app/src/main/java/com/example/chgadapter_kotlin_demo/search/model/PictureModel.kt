package com.example.chgadapter_kotlin_demo.search.model

import com.chg.adapter.Model
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.VH.PictureViewModel

class PictureModel : Model {

     var text:String

    constructor(text: String) {
        this.text = text
    }


    override fun getResource(position: Int): Int {
        return R.layout.search_result_picture
    }

    override fun getHolderClass(position: Int): Class<*> {
        return PictureViewModel::class.java
    }
}
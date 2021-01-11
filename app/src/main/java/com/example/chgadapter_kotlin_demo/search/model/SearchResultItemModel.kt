package com.example.chgadapter_kotlin_demo.search.model

import com.chg.adapter.base.Model
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.VH.SearchResultItemViewHolder

class SearchResultItemModel : Model {

    var type:Int = 0
    var title:String = ""

    constructor(type: Int, title: String) {
        this.type = type
        this.title = title
    }


    override fun getResource(position: Int): Int {
//        if (type == 0) {
//
//        } else if (type == 1) {
//            return R.layout.search_result_user
//        } else if (type == 2) {
//            return R.layout.search_result_user
//        } else if (type == 3) {
//            return R.layout.search_result_user
//        }

        return R.layout.search_result_xiaoyou
    }

    override fun getHolderClass(position: Int): Class<*> {
        return SearchResultItemViewHolder::class.java
    }
}
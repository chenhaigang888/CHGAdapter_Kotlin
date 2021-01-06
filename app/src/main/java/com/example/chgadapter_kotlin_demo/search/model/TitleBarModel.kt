package com.example.chgadapter_kotlin_demo.search.model

import com.chg.adapter.Model
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.VH.TitleBarViewHolder

class TitleBarModel:Model {

    var currentPosition:Int = 0
    var barItems:List<TitleBarItemModel>
    var searchResults:List<SearchResultModel>

    constructor(barItems: List<TitleBarItemModel>,searchResults:List<SearchResultModel>) {
        this.barItems = barItems
        this.searchResults = searchResults
    }


    override fun getResource(position: Int): Int {
        return R.layout.title_bar
    }

    override fun getHolderClass(position: Int): Class<*> {
        return TitleBarViewHolder::class.java
    }
}
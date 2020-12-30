package com.example.chgadapter_kotlin_demo.search.model

import com.chg.adapter.Model
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.VH.SearchResultViewHolder

/**
 * 搜索结果页面
 */
class SearchResultModel : Model {

    var resultModels:List<Model>

    constructor(resultModels: List<Model>) {
        this.resultModels = resultModels
    }


    override fun getResource(position: Int): Int {
        return R.layout.search_result
    }

    override fun getHolderClass(position: Int): Class<*> {
        return SearchResultViewHolder::class.java
    }
}
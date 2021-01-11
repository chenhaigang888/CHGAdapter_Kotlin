package com.example.chgadapter_kotlin_demo.search.model

import com.chg.adapter.base.Model
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.VH.SearchSuggestionsViewHolder

/**
 * 搜索建议模型
 */
class SearchSuggestionsModel : Model {

    var title:String

    var suggestionsData:List<TagModel>

    constructor(title: String, suggestionsData: List<TagModel>) {
        this.title = title
        this.suggestionsData = suggestionsData
    }


    override fun getResource(position: Int): Int {
        return R.layout.search_suggestions_item
    }

    override fun getHolderClass(position: Int): Class<*> {
        return SearchSuggestionsViewHolder::class.java
    }
}
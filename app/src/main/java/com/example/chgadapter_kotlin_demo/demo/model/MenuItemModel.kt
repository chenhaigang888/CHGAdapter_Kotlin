package com.example.chgadapter_kotlin_demo.demo.model

import com.chg.adapter.Model
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.demo.VH.MenuItemViewHolder

class MenuItemModel : Model {

    /*演示功能标题*/
    var title: String? = null
    /*功能介绍*/
    var desc: String? = null

    var activityClass: Class<*>? = null

    constructor(title: String?, desc: String?, activityClass: Class<*>?) {
        this.title = title
        this.desc = desc
        this.activityClass = activityClass
    }


    override fun getResource(position: Int): Int {
        return R.layout.menu_item
    }

    override fun getHolderClass(position: Int): Class<*> {
        return MenuItemViewHolder::class.java
    }
}
package com.example.chgadapter_kotlin_demo.model

import com.chg.adapter.Model
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.VH.InputBoxViewHolder

class InputBoxModel : Model {

    /*标签*/
     var title: String? = null

    /*存储输入结果*/
     var inputResult: String? = null

    /*输入提示语*/
     var hint: String? = null

    /*输入内容是否为密码*/
     var password = false

    constructor(title: String?, inputResult: String?, hint: String?, password: Boolean) {
        this.title = title
        this.inputResult = inputResult
        this.hint = hint
        this.password = password
    }


    override fun getResource(position: Int): Int {
        return R.layout.input_view
    }

    override fun getHolderClass(position: Int): Class<*> {
        return InputBoxViewHolder::class.java
    }
}
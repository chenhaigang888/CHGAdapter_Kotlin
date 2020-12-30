package com.chg.adapter

import androidx.recyclerview.widget.RecyclerView

/**
 * ViewHolder生命周期
 */
 interface ViewHolderLifeCycle<M:Model> {

    /**
     * 绑定数据
     *
     * @param model 具体的数据
     */
     fun onBindViewHolder(model: M?)

    fun onBindViewHolder(payloads: MutableList<Any>)
    /**
     * 将要显示
     */
     fun onViewAttachedToWindow()

    /**
     * 不在显示
     */
     fun onViewDetachedFromWindow()


    /**
     * 即将销毁
     */
     fun onViewRecycled()
}
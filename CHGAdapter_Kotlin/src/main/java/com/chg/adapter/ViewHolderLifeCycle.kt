package com.chg.adapter

/**
 * ViewHolder生命周期
 */
open interface ViewHolderLifeCycle<M:Model> {

    /**
     * 绑定数据
     *
     * @param model 具体的数据
     */
    open fun onBindViewHolder(model: M?)

    /**
     * 将要显示
     */
    open fun onViewAttachedToWindow()

    /**
     * 不在显示
     */
    open fun onViewDetachedFromWindow()


    /**
     * 即将销毁
     */
    open fun onViewRecycled()
}
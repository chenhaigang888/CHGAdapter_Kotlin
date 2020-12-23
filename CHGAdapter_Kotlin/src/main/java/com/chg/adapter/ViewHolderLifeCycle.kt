package com.chg.adapter

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
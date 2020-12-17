package com.chg.adapter

/**
 * 所有model需要实现这个接口
 */
open interface Model {

    /**
     * 返回当前model对应的布局文件 例如：R.layout.xxx
     */
    open fun getResource(position: Int): Int

    /**
     * 当model所对应的Holder类
     */
    open fun getHolderClass(position: Int): Class<*>
}
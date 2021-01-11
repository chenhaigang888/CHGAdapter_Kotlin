package com.chg.adapter.base

/**
 * 所有model需要实现这个接口
 */
 interface Model {

    /**
     * 返回当前model对应的布局文件 例如：R.layout.xxx
     */
     fun getResource(position: Int): Int

    /**
     * 当model所对应的Holder类
     */
     fun getHolderClass(position: Int): Class<*>
}
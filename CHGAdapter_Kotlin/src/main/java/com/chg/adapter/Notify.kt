package com.chg.adapter

/**
 * 抽离ViewHolder刷新
 */
interface Notify {

    fun notifyDataSetChanged()

    fun notifyItemChanged(position: Int)

    fun notifyItemChanged(position: Int, payload: Any?)

    fun notifyItemRangeChanged(positionStart: Int, itemCount: Int)

    fun notifyItemRangeChanged(
        positionStart: Int, itemCount: Int,
        payload: Any?
    )

    fun notifyItemInserted(position: Int)

    fun notifyItemMoved(fromPosition: Int, toPosition: Int)

    fun notifyItemRangeInserted(positionStart: Int, itemCount: Int)

    fun notifyItemRemoved(position: Int)

    fun notifyItemRangeRemoved(positionStart: Int, itemCount: Int)
}
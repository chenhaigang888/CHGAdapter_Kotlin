package com.chg.adapter

import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

/**
 * 封装的ViewHolder类
 */
abstract class ViewHolder<M : Model> constructor(itemView: View, eventTransmissionListener: EventTransmissionListener?, parent: ViewGroup?) : RecyclerView.ViewHolder(itemView),ViewHolderLifeCycle<M> ,Notify{

    private var eventTransmissionListener: EventTransmissionListener? = null
    private var parent: ViewGroup? = null
    private var model: M? = null

    /**
     * 快捷的方法
     */
    open fun eventTransmission(
        target: Any?,
        params: Any?,
        tag: Int,
        callBack: EventTransmissionListener.CallBack?
    ):Any?{
        return eventTransmissionListener?.onEventTransmissionListener(target, params, tag, callBack)
    }

    abstract fun onCreated();

    open fun <T : View?> findViewById(@IdRes id: Int): T? {
        return itemView.findViewById<T>(id)
    }

    open fun getParent(): ViewGroup {
        return parent!!
    }

    open fun setParent(parent: ViewGroup?) {
        this.parent = parent
    }

    /**
     * 获取用户自定义的对象
     *
     * @return
     */
    open fun getCustomData(): Any? {
        return getAdapter()?.getCustomData()
    }

    open fun getAdapter(): Adapter<M>? {
        return (getParent() as RecyclerView).adapter as Adapter<M>?
    }

    open fun getModel(): M? {
        return model
    }

    open fun setModel(model: M?) {
        this.model = model
    }

    open fun getEventTransmissionListener(): EventTransmissionListener? {
        return eventTransmissionListener
    }

    open fun setEventTransmissionListener(eventTransmissionListener: EventTransmissionListener?) {
        this.eventTransmissionListener = eventTransmissionListener
    }

    /**
     * 刷新当前Item
     */
    open fun notifyCurrentItemChanged(){
        notifyItemChanged(adapterPosition)
    }

    override fun notifyDataSetChanged() {
        getAdapter()?.notifyDataSetChanged()
    }


    override fun notifyItemChanged(position: Int) {
        getAdapter()?.notifyItemRangeChanged(position, 1)
    }


    override fun notifyItemChanged(position: Int, payload: Any?) {
        getAdapter()?.notifyItemRangeChanged(position, 1, payload)
    }


    override fun notifyItemRangeChanged(positionStart: Int, itemCount: Int) {
        getAdapter()?.notifyItemRangeChanged(positionStart, itemCount)
    }


    override fun notifyItemRangeChanged(
        positionStart: Int, itemCount: Int,
        payload: Any?
    ) {
        getAdapter()?.notifyItemRangeChanged(positionStart, itemCount, payload)
    }


    override fun notifyItemInserted(position: Int) {
        getAdapter()?.notifyItemRangeInserted(position, 1)
    }


    override fun notifyItemMoved(fromPosition: Int, toPosition: Int) {
        getAdapter()?.notifyItemMoved(fromPosition, toPosition)
    }


    override fun notifyItemRangeInserted(positionStart: Int, itemCount: Int) {
        getAdapter()?.notifyItemRangeInserted(positionStart, itemCount)
    }


    override fun notifyItemRemoved(position: Int) {
        getAdapter()?.notifyItemRangeRemoved(position, 1)
    }

    override fun notifyItemRangeRemoved(positionStart: Int, itemCount: Int) {
        getAdapter()?.notifyItemRangeRemoved(positionStart, itemCount)
    }

    override fun onBindViewHolder(model: M?) {
        this.model = model
    }

    override fun onViewAttachedToWindow() {

    }

    override fun onViewDetachedFromWindow() {

    }

    override fun onViewRecycled() {

    }
}
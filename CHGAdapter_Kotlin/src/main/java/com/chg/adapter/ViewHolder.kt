package com.chg.adapter

import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

/**
 * 封装的ViewHolder类
 */
open class ViewHolder<M:Model> constructor(itemView: View, eventTransmissionListener: EventTransmissionListener?, parent: ViewGroup?) : RecyclerView.ViewHolder(itemView),ViewHolderLifeCycle<M> {

    private var eventTransmissionListener: EventTransmissionListener? = null
    private var parent: ViewGroup? = null
    private var model: M? = null


    init {
        this.eventTransmissionListener = eventTransmissionListener
        this.parent = parent
    }

    open fun eventTransmission(target: Any?,
                               params: Any?,
                               tag: Int,
                               callBack: EventTransmissionListener.CallBack?):Any?{
        return eventTransmissionListener?.onEventTransmissionListener(target,params,tag,callBack)
    }

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
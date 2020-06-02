package com.chg.adapter

import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

/**
 * 封装的ViewHolder类
 */
open class ViewHolder constructor(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : RecyclerView.ViewHolder(itemView) {

    private var eventTransmissionListener: EventTransmissionListener? = null
    private var parent: ViewGroup? = null
    private var model: Model? = null


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

    /**
     * 绑定数据
     *
     * @param model 具体的数据
     */
    open fun onBindViewHolder(model: Model?) {
        this.model = model
    }

    /**
     * 将要显示
     */
    open fun onViewAttachedToWindow() {}

    /**
     * 不在显示
     */
    open fun onViewDetachedFromWindow() {}


    /**
     * 即将销毁
     */
    open fun onViewRecycled() {}

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

    open fun getAdapter(): Adapter<Model>? {
        return (getParent() as RecyclerView).adapter as Adapter<Model>?
    }

    open fun getModel(): Model? {
        return model
    }

    open fun setModel(model: Model?) {
        this.model = model
    }

    open fun getEventTransmissionListener(): EventTransmissionListener? {
        return eventTransmissionListener
    }

    open fun setEventTransmissionListener(eventTransmissionListener: EventTransmissionListener?) {
        this.eventTransmissionListener = eventTransmissionListener
    }
}
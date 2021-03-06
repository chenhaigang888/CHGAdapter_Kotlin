package com.chg.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import java.lang.reflect.Constructor

open class Adapter<M : Model>() : Adapter<RecyclerView.ViewHolder>(), View.OnClickListener,View.OnLongClickListener {

    private var models: List<M>? = null
    private var context: Context? = null
    private var eventTransmissionListener: EventTransmissionListener? = null
    private var customData: Any? = null
    private var viewHolderClass: Class<ViewHolder<M>>? = null
    private var slideMomentumListener: SlideMomentumListener? = null

    private var onItemClickListener : OnItemClickListener? = null
    private var onItemLongClickListener :OnItemLongClickListener? = null

    private var recyclerView : RecyclerView? = null

    constructor(context: Context, models: List<M>?) : this() {
        this.context = context
        this.models = models
    }

    constructor(context: Context) : this() {
        this.context = context
    }

     interface OnItemClickListener {
        fun onItemClick(parent: RecyclerView?, view: View?, position: Int?, model: Model?)
    }

     interface OnItemLongClickListener {
        fun onItemLongClick(parent: RecyclerView?, view: View?, position: Int?, model: Model?): Boolean
    }

    /**
     * 监听滑动的数量
     */
     interface SlideMomentumListener {
        /**
         * 设置剩余数量没展示的时候发出回调。当剩余的item数量小于设置的数量 onRemainingAmount方法回被回调
         *
         * @return
         */
        fun onRemainingAmount(): Int

        /**
         * 达到设置的剩余量时候触发
         */
        fun onArriveRemainingAmount()
    }

    open fun getSlideMomentumListener(): SlideMomentumListener? {
        return slideMomentumListener
    }

    open fun setSlideMomentumListener(slideMomentumListener: SlideMomentumListener?) {
        this.slideMomentumListener = slideMomentumListener
    }

    open fun getOnItemClickListener(): OnItemClickListener? {
        return onItemClickListener
    }

    open fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    open fun getOnItemLongClickListener(): OnItemLongClickListener? {
        return onItemLongClickListener
    }

    open fun setOnItemLongClickListener(onItemLongClickListener: OnItemLongClickListener?) {
        this.onItemLongClickListener = onItemLongClickListener
    }

    open fun getModels(): List<M>? {
        return models
    }

    open fun setModels(models: List<M>?) {
        this.models = models
    }

    open fun getCustomData(): Any? {
        return customData
    }

    open fun setCustomData(customData: Any?) {
        this.customData = customData
    }

    open fun getEventTransmissionListener(): EventTransmissionListener? {
        return eventTransmissionListener
    }

    open fun setEventTransmissionListener(eventTransmissionListener: EventTransmissionListener?) {
        this.eventTransmissionListener = eventTransmissionListener
    }

    @SuppressWarnings("unchecked")
    override fun getItemViewType(position: Int): Int {
        viewHolderClass = models?.get(position)?.getHolderClass(position) as Class<ViewHolder<M>>?
        return models?.get(position)?.getResource(position)!!
    }

    private fun initViewListener(view: View){
        view.setOnClickListener(this)
        view.setOnLongClickListener(this)
    }

    @SuppressWarnings("unchecked")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(viewType, parent, false)
        initViewListener(view)
        val constructor: Constructor<*> =
            viewHolderClass?.getDeclaredConstructor(
                View::class.java,
                EventTransmissionListener::class.java,
                ViewGroup::class.java
            )!!
        val viewHolder = constructor.newInstance(view, eventTransmissionListener, parent) as ViewHolder<M>
        viewHolder.onCreated();
        return viewHolder
    }

    override fun getItemCount(): Int {
        if (models == null)return 0
        return models?.size!!
    }

    /**
     * 绑定数据
     */
    @SuppressWarnings("unchecked")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getSlideMomentumListener() != null && itemCount - position < getSlideMomentumListener()!!.onRemainingAmount()) {
            getSlideMomentumListener()!!.onArriveRemainingAmount()
        }
        val viewHolder: ViewHolder<M> = holder as ViewHolder<M>
        viewHolder.onBindViewHolder(models?.get(position))
    }

    @SuppressWarnings("unchecked")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        val viewHolder: ViewHolder<M> = holder as ViewHolder<M>
        viewHolder.onBindViewHolder(payloads)
    }

    /**
     * （当Item进入这个页面的时候调用）
     *
     * @param holder
     */
    @SuppressWarnings("unchecked")
    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        (holder as ViewHolder<M>).onViewAttachedToWindow()
    }

    /**
     * （当Item离开这个页面的时候调用）
     *
     * @param holder
     */
    @SuppressWarnings("unchecked")
    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        (holder as ViewHolder<M>).onViewDetachedFromWindow()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView= recyclerView;
    }

    @SuppressWarnings("unchecked")
    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        (holder as ViewHolder<M>).onViewRecycled()
    }

    override fun onClick(v: View?) {
        if (onItemClickListener != null) {
            val position: Int? = recyclerView?.getChildAdapterPosition(v!!)
            position?.let {
                onItemClickListener?.onItemClick(recyclerView,v,
                    it,getModels()?.get(position))
            }
        }
    }

    override fun onLongClick(v: View?): Boolean {
        if (onItemLongClickListener !== null) {
            val position: Int? = recyclerView?.getChildAdapterPosition(v!!)
            return onItemLongClickListener?.onItemLongClick(recyclerView,v,position,getModels()?.get(
                position!!
            ))!!
        }
        return true
    }
}



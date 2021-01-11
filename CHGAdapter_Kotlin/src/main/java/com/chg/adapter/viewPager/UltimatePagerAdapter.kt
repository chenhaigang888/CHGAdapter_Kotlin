package com.chg.adapter.viewPager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.chg.adapter.base.EventTransmissionListener
import com.chg.adapter.base.Model
import com.chg.adapter.recyclerView.ViewHolder
import java.lang.reflect.Constructor

class UltimatePagerAdapter<M: Model>() : PagerAdapter() {

    private var context: Context? = null
    private var models: List<M>? = null
    private var customData: Any? = null
    private var eventTransmissionListener: EventTransmissionListener? = null

    constructor(context: Context?, models: List<M>?) : this() {
        this.context = context
        this.models = models
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

    override fun getCount(): Int {
        if (models == null) return 0
        return models!!.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        return super.instantiateItem(container, position)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)

    }

    fun onCreateViewHolder(parent: ViewGroup, position: Int): View? {
        val model = models?.get(position)
        val resource = model?.getResource(position)
        val viewHolderClass = model?.getHolderClass(position)
        val view = resource?.let { LayoutInflater.from(context).inflate(it, parent, false) }

        val constructor: Constructor<*> =
            viewHolderClass?.getDeclaredConstructor(
                View::class.java,
                EventTransmissionListener::class.java,
                ViewGroup::class.java
            )!!
        val viewHolder = constructor.newInstance(view, eventTransmissionListener, parent) as ViewHolder<M>
        viewHolder.onCreated();
        view?.tag = viewHolder
        return view
    }
}
package com.chg.adapter.extension

import androidx.recyclerview.widget.RecyclerView
import com.chg.adapter.recyclerView.Adapter
import com.chg.adapter.base.EventTransmissionListener
import com.chg.adapter.base.Model


var RecyclerView.eventTransmissionListener: EventTransmissionListener?
    get() {
        return adapter().getEventTransmissionListener()
    }
    set(value) {
        adapter().setEventTransmissionListener(value)
    }

var RecyclerView.models: List<Model>?
    get() {
        return adapter().getModels()
    }
    set(value) {
        adapter().setModels(value)
    }

var RecyclerView.customData: Any?
    get() {
        return adapter().getCustomData()
    }
    set(value) {
        adapter().setCustomData(value)
    }

var RecyclerView.slideMomentumListener: Adapter.SlideMomentumListener?
    get() {
        return adapter().getSlideMomentumListener()
    }
    set(value) {
        adapter().setSlideMomentumListener(value)
    }

@SuppressWarnings("unchecked")
fun RecyclerView.adapter(): Adapter<Model> {
    if (adapter == null) {
        adapter = Adapter<Model>(context)
    }
    return adapter as Adapter<Model>
}


fun RecyclerView.notifyDataSetChanged() {
    adapter().notifyDataSetChanged()
}

fun RecyclerView.notifyItemChanged(position: Int) {
    adapter().notifyItemRangeChanged(position, 1)
}

fun RecyclerView.notifyItemChanged(position: Int, payload: Any?) {
    adapter().notifyItemRangeChanged(position, 1, payload)
}

fun RecyclerView.notifyItemRangeChanged(positionStart: Int, itemCount: Int) {
    adapter().notifyItemRangeChanged(positionStart, itemCount)
}

fun RecyclerView.notifyItemRangeChanged(
    positionStart: Int, itemCount: Int,
    payload: Any?
) {
    adapter().notifyItemRangeChanged(positionStart, itemCount, payload)
}

fun RecyclerView.notifyItemInserted(position: Int) {
    adapter().notifyItemRangeInserted(position, 1)
}

fun RecyclerView.notifyItemMoved(fromPosition: Int, toPosition: Int) {
    adapter().notifyItemMoved(fromPosition, toPosition)
}

fun RecyclerView.notifyItemRangeInserted(positionStart: Int, itemCount: Int) {
    adapter().notifyItemRangeInserted(positionStart, itemCount)
}

fun RecyclerView.notifyItemRemoved(position: Int) {
    adapter().notifyItemRangeRemoved(position, 1)
}

fun RecyclerView.notifyItemRangeRemoved(positionStart: Int, itemCount: Int) {
    adapter().notifyItemRangeRemoved(positionStart, itemCount)
}

fun RecyclerView.setOnItemClickListener  (onItemClickListener: Adapter.OnItemClickListener) {
    adapter().setOnItemClickListener(onItemClickListener)
}

fun RecyclerView.setOnItemLongClickListener  (onItemLongClickListener : Adapter.OnItemLongClickListener) {
    adapter().setOnItemLongClickListener(onItemLongClickListener)
}



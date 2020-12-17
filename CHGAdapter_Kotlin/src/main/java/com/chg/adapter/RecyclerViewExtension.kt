package com.chg.adapter

import androidx.recyclerview.widget.RecyclerView


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

fun RecyclerView.adapter(): Adapter<Model> {
    if (adapter == null) {
        adapter = Adapter<Model>(context)
    }
    return adapter as Adapter<Model>
}


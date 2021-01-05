package com.example.chgadapter_kotlin_demo.weibo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.chg.adapter.EventTransmissionListener
import com.chg.adapter.EventTransmissionListener.CallBack
import com.chg.adapter.Model
import com.chg.adapter.extension.eventTransmissionListener
import com.chg.adapter.extension.getContext
import com.chg.adapter.extension.models
import com.example.chgadapter_kotlin_demo.R
import java.util.*

class ShowBigImageViewActivity : AppCompatActivity(),EventTransmissionListener {

    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_big_image_view)
        recyclerView = findViewById(R.id.recyclerView)
        val manager = LinearLayoutManager(getContext())
        manager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.setLayoutManager(manager)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        val map:MutableMap<Any,Any> = intent.extras!!["sources"] as MutableMap<Any, Any>
        recyclerView.models = map["sources"] as List<Model>?
        recyclerView.scrollToPosition((map!!["position"] as Int?)!!)
        recyclerView.eventTransmissionListener = this

    }

    override fun onEventTransmissionListener(target: Any?, params: Any?, eventId: Int, callBack: CallBack?): Any? {
        finish()
        return null
    }
}
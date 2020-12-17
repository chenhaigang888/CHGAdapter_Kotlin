package com.example.chgadapter_kotlin_demo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.chg.adapter.EventTransmissionListener
import com.chg.adapter.eventTransmissionListener
import com.chg.adapter.models
import com.example.chgadapter_kotlin_demo.model.Animals
import com.example.chgadapter_kotlin_demo.model.Person


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.models = getAnimals()

        recyclerView.addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                Log.i("chg", "滚动状态 newState：" + newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.i("chg", "滚动 dx：" + dx + "  dy:" + dy)
            }
        })

        recyclerView.eventTransmissionListener = object : EventTransmissionListener {
            override fun onEventTransmissionListener(
                target: Any?,
                params: Any?,
                tag: Int,
                callBack: EventTransmissionListener.CallBack?
            ): Any? {
                recyclerView.models = getPerson()
                recyclerView.adapter?.notifyDataSetChanged()
                return null
            }
        }
    }

    fun getAnimals(): List<Animals> {
        var models = mutableListOf<Animals>()
        for (index in 1..1000) {
            models.add(Animals("头:$index"))
        }
        return models
    }

    fun getPerson(): List<Person> {
        var models = mutableListOf<Person>()
        for (index in 1..1000) {
            models.add(Person())
        }
        return models
    }
}
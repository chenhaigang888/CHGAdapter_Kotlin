package com.example.chgadapter_kotlin_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.chg.adapter.EventTransmissionListener
import com.chg.adapter.eventTransmissionListener
import com.chg.adapter.models
import com.example.chgadapter_kotlin_demo.model.Animals
import com.example.chgadapter_kotlin_demo.model.Person


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.models = getAnimals()

        recyclerView.eventTransmissionListener = object : EventTransmissionListener{
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
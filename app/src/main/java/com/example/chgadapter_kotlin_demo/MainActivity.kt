package com.example.chgadapter_kotlin_demo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.chg.adapter.EventTransmissionListener
import com.chg.adapter.eventTransmissionListener
import com.chg.adapter.models
import com.example.chgadapter_kotlin_demo.VH.AnimalsViewHolder
import com.example.chgadapter_kotlin_demo.model.Animals

class MainActivity : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.models = getAnimals()

        //接收ItemView的按钮点击事件
        recyclerView.eventTransmissionListener = (object :EventTransmissionListener{
            override fun onEventTransmissionListener(
                target: Any?,
                params: Any?,
                tag: Int,
                callBack: EventTransmissionListener.CallBack?
            ): Any? {
                if (target is AnimalsViewHolder) {
                    if (tag == 1) {
                        Toast.makeText(this@MainActivity,"按钮事件传递到页面",Toast.LENGTH_LONG).show()
                    }
                }
                return null
            }
        })



    }

    fun getAnimals(): List<Animals> {
        var models = mutableListOf<Animals>()
        for (index in 1..1000) {
            models.add(Animals("头:$index"))
        }
        return models
    }



}
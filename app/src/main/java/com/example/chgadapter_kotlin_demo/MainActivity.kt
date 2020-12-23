package com.example.chgadapter_kotlin_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.chg.adapter.models
import com.example.chgadapter_kotlin_demo.model.Animals

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.models = getAnimals()


    }

    fun getAnimals(): List<Animals> {
        var models = mutableListOf<Animals>()
        for (index in 1..1000) {
            models.add(Animals("头:$index"))
        }
        return models
    }

}
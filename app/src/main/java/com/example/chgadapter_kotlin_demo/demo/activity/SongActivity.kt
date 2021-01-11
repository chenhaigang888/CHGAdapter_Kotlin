package com.example.chgadapter_kotlin_demo.demo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.chg.adapter.base.Model
import com.chg.adapter.extension.models
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.demo.model.SongModel

/**
 * 其实 可以使用一个Activity
 */
class SongActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        //设置数据
        recyclerView.models = getModels()
    }

    fun getModels(): List<Model> {
        var models = mutableListOf<SongModel>()
        for (index in 1..1000) {
            models.add(SongModel(R.drawable.music,"歌曲名称:$index","歌手名字：$index"))
        }
        return models
    }

}

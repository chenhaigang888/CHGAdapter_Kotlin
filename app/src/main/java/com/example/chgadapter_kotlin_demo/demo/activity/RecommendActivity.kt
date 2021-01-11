package com.example.chgadapter_kotlin_demo.demo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.chg.adapter.base.Model
import com.chg.adapter.extension.models
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.demo.model.AlbumModel
import com.example.chgadapter_kotlin_demo.demo.model.SongModel


class RecommendActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        //设置数据
        recyclerView.models = getModels()
    }

    fun getModels(): List<Model> {
        var models = mutableListOf<Model>()
        for (i in 0..99) {
            if (i % 2 == 0) {
                models.add(SongModel(R.drawable.music,"歌曲名称:$i","歌手名字：$i"))
            } else {
                models.add(AlbumModel("专辑名称：$i", "歌手"))
            }
        }
        return models
    }
}
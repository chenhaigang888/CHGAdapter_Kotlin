package com.example.chgadapter_kotlin_demo.demo.activity

import com.chg.adapter.Model
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.demo.model.AlbumModel
import com.example.chgadapter_kotlin_demo.demo.model.SongModel


class RecommendActivity : MainActivity() {

    override fun getModels(): List<Model> {
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
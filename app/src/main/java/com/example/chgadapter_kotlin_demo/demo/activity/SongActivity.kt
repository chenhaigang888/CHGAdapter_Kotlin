package com.example.chgadapter_kotlin_demo.demo.activity

import com.chg.adapter.Model
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.demo.model.SongModel

/**
 * 其实 可以使用一个Activity
 */
class SongActivity : MainActivity() {

    override fun getModels(): List<Model> {
        var models = mutableListOf<SongModel>()
        for (index in 1..1000) {
            models.add(SongModel(R.drawable.music,"歌曲名称:$index","歌手名字：$index"))
        }
        return models
    }

}

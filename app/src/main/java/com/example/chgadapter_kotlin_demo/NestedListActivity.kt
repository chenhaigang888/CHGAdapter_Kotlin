package com.example.chgadapter_kotlin_demo

import com.chg.adapter.Model
import com.example.chgadapter_kotlin_demo.model.*


class NestedListActivity : MainActivity() {

    override fun getModels(): List<Model> {
        return getData()
    }

    fun getData(): List<Model> {
        var list = mutableListOf<Model>()
        for (i in 0..99) {
            if (i % 5 == 0) {
                list.add(SongModel(R.drawable.music,"歌曲名称:$i","歌手名字：$i"))
            } else if (i % 5 == 1) {
                list.add(AlbumModel("专辑名称：$i", "歌手"))
            } else if (i % 5 == 2) {
                list.add(creageMusicData(i, i % 2))
            } else {
                list.add(creageHybridData())
            }
        }
        return list
    }

    /*创建推荐内容*/
    fun creageMusicData(position: Int, type: Int): MusicModel {
        var list = mutableListOf<Model>()
        val title = if (type == 0) "推荐歌曲" else "推荐专辑"
        for (i in 0..99) {
            if (type == 0) {
                list.add(NestedSongModel(R.drawable.music,"推荐歌曲：$i","歌手：$i"))
            } else if (type == 1) {
                list.add(NestedAlbumModel("推荐专辑：$i", "歌手：$i"))
            }
        }
        return MusicModel(title + position, list)
    }

    /*创建推荐内容*/
    fun creageHybridData(): MusicModel {
        var list = mutableListOf<Model>()
        for (i in 0..99) {
            if (i % 2 == 0) {
                list.add(NestedSongModel(R.drawable.music,"推荐歌曲：$i","歌手：$i"))
            } else {
                list.add(NestedAlbumModel("推荐专辑：$i", "歌手：$i"))
            }
        }
        return MusicModel("推荐的歌曲+专辑", list)
    }
}
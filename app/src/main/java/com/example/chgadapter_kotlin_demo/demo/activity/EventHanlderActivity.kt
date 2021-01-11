package com.example.chgadapter_kotlin_demo.demo.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.chg.adapter.base.EventTransmissionListener
import com.chg.adapter.extension.eventTransmissionListener
import com.chg.adapter.extension.models
import com.chg.adapter.base.Model
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.demo.VH.PlayListItemViewHolder
import com.example.chgadapter_kotlin_demo.demo.model.PlayListItemModel


class EventHanlderActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        //设置数据
        recyclerView.models = getModels()
        //接收ItemView的中的事件
        recyclerView.eventTransmissionListener = (object: EventTransmissionListener {
            override fun onEventTransmissionListener(
                target: Any?,
                params: Any?,
                eventId: Int,
                callBack: EventTransmissionListener.CallBack?
            ): Any? {
                Log.i("chg", "被点击")
                if (target is PlayListItemViewHolder){
                    if (eventId == 1) {//播放列表中播放被点击，演示同步返回数据
                        return handlePlayStatus(target, params, eventId, callBack);
                    } else if(eventId == 2){//收藏
                        return handleCollectionStatus(target, params, eventId, callBack);
                    }
                }
                return null
            }
        })
    }

    /*处理ItemView中的播放音乐的状态(演示同步返回数据)*/
    fun handlePlayStatus(
        target: Any?,
        params: Any?,
        eventId: Int,
        callBack: EventTransmissionListener.CallBack?
    ): Any? {
        val playStatus = params as Boolean
        return !playStatus //更改播放状态返回
    }

    /*处理ItemView中的播放音乐的状态(演示异步返回数据)*/
    fun handleCollectionStatus(
        target: Any?,
        params: Any?,
        eventId: Int,
        callBack: EventTransmissionListener.CallBack?
    ): Any? {
        val collectionStatus = params as Boolean
        /*这里可以做一些耗时的操作，比如将是否收藏当前音乐发送给服务器，服务器返回结果后再通过callBack回掉到列表中更新状态*/
        /*耗时操作省略*/
        callBack?.callBack(!collectionStatus) //使用异步返回
        return null
    }

    open fun getModels(): List<Model> {
        var models = mutableListOf<Model>()
        for (i in 0..99) {
            models.add(PlayListItemModel("歌曲名称：$i", "歌手名字：$i"))
        }
        return models
    }
}
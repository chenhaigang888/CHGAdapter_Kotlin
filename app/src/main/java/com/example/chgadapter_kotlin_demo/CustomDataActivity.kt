package com.example.chgadapter_kotlin_demo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.chg.adapter.*
import com.example.chgadapter_kotlin_demo.Other.LoginData
import com.example.chgadapter_kotlin_demo.VH.InputBoxViewHolder
import com.example.chgadapter_kotlin_demo.VH.SubmitBtnViewHolder
import com.example.chgadapter_kotlin_demo.model.InputBoxModel
import com.example.chgadapter_kotlin_demo.model.OneTitleModel
import com.example.chgadapter_kotlin_demo.model.SubmitBtnModel

//做一个登录页面来演示 在ItemView，slice之间共享数据。这个主要颜色数据共享，并不一定要这么做。具体还是要看自己的需求
class CustomDataActivity : AppCompatActivity() ,EventTransmissionListener{

    private lateinit var recyclerView: RecyclerView
    private var loginData = LoginData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.models = getModels()
        recyclerView.customData = loginData
        recyclerView.eventTransmissionListener = this
    }

    fun getModels(): List<Model> {
        var models = mutableListOf<Model>()
        models.add(OneTitleModel("登录"))
        models.add(InputBoxModel("用户名：","","请输入用户名",false))
        models.add(InputBoxModel("密码：","","请输入密码",true))
        models.add(SubmitBtnModel("登录"))
        return models
    }

    override fun onEventTransmissionListener(
        target: Any?,
        params: Any?,
        eventId: Int,
        callBack: EventTransmissionListener.CallBack?
    ): Any? {
        if (target is SubmitBtnViewHolder) {
            Log.i("chgLog","activity中LoginData的数据:$loginData")
        }
        return null
    }
}
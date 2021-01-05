package com.example.chgadapter_kotlin_demo.demo.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.chg.adapter.*
import com.chg.adapter.extension.eventTransmissionListener
import com.chg.adapter.extension.models
import com.chg.adapter.extension.setOnItemClickListener
import com.chg.adapter.extension.setOnItemLongClickListener
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.demo.VH.SongViewHolder
import com.example.chgadapter_kotlin_demo.demo.model.MenuItemModel
import com.example.chgadapter_kotlin_demo.search.activity.SearchActivity
import com.example.chgadapter_kotlin_demo.search.model.GroupModel
import com.example.chgadapter_kotlin_demo.search.model.PostModel
import com.example.chgadapter_kotlin_demo.search.model.SearchSuggestionsModel
import com.example.chgadapter_kotlin_demo.search.model.TagModel
import com.example.chgadapter_kotlin_demo.weibo.activity.FoundActivity

open class MainActivity : AppCompatActivity(),EventTransmissionListener,Adapter.OnItemClickListener,Adapter.OnItemLongClickListener{

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        //设置数据
        recyclerView.models = getModels()
        //接收ItemView的中的事件
        recyclerView.eventTransmissionListener = this
        recyclerView.setOnItemClickListener(this)
        recyclerView.setOnItemLongClickListener(this)
    }

    open fun getModels(): List<Model> {
        var models = mutableListOf<Model>()
        models.add(MenuItemModel("简单的显示（显示一种布局）","最基础使用", SongActivity::class.java))
        models.add(MenuItemModel("简单的显示（显示多种布局）","最基础使用", RecommendActivity::class.java))
        models.add(MenuItemModel("嵌套RecyclerView","最基础使用", NestedListActivity::class.java))
        models.add(MenuItemModel("ItemView中的按钮点击、等事件","基础的使用", EventHanlderActivity::class.java))
        models.add(MenuItemModel("设置自定义数据","基础的使用", CustomDataActivity::class.java))
        models.add(MenuItemModel("比较复杂的演示（搜索）","使用当前框架在一个页面展示搜索及结果展示", SearchActivity::class.java))
        models.add(MenuItemModel("比较复杂的演示（微博）","使用当前框架模拟微博", FoundActivity::class.java))
        return models
    }

    override fun onEventTransmissionListener(
        target: Any?,
        params: Any?,
        tag: Int,
        callBack: EventTransmissionListener.CallBack?
    ): Any? {
        if (target is SongViewHolder) {
            if (tag == 1) {

            }
        }
        return null
    }

    override fun onItemClick(parent: RecyclerView?, view: View?, position: Int?, model: Model?) {
        if (model is MenuItemModel) {
            val  data: MenuItemModel = model as MenuItemModel
            var intent = Intent(this@MainActivity,data.activityClass)
            startActivity(intent)
        }
    }

    override fun onItemLongClick(
        parent: RecyclerView?,
        view: View?,
        position: Int?,
        model: Model?
    ): Boolean {
        if (model is MenuItemModel) {
            val data: MenuItemModel = model as MenuItemModel
            Toast.makeText(this@MainActivity,data.title,Toast.LENGTH_LONG).show()
        }

        return true
    }

}
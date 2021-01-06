package com.example.chgadapter_kotlin_demo.weibo.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chg.adapter.Adapter
import com.chg.adapter.Adapter.SlideMomentumListener
import com.chg.adapter.EventTransmissionListener
import com.chg.adapter.EventTransmissionListener.CallBack
import com.chg.adapter.Model
import com.chg.adapter.extension.*
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.activity.SearchActivity
import com.example.chgadapter_kotlin_demo.weibo.VH.FuncItemViewHolder
import com.example.chgadapter_kotlin_demo.weibo.VH.SourceViewHolder
import com.example.chgadapter_kotlin_demo.weibo.model.*
import com.google.gson.*
import java.io.InputStreamReader
import java.io.Serializable
import java.util.*

class FoundActivity : AppCompatActivity(),EventTransmissionListener,SlideMomentumListener,Adapter.OnItemClickListener,Adapter.OnItemLongClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val recycleViewData = mutableListOf<Model>()

    private var functionArea: FunctionArea = FunctionArea()
    private var pageIndex = 0
    private var isPullRefresh: Boolean? = null
    private var isLoading : Boolean? = null//是否正在加载

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        swipeRefreshLayout = findViewById(R.id.refresh)
        recyclerView = findViewById(R.id.recycleView)
        configEventTransmissionListener()
        recycleViewData.add(getFunctionArea())
        configRefreshLayout()
        swipeRefreshLayout.setRefreshing(true)
        isPullRefresh = true
        postAsynHttp()
    }

    /**
     * 配置RecycleView
     */
    fun configEventTransmissionListener() {
        val manager = LinearLayoutManager(getContext())
        manager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = manager
        recyclerView.eventTransmissionListener = this
        recyclerView.slideMomentumListener = this
        recyclerView.setOnItemClickListener(this)
        recyclerView.setOnItemLongClickListener(this)
    }

    fun configRefreshLayout() {
        //下拉刷新的圆圈是否显示
        swipeRefreshLayout.isRefreshing = false
        //设置下拉时圆圈的颜色（可以由多种颜色拼成）
        swipeRefreshLayout.setColorSchemeResources(
            android.R.color.holo_blue_light,
            android.R.color.holo_red_light,
            android.R.color.holo_orange_light
        )
        //设置下拉时圆圈的背景颜色（这里设置成白色）
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white)
        swipeRefreshLayout.setOnRefreshListener {
            isPullRefresh = true
            postAsynHttp()
        }
    }

    //刷新
    fun refresh(){
        runOnUiThread {
            isLoading = false
            swipeRefreshLayout.isRefreshing = false
            recyclerView.models = recycleViewData
            recyclerView.notifyDataSetChanged()
        }
    }

    //模拟网络请求
    private fun postAsynHttp() {
        isLoading = true
        pageIndex += 1
        //模拟网络请求
        Thread(object :Runnable{
            override fun run() {
                var rawName = "weibo_data_$pageIndex"
                val responseId = getContext().resources.getIdentifier(rawName,"raw",getContext().packageName)
                if (responseId == 0){
                    pageIndex = 0
                    refresh()
                    return
                }
                var inputStream = getContext().resources.openRawResource(responseId)
                var inputStreamReader = InputStreamReader(inputStream)
                var str = inputStreamReader.readText()
                inputStream.close()
                inputStreamReader.close()

                //解析数据
                val gson = Gson()
                Log.i("chgwebodata", "str:" + str);
                val serverResponse = gson.fromJson(str, ServerResponse::class.java)
                val list = serverResponse.data
                Log.i("chgWeibo","list:${list.count()}")
                for (found in list) {
                    val type = found.type
                    val factor = found.factor
                    val feedExts = found.feedExts
                    if (type == "0") {
                        Log.i("chg", "内容类型：$type")
                    } else if (type == "1") {
                        if (factor == "4") { //精彩小视频
                        } else { //发布的内容
                            val foundSendData = parserJsonArray(gson.toJson(feedExts),FoundSendData::class.java)
                            if (isPullRefresh!!) {
                                recycleViewData.add(1, foundSendData[0])
                            } else {
                                recycleViewData.add(foundSendData[0])
                            }
                        }
                    } else if (type == "2") { //好友推荐
                        val recommendedFriendModel = RecommendedFriendModel()
                        val foundUserModels = parserJsonArray(gson.toJson(feedExts),FoundUserModel::class.java)
                        recommendedFriendModel.friends = foundUserModels
                        recycleViewData.add(recommendedFriendModel)
                    }
                }
                refresh()
            }
        }).start()
    }

    fun parserJsonArray(strJson: String?,classT:Class<*>): ArrayList<Model> {
        val list = ArrayList<Model>()
        //创建一个Gson对象
        val gson = Gson()
        //创建一个JsonParser
        val parser = JsonParser()
        //通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
        val el = parser.parse(strJson)
        //把JsonElement对象转换成JsonObject
        var jsonObj: JsonObject? = null
        if (el.isJsonObject) {
            jsonObj = el.asJsonObject
        }
        //把JsonElement对象转换成JsonArray
        var jsonArray: JsonArray? = null
        if (el.isJsonArray) {
            jsonArray = el.asJsonArray
        }
        //遍历JsonArray对象
        val it: Iterator<*> = jsonArray!!.iterator()
        while (it.hasNext()) {
            val e = it.next() as JsonElement
            //JsonElement转换为JavaBean对象
            list.add(gson.fromJson(e, classT) as Model)
        }
        return list
    }

    fun getFunctionArea(): FunctionArea {
        if (functionArea.funcItems != null && functionArea.funcItems.count() > 0) return functionArea
        val funcs =
            arrayOf("搜索", "附近的人", "闪聊", "树洞", "雷达", "校友通讯录", "附近的人", "闪聊", "树洞", "雷达", "校友通讯录")
        val icons = intArrayOf(
            R.drawable.da_xue_man_you,
            R.drawable.da_xue_man_you,
            R.drawable.shan_liao,
            R.drawable.shu_dong,
            R.drawable.tong_xun_lu,
            R.drawable.lei_da,
            R.drawable.da_xue_man_you,
            R.drawable.shan_liao,
            R.drawable.shu_dong,
            R.drawable.tong_xun_lu,
            R.drawable.lei_da
        )
        val list = mutableListOf<FuncItem>()
        var funcItem: FuncItem? = null
        for (i in 0..(funcs.count()-1) ) {
            Log.i("chgLog","i:$i")
            funcItem = FuncItem()
            funcItem.name = funcs[i]
            funcItem.icon = icons[i]
            list.add(funcItem)
        }
        functionArea.setFuncItems(list)
        return functionArea
    }

    override fun onEventTransmissionListener(
        target: Any?,
        params: Any?,
        eventId: Int,
        callBack: CallBack?
    ): Any? {
        Log.i("chgLog","点击")
        if (target is FuncItemViewHolder) {
            val intent = Intent(getContext(), SearchActivity::class.java)
            startActivity(intent)
        } else if (target is SourceViewHolder) {
            val intent = Intent(getContext(), ShowBigImageViewActivity::class.java)
            intent.putExtra("sources", params as Serializable?)
            startActivity(intent)
        }
        return null
    }

    override fun onRemainingAmount(): Int {
        return 30
    }

    override fun onArriveRemainingAmount() {
        //加载更多数据
        isPullRefresh = false
        if (!isLoading!!) {
            postAsynHttp()
        }
    }

    override fun onItemClick(parent: RecyclerView?, view: View?, position: Int?, model: Model?) {
        Toast.makeText(getContext(), "我好像被点击了", Toast.LENGTH_LONG).show()
    }

    override fun onItemLongClick(
        parent: RecyclerView?,
        view: View?,
        position: Int?,
        model: Model?
    ): Boolean {
        Toast.makeText(getContext(), "我好像被长按了", Toast.LENGTH_LONG).show()
        return true
    }

}
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
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException
import java.io.Serializable
import java.util.*

class FoundActivity : AppCompatActivity() {

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
        recyclerView.eventTransmissionListener = (object : EventTransmissionListener {
            override fun onEventTransmissionListener(
                target: Any?,
                params: Any?,
                eventId: Int,
                callBack: CallBack?
            ): Any? {
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
        })

        recyclerView.slideMomentumListener = (object : SlideMomentumListener {
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
        })
        recyclerView.setOnItemClickListener(object : Adapter.OnItemClickListener {
            override fun onItemClick(
                parent: RecyclerView?,
                view: View?,
                position: Int?,
                model: Model?
            ) {
                Toast.makeText(getContext(), "我好像被点击了", Toast.LENGTH_LONG).show()
            }
        })
        recyclerView.setOnItemLongClickListener(object : Adapter.OnItemLongClickListener {
            override fun onItemLongClick(
                parent: RecyclerView?,
                view: View?,
                position: Int?,
                model: Model?
            ): Boolean {
                Toast.makeText(getContext(), "我好像被长按了", Toast.LENGTH_LONG).show()
                return true
            }
        })
    }

    fun configRefreshLayout() {
        //下拉刷新的圆圈是否显示
        swipeRefreshLayout!!.isRefreshing = false
        //设置下拉时圆圈的颜色（可以由多种颜色拼成）
        swipeRefreshLayout!!.setColorSchemeResources(
            android.R.color.holo_blue_light,
            android.R.color.holo_red_light,
            android.R.color.holo_orange_light
        )
        //设置下拉时圆圈的背景颜色（这里设置成白色）
        swipeRefreshLayout!!.setProgressBackgroundColorSchemeResource(android.R.color.white)
        swipeRefreshLayout!!.setOnRefreshListener {
            isPullRefresh = true
            postAsynHttp()
        }
    }

    private fun postAsynHttp() {
        isLoading = true
        pageIndex += 1
        val mOkHttpClient = OkHttpClient()
        var params = mutableMapOf<Any, Any>()

        params["appId"] = "1003604205986484225"
        params["lat"] = "0"
        params["lng"] = "0"
        params["pageIndex"] = pageIndex.toString() + ""
        params["pageSize"] = "10"
        params["platform"] = "ios"
        params["timestamp"] = "1578715788332"
        params["token"] =
            "fcb525ba5eebef743a028fae49ff382c9387e2ed9d5e04fcee6913fc3ee4937b64a2104a27ef241b93c3d0baa401c0b39cb17883cfbbaf9895ebc813c245d22916bcd42b5c33121972592575ac2be4f0"
        params["version"] = "1.1.4"
        val gson = Gson()

        val JSON: MediaType = "application/json; charset=utf-8".toMediaTypeOrNull()!!
        val body = RequestBody.create(JSON, gson.toJson(params))
        val request: Request = Request.Builder()
            .url("https://api.dnaerapp.com/zoology/feed/mobile/v1/feeds")
            .post(body)
            .build()
        val call = mOkHttpClient.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val str = response.body!!.string()
//                                Log.i("chg", "str:" + str);
                val serverResponse = gson.fromJson(str, ServerResponse::class.java)
                val list = serverResponse.data
                for (found in list) {
                    val type = found.type
                    val factor = found.factor
                    val feedExts = found.feedExts
                    if (type == "0") {
                        Log.i("chg", "内容类型：$type")
                    } else if (type == "1") {
                        if (factor == "4") { //精彩小视频
                        } else { //发布的内容
                            val foundSendData = parserJsonArray(gson.toJson(feedExts))
                            if (isPullRefresh!!) {
                                recycleViewData.add(1, foundSendData[0])
                            } else {
                                recycleViewData.add(foundSendData[0])
                            }
                        }
                    } else if (type == "2") { //好友推荐
                    }
                }
                runOnUiThread {
                    isLoading = false
                    swipeRefreshLayout.isRefreshing = false
                    recyclerView.models = recycleViewData
                    recyclerView.notifyDataSetChanged()
                }
            }
        })
    }

    fun parserJsonArray(strJson: String?): ArrayList<FoundSendData> {
        val list = ArrayList<FoundSendData>()
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
            list.add(gson.fromJson(e, FoundSendData::class.java))
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

    fun onEventTransmissionListener(
        target: Any?,
        params: Any?,
        eventId: Int,
        callBack: CallBack?
    ): Any? {
        return null
    }
}
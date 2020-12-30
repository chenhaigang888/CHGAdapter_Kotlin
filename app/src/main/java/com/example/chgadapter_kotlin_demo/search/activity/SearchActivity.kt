package com.example.chgadapter_kotlin_demo.search.activity
import android.nfc.Tag
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.chg.adapter.*
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.VH.SearchSuggestionsViewHolder
import com.example.chgadapter_kotlin_demo.search.model.*

class SearchActivity : AppCompatActivity() ,View.OnKeyListener,View.OnClickListener,EventTransmissionListener{

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mEditText:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        mRecyclerView = findViewById(R.id.recyclerView)
        mEditText = findViewById(R.id.editText);
        mEditText.setOnKeyListener(this)
        findViewById<Button>(R.id.searchButton).setOnClickListener(this)
        mRecyclerView.models = defaultData()
        mRecyclerView.eventTransmissionListener = this
    }

    //默认数据，显示历史搜索和热么搜索
    fun defaultData():List<Model> {
        //构造历史搜索数据
        var historySearchData = mutableListOf<TagModel>()
        for (i in 0..10) {
            historySearchData.add(TagModel("历史：$i"))
        }
        var historySearch = SearchSuggestionsModel("历史搜索",historySearchData)

        //构造热么搜索数据
        var hotSearchData = mutableListOf<TagModel>()
        for (i in 0..10) {
            hotSearchData.add(TagModel("热门：$i"))
        }
        var hotSearch = SearchSuggestionsModel("热门搜索",hotSearchData)
        return listOf(historySearch,hotSearch)
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if (v == mEditText) {//如果是搜索框
            val text = mEditText.text.toString()
            if (text.length > 0) {//显示输入提示选项
                mRecyclerView.models = createAdvices(text)
            } else {//显示默认的数据
                mRecyclerView.models = defaultData()
            }
            mRecyclerView.notifyDataSetChanged()
        }
        return false
    }

    fun createAdvices(text:String):List<Model>{
        var list = mutableListOf<AdviceModel>()
        for (i in 0..30) {
            list.add(AdviceModel("搜索建议： $text  $i"))
        }
        return list
    }

    override fun onClick(v: View?) {
        if (v == findViewById(R.id.searchButton)) {//搜索按钮
            //展示搜索结果
            mRecyclerView.models = listOf(getSearchResults(mEditText.text.toString()))
            mRecyclerView.notifyDataSetChanged()
        }
    }

    fun getSearchResults(keyword:String):TitleBarModel{
        var list = mutableListOf<TitleBarItemModel>()//搜索结果分类的标题
        var searchResults = mutableListOf<SearchResultModel>()//搜索分类结果
        val titles = listOf("综合", "校友", "关系","用户","群聊","圈子","聊天记录","私藏","视频","图片","文章","音乐","语音","文字")
        val titleTypes = listOf(0, 1, 2,3,4,5,6,7,8,9,10,11,12,13)
        var j = 0
        for (title in titles) {
            //构造横向滚动的标题
            list.add(TitleBarItemModel(title))
            //构造搜索结果数据
            var searchResultItemModels = mutableListOf<SearchResultItemModel>()
            for (i in 0..100) {
                searchResultItemModels.add(SearchResultItemModel(titleTypes[j],"搜索结果:$title $keyword $i"))
            }
            searchResults.add(SearchResultModel(searchResultItemModels))
            j+=1
        }
        return TitleBarModel(list,searchResults)
    }

    override fun onEventTransmissionListener(target: Any?, params: Any?, eventId: Int, callBack: EventTransmissionListener.CallBack?): Any? {
        if (target is SearchSuggestionsViewHolder) {
            return clickHistoryOrHotTag(target,params,eventId,callBack)
        }
        return null
    }

    //处理点击历史搜索，热么搜索
    fun clickHistoryOrHotTag(target: Any?, params: Any?, eventId: Int, callBack: EventTransmissionListener.CallBack?) : Any?{
        var tag:TagModel = params as TagModel
        mRecyclerView.models = listOf(getSearchResults(tag.name))
        mRecyclerView.notifyDataSetChanged()
        return null
    }
}
package com.example.chgadapter_kotlin_demo.search.activity

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.chg.adapter.*
import com.chg.adapter.extension.*
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.VH.SearchResultViewHolder
import com.example.chgadapter_kotlin_demo.search.VH.SearchSuggestionsViewHolder
import com.example.chgadapter_kotlin_demo.search.model.*

class SearchActivity : AppCompatActivity() ,View.OnKeyListener,View.OnClickListener,EventTransmissionListener,Adapter.OnItemClickListener{

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mEditText:EditText
    private var mHistorySearchData = mutableListOf<TagModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        mRecyclerView = findViewById(R.id.recyclerView)
        mEditText = findViewById(R.id.editText);
        mEditText.setOnKeyListener(this)
        findViewById<Button>(R.id.searchButton).setOnClickListener(this)
        mRecyclerView.models = defaultData()
        mRecyclerView.eventTransmissionListener = this
        mRecyclerView.setOnItemClickListener(this)
    }

    //默认数据，显示历史搜索和热么搜索
    fun defaultData():List<Model> {
        //构造历史搜索数据
        var historySearch = SearchSuggestionsModel("历史搜索",mHistorySearchData)
        //构造热么搜索数据
        var hotSearchData = mutableListOf<TagModel>()
        for (i in 0..10) {
            hotSearchData.add(TagModel("热门：$i"))
        }
        var hotSearch = SearchSuggestionsModel("热门搜索",hotSearchData)
        if (mHistorySearchData.count() > 0) {
            return  listOf(historySearch,hotSearch)
        } else {
            return listOf(hotSearch)
        }
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
        var list = mutableListOf<Model>()
        for (i in 0..30) {
            if (text.length % 5 == 0) {//模拟建议搜索的时候检测到用户
                list.add(UserModel("用户： $text  $i"))
            } else if(text.length % 5 == 1) {//模拟建议搜索的时候检测到群
                list.add(GroupModel("群组： $text  $i"))
            } else if(text.length % 5 == 2) {
                list.add(PictureModel("图片 $text  $i"))
            } else if(text.length % 5 == 3) {
                list.add(PostModel("帖子内容： $text  $i"))
            }
            //建议的内容数据太少，多加一些😄
            list.add(AdviceModel("搜索建议： $text  $i+1"))
            list.add(AdviceModel("搜索建议： $text  $i+2"))
            list.add(AdviceModel("搜索建议： $text  $i+3"))
            list.add(AdviceModel("搜索建议： $text  $i+4"))
            list.add(AdviceModel("搜索建议： $text  $i+5"))
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

    /**
     * 通过关键字返回搜索结果
     */
    fun getSearchResults(keyword:String):TitleBarModel{
        mHistorySearchData.add(TagModel(keyword))
        var list = mutableListOf<TitleBarItemModel>()//搜索结果分类的标题
        var searchResults = mutableListOf<SearchResultModel>()//搜索分类结果
        val titles = listOf("帖子", "用户","群聊","图片","帖子1", "用户1","群聊1","图片1")
        var j = 0
        for (title in titles) {
            //构造横向滚动的标题
            list.add(TitleBarItemModel(title))
            //构造搜索结果数据
            var searchResultItemModels = mutableListOf<Model>()
            for (i in 0..100) {
                if (j == 0 || j == 4) {
                    searchResultItemModels.add(PostModel("帖子内容： $keyword  $i"))
                } else if(j == 1 || j == 5) {
                    searchResultItemModels.add(UserModel("用户： $keyword  $i"))
                } else if(j == 2 || j == 6) {
                    searchResultItemModels.add(GroupModel("群： $keyword  $i"))
                } else if(j == 3 || j == 7) {
                    searchResultItemModels.add(PictureModel("图片 $keyword  $i"))
                }
            }
            searchResults.add(SearchResultModel(searchResultItemModels))
            j+=1
        }

        //构造综合显示数据,从每一个分类中获取3个用来显示在综合中
        var comprehensiveData = mutableListOf<Model>()
        for (m in 0..(searchResults.count()-1)) {
            comprehensiveData.add(HeaderTitleModel("${titles[m]}"))
            comprehensiveData.add(searchResults[m].resultModels[0])
            comprehensiveData.add(searchResults[m].resultModels[1])
            comprehensiveData.add(searchResults[m].resultModels[2])
            comprehensiveData.add(FooterTitleModel("查看${searchResults[m].resultModels.count()}个搜索结果(${titles[m]})"))
        }
        val comprehensiveSearchResultModel = SearchResultModel(comprehensiveData)
        searchResults.add(0,comprehensiveSearchResultModel)
        list.add(0,TitleBarItemModel("综合"))//添加一个综合类的tab
        return TitleBarModel(list,searchResults)
    }

    override fun onEventTransmissionListener(target: Any?, params: Any?, eventId: Int, callBack: EventTransmissionListener.CallBack?): Any? {
        if (target is SearchSuggestionsViewHolder) {
            return clickHistoryOrHotTag(target,params,eventId,callBack)
        } else if (target is SearchResultViewHolder) {
            return handleSearchResultViewHolder(target,params,eventId,callBack)
        }
        return null
    }

    /**
     * 处理点击搜索结果item
     */
    fun handleSearchResultViewHolder(target: Any?, params: Any?, eventId: Int, callBack: EventTransmissionListener.CallBack?): Any? {
        if (eventId == 1) {//搜索结果ItemClick
            getContext().longToast("搜索结果点击事件")
        }
        return null
    }

    //处理点击历史搜索，热门搜索Item
    fun clickHistoryOrHotTag(target: Any?, params: Any?, eventId: Int, callBack: EventTransmissionListener.CallBack?) : Any?{
        var tag:TagModel = params as TagModel
        mEditText.setText(tag.name)
        mRecyclerView.models = listOf(getSearchResults(tag.name))
        mRecyclerView.notifyDataSetChanged()
        return null
    }

    override fun onItemClick(parent: RecyclerView?, view: View?, position: Int?, model: Model?) {
        if (parent == mRecyclerView) {
            if (model is AdviceModel) {//搜索建议
                val adviceModel:AdviceModel = model
                mEditText.setText(adviceModel.title)
                mRecyclerView.models = listOf(getSearchResults(adviceModel.title))
                mRecyclerView.notifyDataSetChanged()
            } else if(model is UserModel) {//点击用户
                var userModel:UserModel = model
                getContext().longToast("用户：${userModel.name}")
            } else if(model is PostModel) {//点击用户
                var postModel:PostModel = model
                getContext().longToast("帖子：${postModel.text}")
            } else if(model is GroupModel) {//点击用户
                var groupModel:GroupModel = model
                getContext().longToast("群组：${groupModel.name}")
            } else if(model is PictureModel) {//点击用户
                var pictureModel:PictureModel = model
                getContext().longToast("图片：${pictureModel.text}")
            } else if(model is SearchResultItemModel){
                var searchResultItemModel:SearchResultItemModel = model
                getContext().longToast("图片：${searchResultItemModel.title}")
            }
        }
    }

}
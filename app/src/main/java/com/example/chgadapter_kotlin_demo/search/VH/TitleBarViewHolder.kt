package com.example.chgadapter_kotlin_demo.search.VH

import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.chg.adapter.*
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.search.model.TitleBarModel

class TitleBarViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<TitleBarModel>(itemView, eventTransmissionListener, parent), Adapter.OnItemClickListener{

    private lateinit var mRecyclerView:RecyclerView
    private lateinit var mResultsRecyclerView:RecyclerView
    //记录搜索结果左右滚动的位置
    private var mResultsRecyclerViewScrollTotal_dx = 0

    override fun onCreated() {
        mRecyclerView = findViewById(R.id.recyclerView)
        mResultsRecyclerView = findViewById(R.id.resultsRecyclerView)
        mResultsRecyclerView.eventTransmissionListener = getEventTransmissionListener()
        mRecyclerView.setOnItemClickListener(this)

        //监听搜索结果左右滚动，以更新顶部的tab
        mResultsRecyclerView.addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == 0) {
                    val displayMetrics: DisplayMetrics = getContext().getResources().getDisplayMetrics()
                    val page = mResultsRecyclerViewScrollTotal_dx/ displayMetrics.widthPixels
                    getModel()?.currentPosition = page
                    mRecyclerView.scrollToPosition(page)
                    mRecyclerView.notifyDataSetChanged()
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                mResultsRecyclerViewScrollTotal_dx += dx
            }
        })
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(mResultsRecyclerView)
    }

    override fun onBindViewHolder(model: TitleBarModel?) {
        super.onBindViewHolder(model)
        //设置标题数据
        mRecyclerView.models = model?.barItems
        mRecyclerView.customData = model
        mRecyclerView.notifyDataSetChanged()
        //设置搜索结果数据
        mResultsRecyclerView.models = model?.searchResults
        mResultsRecyclerView.notifyDataSetChanged()
        //通过position 设置应该显示的页
        mResultsRecyclerViewScrollTotal_dx = model?.currentPosition!! * getContext().getResources().getDisplayMetrics().widthPixels
        mRecyclerView.scrollToPosition(model?.currentPosition!!)
        mResultsRecyclerView.scrollToPosition(model?.currentPosition!!)
    }

    override fun onItemClick(parent: RecyclerView?, view: View?, position: Int?, model: Model?) {
        if (parent == mRecyclerView) {//顶部tabItem被点击
            mResultsRecyclerViewScrollTotal_dx = position!! * getContext().getResources().getDisplayMetrics().widthPixels
            getModel()?.currentPosition = position!!
            mRecyclerView.scrollToPosition(position)
            mRecyclerView.notifyDataSetChanged()
            mResultsRecyclerView.scrollToPosition(position)
        }
    }


}
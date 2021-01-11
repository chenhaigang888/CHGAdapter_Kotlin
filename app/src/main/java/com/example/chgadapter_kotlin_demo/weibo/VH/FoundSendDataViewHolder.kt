package com.example.chgadapter_kotlin_demo.weibo.VH

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.chg.adapter.base.EventTransmissionListener
import com.chg.adapter.base.Model
import com.chg.adapter.recyclerView.ViewHolder
import com.chg.adapter.extension.customData
import com.chg.adapter.extension.eventTransmissionListener
import com.chg.adapter.extension.models
import com.chg.adapter.extension.notifyDataSetChanged
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.weibo.model.FoundSendData
import com.example.chgadapter_kotlin_demo.weibo.model.FoundUser

class FoundSendDataViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<FoundSendData>(itemView, eventTransmissionListener, parent) {
    private lateinit var content: TextView
    private lateinit var chgRecyclerView: RecyclerView
    private lateinit var headImageView: ImageView
    private lateinit var nickname: TextView
    private lateinit var remark: TextView
    private lateinit var browses: TextView

    private lateinit var imageView1: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var imageView3: ImageView

    override fun onCreated() {
        content = findViewById(R.id.content)
        chgRecyclerView = findViewById(R.id.chgRecycleView)
        headImageView = findViewById(R.id.headImageView)
        nickname = findViewById(R.id.nickname)
        remark = findViewById(R.id.remark)
        browses = findViewById(R.id.browses)
        imageView1 = findViewById(R.id.imageView1)
        imageView2 = findViewById(R.id.imageView2)
        imageView3 = findViewById(R.id.imageView3)
    }

    override fun onBindViewHolder(model: FoundSendData?) {
        super.onBindViewHolder(model)
        val foundSendData = model
        content.text = foundSendData?.content?.content ?: ""
        val sources: List<*>? = foundSendData?.content?.source
        if (sources != null) {
            if (sources.size == 1) {
                chgRecyclerView.layoutManager = GridLayoutManager(getContext(), 1)
            } else if (sources.size == 2) {
                chgRecyclerView.layoutManager = GridLayoutManager(getContext(), 2)
            } else {
                chgRecyclerView.layoutManager = GridLayoutManager(getContext(), 3)
            }
        }

        chgRecyclerView.models = sources as List<Model>?
        chgRecyclerView.eventTransmissionListener = getEventTransmissionListener()
        chgRecyclerView.customData = foundSendData
        chgRecyclerView.notifyDataSetChanged()
        val url = getUrl(foundSendData?.user?.avatar, 24)
        headImageView.tag = url
        displayImageCenter(headImageView, url, getContext(), R.drawable.lei_da, true)
        nickname.text = foundSendData?.user?.finalShowName ?: ""
        remark.text = foundSendData?.user?.exts ?: ""
        browses.text = foundSendData?.content?.browses.toString() + "人看过"
        if (foundSendData != null) {
            setLikesView(foundSendData)
        }
    }

    override fun onViewDetachedFromWindow() {
        super.onViewDetachedFromWindow()
        Glide.with(getContext()).clear(headImageView)
        headImageView.setImageResource(R.drawable.default_pic)
        Glide.with(getContext()).clear(imageView1)
        imageView1.setImageResource(R.drawable.default_pic)
        Glide.with(getContext()).clear(imageView2)
        imageView2.setImageResource(R.drawable.default_pic)
        Glide.with(getContext()).clear(imageView3)
        imageView3.setImageResource(R.drawable.default_pic)
    }

    fun setLikesView(foundSendData: FoundSendData) {
        val likes: List<*>? = foundSendData.likes
        imageView1.visibility = View.GONE
        imageView2.visibility = View.GONE
        imageView3.visibility = View.GONE
        if (likes != null) {
            if (likes.size == 1) {
                imageView1.visibility = View.GONE
                imageView2.visibility = View.GONE
                imageView3.visibility = View.VISIBLE
                val layoutParams = imageView3.layoutParams as LinearLayout.LayoutParams
                layoutParams.setMargins(0, 0, 0, 0) //4个参数按顺序分别是左上右下
                imageView3.layoutParams = layoutParams
                val user1 = likes[0] as FoundUser
                val url3 = getUrl(user1.avatar, 24)
                imageView3.tag = url3
                displayImageCenter(imageView3, url3, getContext(), R.drawable.default_pic, true)
            } else if (likes.size == 2) {
                imageView1.visibility = View.GONE
                imageView2.visibility = View.VISIBLE
                imageView3.visibility = View.VISIBLE
                val layoutParams2 = imageView2.layoutParams as LinearLayout.LayoutParams
                layoutParams2.setMargins(dp2px(0f), 0, 0, 0) //4个参数按顺序分别是左上右下
                imageView2.layoutParams = layoutParams2
                val layoutParams3 = imageView3.layoutParams as LinearLayout.LayoutParams
                layoutParams3.setMargins(dp2px(-10f), 0, 0, 0) //4个参数按顺序分别是左上右下
                imageView3.layoutParams = layoutParams3
                val user1 = likes[0] as FoundUser
                val user2 = likes[1] as FoundUser
                val url3 = getUrl(user1.avatar, 24)
                imageView3.tag = url3
                val url2 = getUrl(user1.avatar, 24)
                imageView2.tag = url3
                displayImageCenter(imageView3, url3, getContext(), R.drawable.default_pic, true)
                displayImageCenter(imageView2, url2, getContext(), R.drawable.default_pic, true)
            } else if (likes.size >= 3) {
                imageView1.visibility = View.VISIBLE
                imageView2.visibility = View.VISIBLE
                imageView3.visibility = View.VISIBLE
                val layoutParams1 = imageView1.layoutParams as LinearLayout.LayoutParams
                layoutParams1.setMargins(0, 0, 0, 0) //4个参数按顺序分别是左上右下
                imageView1.layoutParams = layoutParams1
                val layoutParams2 = imageView2.layoutParams as LinearLayout.LayoutParams
                layoutParams2.setMargins(dp2px(-10f), 0, 0, 0) //4个参数按顺序分别是左上右下
                imageView2.layoutParams = layoutParams2
                val layoutParams3 = imageView3.layoutParams as LinearLayout.LayoutParams
                layoutParams3.setMargins(dp2px(-10f), 0, 0, 0) //4个参数按顺序分别是左上右下
                imageView3.layoutParams = layoutParams3
                val user1 = likes[0] as FoundUser
                val user2 = likes[1] as FoundUser
                val user3 = likes[2] as FoundUser
                val url3 = getUrl(user3.avatar, 24)
                imageView3.tag = url3
                val url2 = getUrl(user2.avatar, 24)
                imageView2.tag = url2
                val url1 = getUrl(user1.avatar, 24)
                imageView1.tag = url1
                displayImageCenter(imageView3, url3, getContext(), R.drawable.default_pic, true)
                displayImageCenter(imageView2, url2, getContext(), R.drawable.default_pic, true)
                displayImageCenter(imageView1, url1, getContext(), R.drawable.default_pic, true)
            }
        }
    }


    fun getUrl(url: String?, dp: Int): String {
        return url + "?x-oss-process=image/resize,w_" + dp2px(dp.toFloat()) + "/quality,q_50"
    }

    fun dp2px(dp: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            getContext().resources.displayMetrics
        )
            .toInt()
    }


    fun displayImageCenter(
        imageview: ImageView?,
        url: String,
        context: Context?,
        defultPic: Int,
        isCircleCrop: Boolean
    ) {
        var options: RequestOptions? = null
        options = if (isCircleCrop) {
            RequestOptions.circleCropTransform()
        } else {
            RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE).placeholder(defultPic)
                .error(defultPic).dontAnimate()
        }
        Glide.with(context!!).load(url).apply(options).into(object : SimpleTarget<Drawable?>() {
            override fun onResourceReady(
                resource: Drawable,
                transition: Transition<in Drawable?>?
            ) {
                if (imageview != null) {
                    if (imageview.tag != null) {
                        val urlT = imageview.tag as String
                        if (resource != null && urlT != null && urlT == url) {
                            imageview.setImageDrawable(resource)
                        }
                    }
                }
            }
        })
    }
}
package com.example.chgadapter_kotlin_demo.weibo.VH

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.chg.adapter.base.EventTransmissionListener
import com.chg.adapter.base.Model
import com.chg.adapter.recyclerView.ViewHolder
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.weibo.model.FoundSendData
import com.example.chgadapter_kotlin_demo.weibo.model.Source
import java.util.*

class SourceViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<Source>(itemView, eventTransmissionListener, parent) {

    private lateinit var imageView: ImageView


    override fun onCreated() {
        imageView = findViewById<ImageView>(R.id.imageView)
        itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                Log.i("chgLog","点击图片")
                if (getCustomData() != null) {
                    val foundSendData = getCustomData() as FoundSendData?
                    var map = mutableMapOf<Any,Any>()
                    map["position"] = adapterPosition
                    map["sources"] = foundSendData!!.content.source
                    eventTransmission(this@SourceViewHolder,map,0,null)
                } else {
                    eventTransmission(this@SourceViewHolder,getModel(),0,null)
                }
            }
        })
    }

    override fun onBindViewHolder(model: Source?) {
        super.onBindViewHolder(model)
        val url = getUrl(getModel(), getPicWidth(getModel()))
        if (model != null) {
            model.handleUrl = url
        }
    }

    override fun onViewAttachedToWindow() {
        super.onViewAttachedToWindow()
        val url = getModel()?.handleUrl
        imageView!!.tag = url
        displayImageCente2(imageView, url, getContext(), R.drawable.default_pic, false)
    }

    fun displayImageCente2(
        imageview: ImageView?,
        url: String?,
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

    override fun onViewDetachedFromWindow() {
        super.onViewDetachedFromWindow()
        Glide.with(getContext()).clear(imageView!!)
        imageView!!.setImageResource(R.drawable.default_pic)
    }

    //获取图片url
    fun getUrl(model: Model?, imageWidth: Int): String? {
        var url: String? = null
        if (getCustomData() != null) {
            val foundSendData = getCustomData() as FoundSendData?
            val type = foundSendData!!.content.type
            if (type == 2) { //图片
                val source = model as Source?
                url = source!!.url + "?x-oss-process=image/resize,w_" + imageWidth + "/quality,q_50"
            } else if (type == 3) { //视频
                url = foundSendData.content.cover
            }
        } else {
            val source = model as Source?
            val sourceType = source!!.sourceType
            url = if (sourceType == 4) { //视频
                source.url
            } else {
                source.url + "?x-oss-process=image/resize,w_" + imageWidth + "/quality,q_50"
            }
        }
        Log.i("chgLog","url:$url")
        return url
    }

    //获取图片的宽高
    fun getPicWidth(model: Model?): Int {
        var imageWidth = 0
        val viewWidth = getContext().resources.displayMetrics.widthPixels
        val viewHeight = getContext().resources.displayMetrics.heightPixels
        val source = model as Source?
        Log.i(
            "chg",
            "source.getHeight():" + source!!.height + "   source.getWidth()" + source.width + "    url:" + source.url
        )
        if (getCustomData() != null) {
            val foundSendData = getCustomData() as FoundSendData?
            if (foundSendData!!.content.source != null && foundSendData.content.source.size > 0) {
                val size = foundSendData.content.source.size
                if (size == 1) {
                    if (source.height == 0f || source.width == 0f) {
                        imageView!!.layoutParams = LinearLayout.LayoutParams(viewWidth, viewWidth)
                        imageWidth = viewWidth
                    } else {
                        var height = (source.height / source.width * viewWidth).toInt()
                        height = if (height >= viewHeight) (viewHeight * 0.7).toInt() else height
                        imageView!!.layoutParams = LinearLayout.LayoutParams(viewWidth, height)
                        imageWidth = viewWidth
                    }
                } else if (size == 2) {
                    imageView!!.layoutParams =
                        LinearLayout.LayoutParams(viewWidth / 2, viewWidth / 2)
                    imageWidth = viewWidth / 2
                } else {
                    imageView!!.layoutParams =
                        LinearLayout.LayoutParams(viewWidth / 3, viewWidth / 3)
                    imageWidth = viewWidth / 3
                }
            }
        } else {
            imageView!!.layoutParams = LinearLayout.LayoutParams(viewWidth, viewHeight)
            imageWidth = viewWidth
        }
        imageWidth = if (imageWidth > 900) 900 else imageWidth
        return imageWidth
    }
}
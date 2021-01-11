package com.example.chgadapter_kotlin_demo.weibo.VH

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.chg.adapter.base.EventTransmissionListener
import com.chg.adapter.recyclerView.ViewHolder
import com.example.chgadapter_kotlin_demo.R
import com.example.chgadapter_kotlin_demo.weibo.model.FoundUserModel

class FoundUserViewHolder(
    itemView: View,
    eventTransmissionListener: EventTransmissionListener?,
    parent: ViewGroup?
) : ViewHolder<FoundUserModel>(itemView, eventTransmissionListener, parent) {

    private lateinit var imageView:ImageView
    private lateinit var name:TextView

    override fun onCreated() {
        imageView = findViewById(R.id.imageView)
        name = findViewById(R.id.name)

    }

    override fun onBindViewHolder(model: FoundUserModel?) {
        super.onBindViewHolder(model)
        name.text = model?.finalShowName ?: ""
        val url = model?.avatar
        imageView!!.tag = url
        displayImageCente2(imageView, url, getContext(), R.drawable.default_pic, true)
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

}
//package com.example.chgadapter_kotlin_demo.weibo.VH;
//
//
//import android.content.Context;
//import android.graphics.drawable.Drawable;
//import android.util.Log;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//
//import androidx.annotation.NonNull;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.request.RequestOptions;
//import com.bumptech.glide.request.target.SimpleTarget;
//import com.bumptech.glide.request.transition.Transition;
//import com.chg.adapter.EventTransmissionListener;
//import com.chg.adapter.Model;
//import com.chg.adapter.ViewHolder;
//import com.example.chgadapter_kotlin_demo.R;
//import com.example.chgadapter_kotlin_demo.weibo.model.FoundSendData;
//import com.example.chgadapter_kotlin_demo.weibo.model.Source;
//
//import java.util.HashMap;
//
//public class SourceViewHolder extends ViewHolder<Source> {
//
//    private ImageView imageView;
//
//    public SourceViewHolder(@NonNull View itemView, EventTransmissionListener eventTransmissionListener, ViewGroup parent) {
//        super(itemView, eventTransmissionListener, parent);
//        imageView = findViewById(R.id.imageView);
//
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (getCustomData() != null) {
//                    final FoundSendData foundSendData = (FoundSendData) getCustomData();
//                    HashMap map = new HashMap();
//                    map.put("position", getAdapterPosition());
//                    map.put("sources", foundSendData.getContent().getSource());
//                    getEventTransmissionListener().onEventTransmission(this, map, 0, null);
//                } else {
//                    getEventTransmissionListener().onEventTransmission(this, getModel(), 0, null);
//                }
//            }
//        });
//    }
//
//    @Override
//    public void onCreated() {
//
//    }
//
//    @Override
//    public void onBindViewHolder(final Source model) {
//        super.onBindViewHolder(model);
//        String url = getUrl(getModel(), getPicWidth(getModel()));
//        model.setHandleUrl(url);
//    }
//
//    @Override
//    public void onViewAttachedToWindow() {
//        super.onViewAttachedToWindow();
//        Source source = (Source) getModel();
//        String url = source.getHandleUrl();
//        imageView.setTag(url);
//        displayImageCente2(imageView, url, getContext(), R.drawable.default_pic, false);
//    }
//
//    public void displayImageCente2(final ImageView imageview, final String url, Context context, int defultPic, Boolean isCircleCrop) {
//        RequestOptions options = null;
//        if (isCircleCrop) {
//            options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE).placeholder(defultPic).error(defultPic).dontAnimate().circleCropTransform();
//        } else {
//            options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE).placeholder(defultPic).error(defultPic).dontAnimate();
//        }
//
//        Glide.with(context).load(url).apply(options).into(new SimpleTarget<Drawable>() {
//            @Override
//            public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
//                if (imageview != null) {
//                    String urlT = (String) imageview.getTag();
//                    if (drawable != null && urlT != null && urlT.equals(url)) {
//                        imageview.setImageDrawable(drawable);
//                    }
//                }
//            }
//        });
//    }
//
//    @Override
//    public void onViewDetachedFromWindow() {
//        super.onViewDetachedFromWindow();
//        Glide.with(getContext()).clear(imageView);
//        imageView.setImageResource(R.drawable.default_pic);
//    }
//
//    //获取图片url
//    public String getUrl(Model model, int imageWidth) {
//        String url = null;
//        if (getCustomData() != null) {
//            final FoundSendData foundSendData = (FoundSendData) getCustomData();
//            Integer type = foundSendData.getContent().getType();
//            if (type == 2) {//图片
//                Source source = (Source) model;
//                url = source.getUrl() + "?x-oss-process=image/resize,w_" + imageWidth + "/quality,q_50";
//            } else if (type == 3) {//视频
//                url = foundSendData.getContent().getCover();
//            }
//        } else {
//            Source source = (Source) model;
//            int sourceType = source.getSourceType();
//            if (sourceType == 4) {//视频
//                url = source.getUrl();
//            } else {
//                url = source.getUrl() + "?x-oss-process=image/resize,w_" + imageWidth + "/quality,q_50";
//            }
//        }
//
//        return url;
//    }
//
//    //获取图片的宽高
//    public int getPicWidth(Model model) {
//        int imageWidth = 0;
//        int viewWidth = getContext().getResources().getDisplayMetrics().widthPixels;
//        int viewHeight = getContext().getResources().getDisplayMetrics().heightPixels;
//        Source source = (Source) model;
//        Log.i("chg", "source.getHeight():" + source.getHeight() + "   source.getWidth()" + source.getWidth() + "    url:" + source.getUrl());
//        if (getCustomData() != null) {
//            final FoundSendData foundSendData = (FoundSendData) getCustomData();
//            if (foundSendData.getContent().getSource() != null && foundSendData.getContent().getSource().size() > 0) {
//                int size = foundSendData.getContent().getSource().size();
//                if (size == 1) {
//                    if (source.getHeight() == 0 || source.getWidth() == 0) {
//                        imageView.setLayoutParams(new LinearLayout.LayoutParams(viewWidth, viewWidth));
//                        imageWidth = viewWidth;
//                    } else {
//                        int height = (int) (source.getHeight() / source.getWidth() * viewWidth);
//                        height = height >= viewHeight ? (int) (viewHeight * 0.7) : height;
//                        imageView.setLayoutParams(new LinearLayout.LayoutParams(viewWidth, height));
//                        imageWidth = viewWidth;
//                    }
//                } else if (size == 2) {
//                    imageView.setLayoutParams(new LinearLayout.LayoutParams(viewWidth / 2, viewWidth / 2));
//                    imageWidth = viewWidth / 2;
//                } else {
//                    imageView.setLayoutParams(new LinearLayout.LayoutParams(viewWidth / 3, viewWidth / 3));
//                    imageWidth = viewWidth / 3;
//                }
//            }
//        } else {
//            imageView.setLayoutParams(new LinearLayout.LayoutParams(viewWidth, viewHeight));
//            imageWidth = viewWidth;
//        }
//
//        imageWidth = imageWidth > 900 ? 900 : imageWidth;
//        return (int) imageWidth;
//    }
//}

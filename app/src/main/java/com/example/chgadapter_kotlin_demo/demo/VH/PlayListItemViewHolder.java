package com.example.chgadapter_kotlin_demo.demo.VH;


import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chg.adapter.EventTransmissionListener;
import com.chg.adapter.ViewHolder;
import com.example.chgadapter_kotlin_demo.R;
import com.example.chgadapter_kotlin_demo.demo.model.PlayListItemModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayListItemViewHolder extends ViewHolder<PlayListItemModel> {
    private ImageView icon;
    private TextView name;
    private TextView songer;
    private TextView love;
    private TextView collection;
    private TextView play;

    public PlayListItemViewHolder(@NotNull View itemView, @Nullable EventTransmissionListener eventTransmissionListener, @Nullable ViewGroup parent) {
        super(itemView, eventTransmissionListener, parent);
    }


    @Override
    public void onBindViewHolder(@Nullable PlayListItemModel model) {
        super.onBindViewHolder(model);
        name.setText(getModel().getName());
        songer.setText(getModel().getSonger());
        // 为了节约时间 以下状态使用文字代替图片。
        love.setText(getModel().isLove() ? "喜欢":"未喜欢");
        collection.setText(getModel().isCollection() ? "收藏":"未收藏");
        play.setText(getModel().isPlay() ? "播放":"未播放");
    }

    @Override
    public void onCreated() {
        icon = (ImageView) findViewById(R.id.icon);
        name = (TextView) findViewById(R.id.name);
        songer = (TextView) findViewById(R.id.songer);
        love = (TextView) findViewById(R.id.love);
        collection = (TextView) findViewById(R.id.collection);
        play = (TextView) findViewById(R.id.play);

        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //内容处理点击事件
                getModel().setLove(!getModel().isLove());//这里把状态存储在数据中，可以根据需求将状态存储在provider中的自定义数据中。具体使用方式可以查看CustomData的使用
                notifyCurrentItemChanged();
                Log.i("chg","love被点击");
            }
        });


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将事件传递到Slice中然后同步返回结果
                Object o = eventTransmission(PlayListItemViewHolder.this,getModel().isPlay(),1,null);
                boolean play = (boolean) o;
                getModel().setPlay(play);//这里把状态存储在数据中，可以根据需求将状态存储在provider中的自定义数据中。具体使用方式可以查看CustomData的使用
                notifyCurrentItemChanged();
            }
        });

        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //此次演示异步返回数据更新状态
                eventTransmission(PlayListItemViewHolder.this, getModel().isCollection(), 2, new EventTransmissionListener.CallBack() {
                    @Override
                    public Object callBack(Object object) {
                        boolean collection = (boolean) object;//这里activity中应该返回boolean类型以适合这里需要的数据
                        getModel().setCollection(collection);//这里把状态存储在数据中，可以根据需求将状态存储在provider中的自定义数据中。具体使用方式可以查看CustomData的使用
                        notifyCurrentItemChanged();
                        return null;
                    }
                });
            }
        });
    }
}

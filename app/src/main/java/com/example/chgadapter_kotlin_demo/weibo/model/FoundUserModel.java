package com.example.chgadapter_kotlin_demo.weibo.model;

import com.chg.adapter.Model;
import com.example.chgadapter_kotlin_demo.R;
import com.example.chgadapter_kotlin_demo.weibo.VH.FoundUserViewHolder;

import org.jetbrains.annotations.NotNull;

public class FoundUserModel implements Model {

/////
//    @property (nonatomic, copy) NSString * otherId;
///// 昵称
//    @property(nonatomic, copy) NSString * nickName;
///// 头像
//    @property(nonatomic, copy) NSString * avatar;
///// 用户唯一id
//    @property(nonatomic, copy) NSString * userId;
//    /// 用户性别 1男2女3其他
//    @property(nonatomic, assign) NSInteger sex;
/////最终显示的名称
//    @property (nonatomic, copy) NSString * finalShowName;

    private String finalShowName;
    private String avatar;

    public String getFinalShowName() {
        return finalShowName;
    }

    public void setFinalShowName(String finalShowName) {
        this.finalShowName = finalShowName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public int getResource(int position) {
        return R.layout.recommend_friend_item;
    }

    @NotNull
    @Override
    public Class<?> getHolderClass(int position) {
        return FoundUserViewHolder.class;
    }
}

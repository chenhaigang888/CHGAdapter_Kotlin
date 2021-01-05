package com.example.chgadapter_kotlin_demo.weibo.model;

public class FoundUser {

//    ///
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

    private String otherId;

    private String nickName;
    private String avatar;
    private String userId;
    private Integer sex;
    private String finalShowName;
    private String exts;

    public String getExts() {
        return exts;
    }

    public void setExts(String exts) {
        this.exts = exts;
    }

    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getFinalShowName() {
        return finalShowName;
    }

    public void setFinalShowName(String finalShowName) {
        this.finalShowName = finalShowName;
    }
}

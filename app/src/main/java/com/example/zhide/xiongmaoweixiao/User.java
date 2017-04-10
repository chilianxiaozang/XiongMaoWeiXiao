package com.example.zhide.xiongmaoweixiao;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by zhide on 2017/4/10.
 */

public class User extends BmobUser {
    private String flag;                  //用于确定是管理端或是用户端的标志
    private BmobFile userimage;          //用户头像
    private String userdesignation;     //用户的等级标志
    private Integer usermoney;     //用户的金钱数目
    private Integer userweibi;     //用户的微币
    private Integer userintegral;  //用户的积分
    private String userbitmapurl;

    public BmobFile getUserimage() {
        return userimage;
    }

    public Integer getUserintegral() {
        return userintegral;
    }

    public void setUserbitmapurl(String userbitmapurl) {
        this.userbitmapurl = userbitmapurl;
    }

    public void setUserimage(BmobFile userimage) {
        this.userimage = userimage;
    }

    public void setUserintegral(Integer userintegral) {
        this.userintegral = userintegral;
    }

    public String getUserbitmapurl() {
        return userbitmapurl;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setUserImage(BmobFile userimage) {
        this.userimage = userimage;
    }

    public void setUserdesignation(String userdesignation) {
        this.userdesignation = userdesignation;
    }

    public void setUsermoney(Integer usermoney) {
        this.usermoney = usermoney;
    }

    public void setUserweibi(Integer userweibi) {
        this.userweibi = userweibi;
    }

    public void setUserIntegral(Integer userintegral) {
        this.userintegral = userintegral;
    }

    public Integer getUserIntegral() {
        return userintegral;
    }

    public String getFlag() {
        return flag;
    }

    public BmobFile getUserImage() {
        return userimage;
    }

    public String getUserdesignation() {
        return userdesignation;
    }

    public Integer getUsermoney() {
        return usermoney;
    }

    public Integer getUserweibi() {
        return userweibi;
    }
    public  User(String userdesignation ,BmobFile userimage , Integer userintegral
            , Integer usermoney, Integer userweibi){
        this.userdesignation = userdesignation;
        this.userimage = userimage;
        this.userintegral = userintegral;
        this.usermoney = usermoney;
        this.userweibi = userweibi ;
    }
    public User(){}

}

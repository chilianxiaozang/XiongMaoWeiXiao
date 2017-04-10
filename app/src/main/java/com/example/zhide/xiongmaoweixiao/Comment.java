package com.example.zhide.xiongmaoweixiao;

import android.graphics.Bitmap;
import android.os.Parcelable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by zhide on 2017/4/10.
 */

public class Comment extends BmobObject {
    private String content;//评论内容
    private BmobFile image;//帖子用户头像图片
    private User user;//评论的用户，Pointer类型，一对一关系

    private Post post; //所评论的帖子，这里体现的是一对多的关系，一个评论只能属于一个微博
    private String commenttime;
    private Bitmap bitmap;
    private Integer location;

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public String getCommenttime() {
        return commenttime;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setCommenttime(String commenttime) {
        this.commenttime = commenttime;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

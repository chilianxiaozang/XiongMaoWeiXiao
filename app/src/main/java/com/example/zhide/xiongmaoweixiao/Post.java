package com.example.zhide.xiongmaoweixiao;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by zhide on 2017/4/10.
 */

public class Post extends BmobObject implements Parcelable{
    private String title;//帖子标题

    private String content;// 帖子内容

    private User author;//帖子的发布者，这里体现的是一对一的关系，该帖子属于某个用户

    private BmobFile image;//帖子用户头像图片

    private BmobRelation likes;//多对多关系：用于存储喜欢该帖子的所有用户

    private Bitmap bitmap;        //存储下载图片的bitmap

    private String bitmapurl;

    private String authorname;

    private String posttime;            //帖子发表的时间

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public BmobFile getImage() {
        return image;
    }

    public BmobRelation getLikes() {
        return likes;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImage(BmobFile image) {
        this.image = image;
    }

    public void setLikes(BmobRelation likes) {
        this.likes = likes;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getBitmapurl() {
        return bitmapurl;
    }

    public String getPosttime() {
        return posttime;
    }

    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }

    public void setBitmapurl(String bitmapurl) {
        this.bitmapurl = bitmapurl;
    }
    Post (String author,String title ,String content ,Bitmap bitmap){
        this.author.setUsername(author);
        this.title = title;
        this.content = content;
        this.bitmap = bitmap;
    }
    Post (){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(authorname);
        dest.writeString(posttime);
        dest.writeString(bitmapurl);
        dest.writeString( id =getObjectId());
    }
    public static final Parcelable.Creator<Post> CREATOR = new Parcelable.Creator<Post>()
    {
        public Post createFromParcel(Parcel in)
        {
            return new Post(in);
        }

        public Post[] newArray(int size)
        {
            return new Post[size];
        }
    };

    private Post(Parcel in)
    {
        title = in.readString();
        content = in.readString();
        authorname = in.readString();
        posttime = in.readString();
        bitmapurl = in.readString();
        id = in.readString();
    }
}

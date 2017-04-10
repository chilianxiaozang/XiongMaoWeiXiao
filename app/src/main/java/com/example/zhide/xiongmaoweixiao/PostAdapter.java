package com.example.zhide.xiongmaoweixiao;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhide on 2017/4/10.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyPostViewHolder> {
    List<Post> postList;
    private Context mContext;
    private LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    PostAdapter(Context context,List<Post> postList){
        this.mContext = context;
        this.postList = postList;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public MyPostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = inflater.inflate(R.layout.conment_item,parent,false);
//        MyPostViewHolder holder =  new MyPostViewHolder(view);
//        return holder;
        return null;
    }

    @Override
    public void onBindViewHolder(MyPostViewHolder holder, final int position) {
        Post post = postList.get(position);
        holder.postAuthor.setText(post.getAuthor().getUsername());
        holder.postTitle.setText(post.getTitle());
        holder.postContent.setText(post.getContent());
        holder.postTime.setText(post.getPosttime());
        holder.postImage.setImageBitmap(post.getBitmap());
        if(mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onClick(position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class MyPostViewHolder extends RecyclerView.ViewHolder{
        TextView postTitle;
        TextView postContent;
        TextView postAuthor;
        TextView postTime;
        ImageView postImage;
        public MyPostViewHolder(View itemView) {
            super(itemView);
//            postTitle = (TextView) itemView.findViewById(R.id.conment_texttitle);
//            postContent = (TextView) itemView.findViewById(R.id.conment_textcontent);
//            postAuthor = (TextView) itemView.findViewById(R.id.conment_username);
//            postTime = (TextView) itemView.findViewById(R.id.conment_time);
//            postImage = (ImageView) itemView.findViewById(R.id.conment_userimage);
        }
    }
    public interface OnItemClickListener{
        void onClick( int position);
        void onLongClick( int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this.mOnItemClickListener=onItemClickListener;
    }
    public void add(Post post){
        postList.add(post);
        notifyDataSetChanged();
    }
    public void addAll(List<Post> postArrayList){
        postList.addAll(postArrayList);
        notifyDataSetChanged();
    }
    public void remove(List<Post> postArrayList){
        postList.removeAll(postArrayList);
        notifyDataSetChanged();
    }
    public List getList(){

        return postList;
    }
    public Post getPost(int position){
        return postList.get(position);
    }
}

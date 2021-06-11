package com.example.yscontact.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yscontact.R;
import com.example.yscontact.model.CommentsInfo;
import com.example.yscontact.model.ForumInfo;

import java.util.ArrayList;

public class ForumCommentAdapter extends BaseAdapter {

    private ArrayList<CommentsInfo> commentsInfoList;
    private TextView userName;
    private TextView commentContent;
    private Context mContext;

    public ForumCommentAdapter(Context context,ArrayList<CommentsInfo> commentsInfoList){
        this.mContext =context;
        this.commentsInfoList = commentsInfoList;
    }

    private void initView(View view){
        userName = view.findViewById(R.id.et_comment_user_name);
        commentContent = view.findViewById(R.id.et_comment_content);
    }

    @Override
    public int getCount() {
        if(commentsInfoList ==null){
            return  0;
        }else{
            return commentsInfoList.size();
        }

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_comment,null);
        initView(view);
        userName.setText(commentsInfoList.get(position).getUser());
        commentContent.setText(commentsInfoList.get(position).getComment());
        return view;
    }
}

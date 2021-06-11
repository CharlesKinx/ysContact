package com.example.yscontact.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yscontact.R;
import com.example.yscontact.model.ForumInfo;

public class ForumCommentAdapter extends BaseAdapter {

    private TextView userName;
    private TextView commentContent;
    private Context mContext;
    private ForumInfo forumInfo;

    public ForumCommentAdapter(Context context,ForumInfo forumInfo){
        this.mContext =context;
        this.forumInfo = forumInfo;
    }

    private void initView(View view){
        userName = view.findViewById(R.id.et_comment_user_name);
        commentContent = view.findViewById(R.id.et_comment_content);
    }

    @Override
    public int getCount() {
        return forumInfo.getCommentsInfoList().size();
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
        View view = inflater.inflate(R.layout.items,null);
        initView(view);
        userName.setText(forumInfo.getCommentsInfoList().get(position).getUser().getUserName());
        commentContent.setText(forumInfo.getCommentsInfoList().get(position).getComment());
        return view;
    }
}

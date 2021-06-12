package com.example.yscontact.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.yscontact.R;
import com.example.yscontact.model.ForumInfo;

import java.util.ArrayList;

public class MyForumListAdapter extends BaseAdapter implements View.OnClickListener {

    private ArrayList<ForumInfo> forumInfoArrayList;
    private Context mContext;
    private InnerItemOnclickListener mListener;
    private Button delete;
    private TextView myForumTitle;
    private TextView userName;
    private TextView time;


    public MyForumListAdapter(Context context,ArrayList<ForumInfo> arrayList){
        this.mContext = context;
        this.forumInfoArrayList = arrayList;
    }

    private void initView(View view){
        delete = view.findViewById(R.id.delete_my_forum);
        myForumTitle = view.findViewById(R.id.my_forum_title);
        userName = view.findViewById(R.id.my_forum_et_name);
        time = view.findViewById(R.id.my_forum_time);
    }
    @Override
    public int getCount() {
        if(forumInfoArrayList == null){
            return 0;
        }else{
            return forumInfoArrayList.size();
        }
    }

    @Override
    public Object getItem(int position) {
        if(forumInfoArrayList == null){
            return null;
        }else{
            return forumInfoArrayList.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_forum,null);
        initView(view);

        delete.setOnClickListener(this);
        delete.setTag(position);
        myForumTitle.setText(forumInfoArrayList.get(position).getTitle());
        time.setText(forumInfoArrayList.get(position).getTime());
        userName.setText(forumInfoArrayList.get(position).getUserInfo().getUserName());

        return view;
    }


    public interface InnerItemOnclickListener {
        void itemClick(View v);
    }

    public void setOnInnerItemOnClickListener(InnerItemOnclickListener listener){
        this.mListener=listener;
    }

    @Override
    public void onClick(View v) {
        mListener.itemClick(v);
    }


}

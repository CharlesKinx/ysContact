package com.example.yscontact.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.yscontact.R;
import com.example.yscontact.activity.HomePageActivity;
import com.example.yscontact.activity.LoginActivity;
import com.example.yscontact.model.ForumInfo;
import com.example.yscontact.model.UserInfo;

import java.util.ArrayList;


public class ForumAdapter extends BaseAdapter {


    private TextView itemEtTitle;
    private TextView itemEtUserName;
    private TextView itemEtComments;
    private Context mContext;
    private ArrayList<ForumInfo> forumInfoArrayList;


    private void initView(View view){
        itemEtTitle = view.findViewById(R.id.item_et_title);
        itemEtUserName = view.findViewById(R.id.item_et_name);
        itemEtComments = view.findViewById(R.id.item_comment_num);
    }
    public  ForumAdapter(Context context,ArrayList<ForumInfo> forumInfos){
        this.mContext = context;
        this.forumInfoArrayList = forumInfos;
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)  {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.items,null);
        initView(view);
        itemEtTitle.setText(forumInfoArrayList.get(position).getTitle());
        itemEtComments.setText(String.valueOf(forumInfoArrayList.get(position).getTime()));
        itemEtUserName.setText(forumInfoArrayList.get(position).getUserInfo().getUserName());
        return view;
    }
}

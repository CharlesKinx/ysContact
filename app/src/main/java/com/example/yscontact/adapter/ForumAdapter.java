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


public class ForumAdapter extends BaseAdapter {


    private TextView itemEtTitle;
    private TextView itemEtUserName;
    private TextView itemEtComments;


    private Context mContext;

    private void initView(View view){
        itemEtTitle = view.findViewById(R.id.item_et_title);
        itemEtUserName = view.findViewById(R.id.item_et_name);
        itemEtComments = view.findViewById(R.id.item_comment_num);
    }
    public ForumAdapter(Context context){
        this.mContext = context;

    }


    @Override
    public int getCount() {
        return HomePageActivity.forumInfoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return HomePageActivity.forumInfoArrayList.get(position);
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
        itemEtTitle.setText(HomePageActivity.forumInfoArrayList.get(position).getTitle());
        itemEtComments.setText(String.valueOf(HomePageActivity.forumInfoArrayList.get(position).getComments()));
        itemEtUserName.setText(HomePageActivity.forumInfoArrayList.get(position).getUserInfo().getUserName());
        return view;
    }
}

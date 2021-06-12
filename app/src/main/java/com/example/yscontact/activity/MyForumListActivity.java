package com.example.yscontact.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yscontact.R;
import com.example.yscontact.adapter.MyForumListAdapter;
import com.example.yscontact.model.ForumInfo;

import java.util.ArrayList;

public class MyForumListActivity extends AppCompatActivity implements MyForumListAdapter.InnerItemOnclickListener, AdapterView.OnItemClickListener {
    private ListView listView;
    private ArrayList<ForumInfo> arrayList;
    private MyForumListAdapter myForumListAdapter;
    private ArrayList<ForumInfo> getData(){
        ArrayList<ForumInfo> arrayList = new ArrayList<>();
        for(int i=0;i<LoginActivity.forumInfoArrayList.size();i++){
            if(LoginActivity.forumInfoArrayList.get(i).getUserInfo().getUserName()
                    .equals(LoginActivity.userInfo.getUserName())){
                arrayList.add(LoginActivity.forumInfoArrayList.get(i));
            }
        }
        return arrayList;
    }

    private void deleteForum(int forumInfoID){
        for(int i = 0;i<LoginActivity.forumInfoArrayList.size();i++){
            if(forumInfoID == LoginActivity.forumInfoArrayList.get(i).getForumID()){
                LoginActivity.forumInfoArrayList.remove(i);
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myforumlist);
        arrayList = getData();
        myForumListAdapter = new MyForumListAdapter(getApplicationContext(),arrayList);
        listView = findViewById(R.id.my_forum_list);
        myForumListAdapter.setOnInnerItemOnClickListener(this);
        listView.setAdapter(myForumListAdapter);
        listView.setOnItemClickListener(this);
    }


    @Override
    public void itemClick(View v) {
        int position;
        position = (Integer) v.getTag();
        switch (v.getId()){
            case R.id.delete_my_forum:
                deleteForum(arrayList.get(position).getForumID());
                arrayList.remove(position);
                myForumListAdapter.notifyDataSetChanged();
                Intent intent = new Intent(MyForumListActivity.this,HomePageActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
   
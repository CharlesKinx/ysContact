package com.example.yscontact.activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yscontact.R;
import com.example.yscontact.adapter.MyForumListAdapter;
import com.example.yscontact.model.ForumInfo;

import java.util.ArrayList;

public class MyForumListActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<ForumInfo> arrayList;

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myforumlist);
        arrayList = getData();
        MyForumListAdapter forumListAdapter = new MyForumListAdapter(getApplicationContext(),arrayList);
        listView = findViewById(R.id.my_forum_list);
        listView.setAdapter(forumListAdapter);

    }
}
   
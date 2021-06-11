package com.example.yscontact.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yscontact.R;
import com.example.yscontact.model.ForumInfo;

public class ForumContentActivity extends AppCompatActivity {

    private TextView title;
    private TextView userName;
    private TextView commentsNum;
    private TextView content;
    private ListView listView;
    private EditText editComment;
    private Button publishComment;

    private ForumInfo forumInfo;


    private void initView(){
        title = findViewById(R.id.forum_title);
        userName = findViewById(R.id.forum_user_name);
        commentsNum = findViewById(R.id.forum_comment_num);
        content = findViewById(R.id.forum_content);
        listView = findViewById(R.id.comment_list);
        editComment = findViewById(R.id.et_comment);
        publishComment = findViewById(R.id.btn_publish_comment);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forumcontent);
        Intent intent = getIntent();
        forumInfo = (ForumInfo)intent.getSerializableExtra("forumInfo");
        initView();
        title.setText(forumInfo.getTitle());
        userName.setText(forumInfo.getUserInfo().getUserName());
        commentsNum.setText(String.valueOf(forumInfo.getComments()));
        content.setText(forumInfo.getContent());



    }
}

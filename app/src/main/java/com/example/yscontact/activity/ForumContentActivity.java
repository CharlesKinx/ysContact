package com.example.yscontact.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yscontact.R;
import com.example.yscontact.adapter.ForumCommentAdapter;
import com.example.yscontact.model.CommentsInfo;
import com.example.yscontact.model.ForumInfo;

import java.util.ArrayList;
import java.util.List;

public class ForumContentActivity extends AppCompatActivity {

    private TextView title;
    private TextView userName;
    private TextView commentsNum;
    private TextView content;
    private ListView listView;
    private EditText editComment;
    private Button publishComment;

    private ForumInfo forumInfo;
    private CommentsInfo commentsInfo;
    private ArrayList<CommentsInfo> commentsInfoArrayList;
    private ForumCommentAdapter forumCommentAdapter;

    private void initView(ForumInfo forumInfo){
        title = findViewById(R.id.forum_title);
        userName = findViewById(R.id.forum_user_name);
        commentsNum = findViewById(R.id.forum_comment_num);
        content = findViewById(R.id.forum_content);
        listView = findViewById(R.id.comment_list);

        editComment = findViewById(R.id.et_comment);
        publishComment = findViewById(R.id.btn_publish_comment);
        commentsInfo = new CommentsInfo();

    }


    private ArrayList<CommentsInfo> getData(ForumInfo forumInfo){
        ArrayList<CommentsInfo> commentsInfos = new ArrayList<>();

        for(CommentsInfo commentsInfo : LoginActivity.commentsInfoArrayList){
            if(commentsInfo.getForumID() == forumInfo.getForumID()){
                commentsInfos.add(commentsInfo);
            }
        }
        return commentsInfos;

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forumcontent);
        Intent intent = getIntent();
        forumInfo = (ForumInfo)intent.getSerializableExtra("forumInfo");
        initView(forumInfo);

        title.setText(forumInfo.getTitle());
        userName.setText(forumInfo.getUserInfo().getUserName());
        commentsNum.setText(String.valueOf(forumInfo.getComments()));
        content.setText(forumInfo.getContent());

        commentsInfoArrayList = getData(forumInfo);
        forumCommentAdapter = new ForumCommentAdapter(getApplicationContext(),commentsInfoArrayList);
        listView.setAdapter(forumCommentAdapter);

        publishComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = editComment.getText().toString();
                if (comment.equals("")){
                    Toast.makeText(ForumContentActivity.this,"评论内容不能为空！",Toast.LENGTH_SHORT).show();
                }else{
                    commentsInfo.setComment(comment);
                    commentsInfo.setUser(LoginActivity.userInfo.getUserName());
                    commentsInfo.setForumID(forumInfo.getForumID());
                    LoginActivity.commentsInfoArrayList.add(commentsInfo);
                    commentsInfoArrayList.add(commentsInfo);
                    forumCommentAdapter.notifyDataSetChanged();
                }
            }
        });

    }
}

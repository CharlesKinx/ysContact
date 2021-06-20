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

public class ForumContentActivity extends AppCompatActivity {

    private TextView title;
    private TextView userName;
    private TextView commentsNum;
    private TextView content;
    private ListView listView;
    private EditText editComment;
    private Button publishComment;

    private ForumInfo forumInfo;
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

    private ForumInfo getForumInfo(int id){
        for(int i=0;i<LoginActivity.forumInfoArrayList.size();i++){
            if(LoginActivity.forumInfoArrayList.get(i).getForumID() == id){
                return LoginActivity.forumInfoArrayList.get(i);
            }
        }
        return null;
    }

    private void addCommentNum(ForumInfo forumInfo){
        for(int i =0;i<LoginActivity.forumInfoArrayList.size();i++){
            if(LoginActivity.forumInfoArrayList.get(i).getForumID()==forumInfo.getForumID()){
                LoginActivity.forumInfoArrayList.get(i).setForumID(LoginActivity.forumInfoArrayList.get(i).getComments()+1);
            }
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forumcontent);
        Intent intent = getIntent();
        int forumInfoID =(int)intent.getSerializableExtra("forumInfoID");
        ForumInfo forumInfo = getForumInfo(forumInfoID);

        initView(forumInfo);
        title.setText(forumInfo.getTitle());
        userName.setText(forumInfo.getUserInfo().getAccount());
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
                    CommentsInfo commentsInfo = new CommentsInfo();
                    commentsInfo.setComment(comment);
                    commentsInfo.setUser(LoginActivity.userInfo.getAccount());
                    commentsInfo.setForumID(forumInfo.getForumID());
                    LoginActivity.commentsInfoArrayList.add(commentsInfo);
                    editComment.setText("");
                    forumInfo.setComments(forumInfo.getComments()+1);
                    commentsNum.setText(String.valueOf(forumInfo.getComments()));

                    commentsInfoArrayList.add(commentsInfo);
                    forumCommentAdapter.notifyDataSetChanged();
                }
            }
        });

    }
}

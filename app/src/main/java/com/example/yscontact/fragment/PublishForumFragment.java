package com.example.yscontact.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.yscontact.R;
import com.example.yscontact.activity.HomePageActivity;
import com.example.yscontact.activity.LoginActivity;
import com.example.yscontact.model.ForumInfo;

public class PublishForumFragment extends Fragment {

    private Button publishForum;
    private TextView forumTitle;
    private TextView forumContent;
    private ForumInfo forumInfo;
    private ListView listView;

    private void initView(View view){
        publishForum = view.findViewById(R.id.btn_publish);
        forumTitle = view.findViewById(R.id.et_forum_title);
        forumContent = view.findViewById(R.id.et_forum_content);
        forumInfo = new ForumInfo();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publishforum, container, false);
        initView(view);

        publishForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = forumTitle.getText().toString();
                String content = forumContent.getText().toString();
                if(title.equals("")){
                    Toast.makeText(getActivity(),"标题不能为空!",Toast.LENGTH_SHORT).show();
                }else if(content.equals("")){
                    Toast.makeText(getActivity(),"内容不能为空!",Toast.LENGTH_SHORT).show();
                }else{
                    forumInfo.setTitle(title);
                    forumInfo.setContent(content);
                    forumInfo.setUserInfo(LoginActivity.userInfo);
                    HomePageActivity.forumInfoArrayList.add(forumInfo);
                    Intent intent = new Intent(getActivity(),HomePageActivity.class);
                    startActivity(intent);
                }


            }
        });
        return view;
    }

}

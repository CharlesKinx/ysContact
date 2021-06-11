package com.example.yscontact.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.example.yscontact.R;
import com.example.yscontact.activity.ChangeInfoActivity;
import com.example.yscontact.activity.LoginActivity;
import com.example.yscontact.activity.MyForumListActivity;

public class UserCenterFragment extends Fragment {

    private TextView userName;
    private TextView userPhone;
    private TextView myList;
    private TextView changeInfo;
    private Button logout;

    private void initView(View view){
        userName = view.findViewById(R.id.et_person_username);
        userPhone = view.findViewById(R.id.et_person_phone);
        myList = view.findViewById(R.id.my_list);
        changeInfo =view.findViewById(R.id.change_info);
        logout = view.findViewById(R.id.logout);
        if(LoginActivity.userInfo !=null){
            userName.setText(LoginActivity.userInfo.getUserName());
            userPhone.setText(LoginActivity.userInfo.getUserPhone());
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_usercenter, container, false);

        initView(view);

        myList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MyForumListActivity.class);
                startActivity(intent);
                //Toast.makeText(getActivity(),"点击了我的帖子",Toast.LENGTH_SHORT).show();
            }
        });

        changeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangeInfoActivity.class);
                startActivity(intent);
                //Toast.makeText(getActivity(),"点击了修改信息",Toast.LENGTH_SHORT).show();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}

package com.example.yscontact.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yscontact.R;
import com.example.yscontact.model.ForumInfo;
import com.example.yscontact.model.UserInfo;
import com.example.yscontact.service.ForumService;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity{

    public static ForumService forumService;

    private EditText userName;
    private EditText userPassword;
    private Button login;
    private Button register;

    public static UserInfo userInfo;
    public static ArrayList<ForumInfo> forumInfoArrayList;

    private static final int REGISTER_REQUEST = 1;
    private static final int REGISTER_RESULT = 2;


    /**
     * 初始化组件
     */
    private void initComponent(){
        userName = findViewById(R.id.et_login_user_name);
        userPassword = findViewById(R.id.et_login_psw);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);
        forumInfoArrayList = getInitData();

    }

    private ArrayList<ForumInfo> getInitData(){
        ArrayList<ForumInfo> arrayList = new ArrayList<>();
        ForumInfo forumInfo = new ForumInfo();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("刘亦菲");

        forumInfo.setTitle("吴海董最帅！");
        forumInfo.setContent("吴海董最帅！不接受反驳");
        forumInfo.setComments(555);
        forumInfo.setUserInfo(userInfo);

        arrayList.add(forumInfo);

        forumInfo = new ForumInfo();
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setUserName("吴海董");

        forumInfo.setTitle("韩康泽爱女装");
        forumInfo.setContent("韩康泽最丑！不接受反驳");
        forumInfo.setComments(999);
        forumInfo.setUserInfo(userInfo1);
        arrayList.add(forumInfo);

        return arrayList;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_login);
        initComponent();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent,REGISTER_REQUEST);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                String password = userPassword.getText().toString();

                if(name.equals("")){
                    Toast.makeText(LoginActivity.this,"用户名不能为空！",Toast.LENGTH_SHORT).show();
                }else if(password.equals("")){
                    Toast.makeText(LoginActivity.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                }else if(userInfo == null){
                    Toast.makeText(LoginActivity.this,"没有该用户信息",Toast.LENGTH_SHORT).show();

                }else if(name.equals(userInfo.getUserName())&&password.equals(userInfo.getUserPassword())){
                    Intent intent = new Intent(LoginActivity.this,HomePageActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this,"密码不正确！",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REGISTER_REQUEST:
                if(resultCode == REGISTER_RESULT){
                    userInfo = (UserInfo)data.getSerializableExtra("userInfo");
                }
                break;
        }

    }
}

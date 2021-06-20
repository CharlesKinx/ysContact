package com.example.yscontact.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONObject;
import com.example.yscontact.R;
import com.example.yscontact.model.CommentsInfo;
import com.example.yscontact.model.ForumInfo;
import com.example.yscontact.model.Result;
import com.example.yscontact.model.UserInfo;
import com.google.gson.Gson;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity{



    private EditText userName;
    private EditText userPassword;
    private Button login;
    private Button register;

    public static UserInfo userInfo;
    public static ArrayList<ForumInfo> forumInfoArrayList;
    public static ArrayList<CommentsInfo> commentsInfoArrayList;

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
        commentsInfoArrayList = new ArrayList<>();

        forumInfoArrayList = getInitData();
    }

    private ArrayList<ForumInfo> getInitData(){
        ArrayList<ForumInfo> arrayList = new ArrayList<>();
        ForumInfo forumInfo = new ForumInfo();
        UserInfo userInfo = new UserInfo();
        CommentsInfo commentsInfo = new CommentsInfo();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new java.util.Date());

        userInfo.setAccount("刘亦菲");

        forumInfo.setTitle("吴海董最帅！");
        forumInfo.setContent("吴海董最帅！不接受反驳");
        forumInfo.setUserInfo(userInfo);
        forumInfo.setForumID(1);
        forumInfo.setTime(datetime);
        commentsInfo.setComment("说得对！");
        commentsInfo.setUser("刘亦菲");
        commentsInfo.setForumID(forumInfo.getForumID());
        commentsInfoArrayList.add(commentsInfo);
        arrayList.add(forumInfo);

        forumInfo = new ForumInfo();
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setAccount("吴海董");

        forumInfo.setTitle("韩康泽爱女装");
        forumInfo.setContent("韩康泽最丑！不接受反驳");
        forumInfo.setForumID(2);
        forumInfo.setUserInfo(userInfo1);
        forumInfo.setTime(datetime);
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
                startActivity(intent);
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
                }else {

                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .readTimeout(120, TimeUnit.SECONDS)
                            .connectTimeout(120, TimeUnit.SECONDS)
                            .writeTimeout(120, TimeUnit.SECONDS)
                            .build();

                    UserInfo student = new UserInfo();

                    student.setAccount(name);
                    student.setPassword(password);

                    Gson gson = new Gson();
                    String json = gson.toJson(student);

                    Request request = new Request.Builder()
                            .url("http://10.0.116.6:8081/user/login")
                            .post(RequestBody.create(MediaType.parse("application/json"),json))
                            .build();

                    Call call = okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            System.out.println(e);
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            String res = response.body().string();
                            Result resultInfo = JSONObject.parseObject(res,Result.class);
                            if(resultInfo.getMsg().equals("登录成功")){
                                userInfo  =resultInfo.getUser();
                                Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                                startActivity(intent);
                            }else{
                                runOnUiThread(()->{
                                    Toast.makeText(LoginActivity.this,resultInfo.getMsg(),Toast.LENGTH_SHORT).show();
                                });
                            }
                        }
                    });
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

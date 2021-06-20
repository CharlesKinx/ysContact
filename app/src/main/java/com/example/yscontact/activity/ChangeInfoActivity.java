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
import com.example.yscontact.model.Result;
import com.example.yscontact.model.UserInfo;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChangeInfoActivity extends AppCompatActivity {

    private EditText changeUserName;
    private EditText changeUserPhone;
    private EditText changeUserPassword;
    private EditText changeUserPassword1;

    private Button changeInfoConfirm;
    private Button changeInfoBack;


    private void initView(){

        changeUserName = findViewById(R.id.et_change_user_name);
        changeUserPhone = findViewById(R.id.et_change_user_phone);
        changeUserPassword = findViewById(R.id.et_change_user_password);
        changeUserPassword1 = findViewById(R.id.et_change_user_password1);

        changeInfoConfirm = findViewById(R.id.btn_change_confirm);
        changeInfoBack = findViewById(R.id.btn_change_back);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeinfo);
        initView();

        changeInfoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        changeInfoConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = changeUserName.getText().toString();
                String phone = changeUserPhone.getText().toString();
                String password = changeUserPassword.getText().toString();
                String password1 = changeUserPassword1.getText().toString();


                if(name.equals("")){
                    Toast.makeText(ChangeInfoActivity.this,"用户名不能为空！",Toast.LENGTH_SHORT).show();
                }else if(phone.equals("")){
                    Toast.makeText(ChangeInfoActivity.this,"手机号不能为空！",Toast.LENGTH_SHORT).show();
                }else if(password.equals("")){
                    Toast.makeText(ChangeInfoActivity.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                }else if(password1.equals("")){
                    Toast.makeText(ChangeInfoActivity.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                }else if(!password.equals(password1)){
                    Toast.makeText(ChangeInfoActivity.this,"两次密码不一致！",Toast.LENGTH_SHORT).show();
                }else{

                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .readTimeout(120, TimeUnit.SECONDS)
                            .connectTimeout(120, TimeUnit.SECONDS)
                            .writeTimeout(120, TimeUnit.SECONDS)
                            .build();
                    UserInfo user = new UserInfo();
                    user.setId(LoginActivity.userInfo.getId());
                    user.setAccount(name);
                    user.setPassword(password);
                    user.setTelephone(phone);
                    Gson gson = new Gson();

                    String json = gson.toJson(user);

                    Request request = new Request.Builder()
                            .url("http://10.0.116.6:8081/user/change")
                            .post(RequestBody.create(MediaType.parse("application/json"),json))
                            .build();

                    Call call = okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            System.out.println("请求失败！");
                            System.out.println(e);
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                            String res = response.body().string();
                            Result resultInfo = JSONObject.parseObject(res,Result.class);
                            if(resultInfo.getMsg().equals("修改成功")){
                                LoginActivity.userInfo.setPassword(resultInfo.getUser().getPassword());
                                LoginActivity.userInfo.setTelephone(resultInfo.getUser().getTelephone());
                                LoginActivity.userInfo.setAccount(resultInfo.getUser().getAccount());
                                LoginActivity.userInfo.setId(resultInfo.getUser().getId());
                                Intent intent = new Intent(ChangeInfoActivity.this,HomePageActivity.class);
                                startActivity(intent);

                            }else{
                                runOnUiThread(()->{
                                    Toast.makeText(ChangeInfoActivity.this,resultInfo.getMsg(),Toast.LENGTH_SHORT).show();
                                });
                            }
                        }
                    });
                }
            }
        });

    }
}

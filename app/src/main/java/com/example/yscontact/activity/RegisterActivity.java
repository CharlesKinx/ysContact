package com.example.yscontact.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class RegisterActivity extends AppCompatActivity {

    private EditText userName;
    private EditText userPhone;
    private EditText userPs;
    private EditText userPs1;

    private Button confirm;
    private Button back;

    private static final int REGISTER_RESULT=2;
    private void initView(){

        userName = findViewById(R.id.et_user_name);
        userPhone = findViewById(R.id.et_phone);
        userPs = findViewById(R.id.et_psw);
        userPs1 = findViewById(R.id.et_psw1);

        confirm = findViewById(R.id.btn_ok);
        back = findViewById(R.id.btn_back);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfo userInfo = new UserInfo();

                String name = userName.getText().toString();
                String phone = userPhone.getText().toString();
                String password = userPs.getText().toString();
                String password1 = userPs1.getText().toString();


                if(name.equals("")){
                    Toast.makeText(RegisterActivity.this,"用户名不能为空！",Toast.LENGTH_SHORT).show();
                }else if(phone.equals("")){
                    Toast.makeText(RegisterActivity.this,"手机号不能为空！",Toast.LENGTH_SHORT).show();
                }else if(password.equals("")){
                    Toast.makeText(RegisterActivity.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                }else if(password1.equals("")){
                    Toast.makeText(RegisterActivity.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                }else if(!password.equals(password1)){
                    Toast.makeText(RegisterActivity.this,"两次密码不一致！",Toast.LENGTH_SHORT).show();
                }else{

                    userInfo.setAccount(name);
                    userInfo.setPassword(password);
                    userInfo.setTelephone(phone);

                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .readTimeout(120, TimeUnit.SECONDS)
                            .connectTimeout(120, TimeUnit.SECONDS)
                            .writeTimeout(120, TimeUnit.SECONDS)
                            .build();

                    Gson gson = new Gson();
                    String json = gson.toJson(userInfo);

                    Request request = new Request.Builder()
                            .url("http://10.0.116.6:8081/user/register")
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
                            if(resultInfo.getMsg().equals("注册成功！")){
                                finish();
                            }else{
                                runOnUiThread(()->{
                                    Toast.makeText(RegisterActivity.this,resultInfo.getMsg(),Toast.LENGTH_SHORT).show();
                                });
                            }
                        }
                    });
                }
            }
        });

    }

}

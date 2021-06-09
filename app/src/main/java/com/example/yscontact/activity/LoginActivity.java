package com.example.yscontact.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yscontact.R;
import com.example.yscontact.service.ForumService;

public class LoginActivity extends AppCompatActivity{

    public static ForumService forumService;

    private EditText userName;
    private EditText userPassword;
    private Button login;
    private Button register;

    /**
     * 初始化组件
     */
    void initComponent(){
        userName = findViewById(R.id.et_login_user_name);
        userPassword = findViewById(R.id.et_login_psw);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);

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
                }else{
                    Intent intent = new Intent(LoginActivity.this,HomePageActivity.class);
                    startActivity(intent);
                }

            }
        });

    }


}

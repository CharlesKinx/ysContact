package com.example.yscontact.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yscontact.R;
import com.example.yscontact.model.UserInfo;

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
                    userInfo.setUserName(name);
                    userInfo.setUserPassword(password);
                    userInfo.setUserPhone(phone);
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    intent.putExtra("userInfo",userInfo);
                    setResult(REGISTER_RESULT,intent);
                    finish();
                }


            }
        });

    }

}

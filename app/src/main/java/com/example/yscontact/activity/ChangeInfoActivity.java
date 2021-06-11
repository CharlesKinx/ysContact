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
import com.example.yscontact.fragment.UserCenterFragment;
import com.example.yscontact.model.UserInfo;

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
                Intent intent = new Intent(ChangeInfoActivity.this, HomePageActivity.class);
                startActivity(intent);
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

                    LoginActivity.userInfo.setUserName(name);
                    LoginActivity.userInfo.setUserPhone(phone);
                    LoginActivity.userInfo.setUserPassword(password);

                    Intent intent = new Intent(ChangeInfoActivity.this, HomePageActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}

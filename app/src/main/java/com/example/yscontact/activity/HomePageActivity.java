package com.example.yscontact.activity;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yscontact.R;
import com.example.yscontact.fragment.ForumListFragment;
import com.example.yscontact.fragment.PublishForumFragment;
import com.example.yscontact.fragment.UserCenterFragment;
import com.example.yscontact.model.ForumInfo;
import com.example.yscontact.model.UserInfo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {

    private ForumListFragment forumListFragment;
    private PublishForumFragment publishForumFragment;
    private UserCenterFragment userCenterFragment;
    public static ArrayList<ForumInfo> forumInfoArrayList;
    public static UserInfo userInfo;

    private ArrayList<ForumInfo> getForumInfoArrayList(){
        ArrayList<ForumInfo> forumInfos = new ArrayList<>();
        ForumInfo forumInfo = new ForumInfo();
        UserInfo user = new UserInfo();

        user.setUserName("吴海董");
        forumInfo.setTitle("测试");
        forumInfo.setComments(5);
        forumInfo.setUserInfo(user);
        forumInfos.add(forumInfo);

        forumInfo = new ForumInfo();
        user.setUserName("刘亦菲");
        forumInfo.setTitle("我爱吴海董");
        forumInfo.setComments(999);
        forumInfo.setUserInfo(user);
        forumInfos.add(forumInfo);
        return forumInfos;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.navigation_item1:
                            showFragment(R.id.navigation_item1);
                            return true;
                        case R.id.navigation_item2:
                            showFragment(R.id.navigation_item2);
                            return true;
                        case R.id.navigation_item3:
                            showFragment(R.id.navigation_item3);
                            return true;

                    }

                    return false;
                }
            };

    private void showFragment(int fragmentID){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        switch (fragmentID){
            case R.id.navigation_item1:
                fragmentTransaction.hide(publishForumFragment).hide(userCenterFragment);
                fragmentTransaction.show(forumListFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.navigation_item2:
                fragmentTransaction.hide(forumListFragment).hide(userCenterFragment);
                fragmentTransaction.show(publishForumFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.navigation_item3:
                fragmentTransaction.hide(publishForumFragment).hide(forumListFragment);
                fragmentTransaction.show(userCenterFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
        }

    }
    private void initView(){

        forumListFragment = new ForumListFragment();
        publishForumFragment = new PublishForumFragment();
        userCenterFragment = new UserCenterFragment();

        forumInfoArrayList = getForumInfoArrayList();


        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content,forumListFragment).add(R.id.content,publishForumFragment).
                add(R.id.content,userCenterFragment);
        fragmentTransaction.hide(forumListFragment).hide(publishForumFragment).hide(userCenterFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        showFragment(R.id.navigation_item1);



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        BottomNavigationView navigationView = findViewById(R.id.bv);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }
}

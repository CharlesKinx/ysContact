package com.example.yscontact.fragment;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.yscontact.R;
import com.example.yscontact.activity.ForumContentActivity;
import com.example.yscontact.activity.HomePageActivity;
import com.example.yscontact.activity.LoginActivity;
import com.example.yscontact.adapter.ForumAdapter;
import com.example.yscontact.model.ForumInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForumListFragment extends ListFragment {

    private ListView listView;
    public static ArrayList<Map<String,Object>> mapArrayList;
    private ArrayList<ForumInfo> forumInfoArrayList;
    private ForumAdapter adapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forumlist, container, false);
        listView = view.findViewById(android.R.id.list);
        adapter = new ForumAdapter(getActivity(),LoginActivity.forumInfoArrayList);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ForumInfo forumInfo= LoginActivity.forumInfoArrayList.get(position);
        Intent intent = new Intent(getActivity(), ForumContentActivity.class);
        intent.putExtra("forumInfoID",forumInfo.getForumID());
        startActivity(intent);
    }
}

package com.example.yscontact.fragment;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;

import com.example.yscontact.R;
import com.example.yscontact.model.ForumInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForumListFragment extends ListFragment {

    private ListView listView;
    static ArrayList<Map<String,Object>> mapArrayList;
    private ArrayList<ForumInfo> forumInfoArrayList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] names = {"forumTitle","userName","commentNum"};
        int[] id = {R.id.title,R.id.name,R.id.comment_num};
        mapArrayList = getDate();
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),mapArrayList,R.layout.items,names,id);
        setListAdapter(simpleAdapter);
    }


    private ArrayList<Map<String,Object>> getDate() {
        ArrayList<Map<String,Object>> arrayList = new ArrayList<>();
        for(ForumInfo forumInfo : forumInfoArrayList){
            Map<String,Object> map = new HashMap<>();
            map.put("forumTitle",forumInfo.getTitle());
            map.put("userName",forumInfo.getUserInfo().getUserName());
            map.put("commentNum",forumInfo.getComments());

            arrayList.add(map);
        }

        return arrayList;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forumlist, container, false);
        listView = view.findViewById(R.id.list);
        return view;



    }
}
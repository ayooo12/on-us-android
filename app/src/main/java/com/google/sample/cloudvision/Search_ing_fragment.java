package com.google.sample.cloudvision;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Search_ing_fragment extends Fragment {


    public Search_ing_fragment() {
        // Required empty public constructor
    }

    public static Search_ing_fragment newInstance() {
        Search_ing_fragment fragment = new Search_ing_fragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_search_ing, container, false);


        // 제품검색 버튼 클릭시 레이아웃 변경
//        Button button4 = v.findViewById(R.id.button4);
//        button4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentTransaction mFragmentTransaction = getChildFragmentManager().beginTransaction();
//
//            }
//        });

        return v;
    }
}
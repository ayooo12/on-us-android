package com.google.sample.cloudvision;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

public class Search_Fragment extends Fragment {
    public Search_Fragment() {
        // Required empty public constructor
    }


    public static Search_Fragment newInstance(String param1, String param2) {
        Search_Fragment fragment = new Search_Fragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // 반환할 view 변수
        View returnView;
        View v = inflater.inflate(R.layout.fragment_search_ing, container, false);


        // 제품검색 버튼 클릭시 레이아웃 변경
        Button button4 = v.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v2 = inflater.inflate(R.layout.fragment_search_prd, container, false);
            }
        });



        return v;
    }
}

package com.google.sample.cloudvision;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toolbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyYoso#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyYoso extends AppCompatActivity {
    ImageView back;


    public MyYoso() {
        // Required empty public constructor
    }


    public static MyYoso newInstance(String param1, String param2) {
        MyYoso fragment = new MyYoso();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_yoso);
        ImageView back = (ImageView) findViewById(R.id.mypage_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
////        return inflater.inflate(R.layout.fragment_my_yoso, container, false);
//        View v = inflater.inflate(R.layout.fragment_my_yoso, container, false);
//        viewPager = getActivity().findViewById(R.id.main_viewPager);
//
//        ImageView back = v.findViewById(R.id.mypage_back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                viewPager.setCurrentItem(3);
//
//            }
//        });
//
//        return v;
//    }
}
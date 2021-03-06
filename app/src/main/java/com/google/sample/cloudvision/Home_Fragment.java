package com.google.sample.cloudvision;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class Home_Fragment extends Fragment {

    static ViewPager viewPager;

    // viewpager 내부의 fragment 간의 데이터 전송을 위한 SharedPreferences
//    SharedPreferences prefer;
//    public static final String MY_SHARED_PREFERENCES ="MySharePrefs";

    public static int sendData = 0;

    public Home_Fragment() {
        // Required empty public constructor
    }

    public static Home_Fragment newInstance(String param1, String param2) {
        Home_Fragment fragment = new Home_Fragment();

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
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = getActivity().findViewById(R.id.main_viewPager);


        //전성분 촬영 클릭 -> 해당 페이지로 이동
        FrameLayout frameLayout_camera = v.findViewById(R.id.frameLayout_camera);
        frameLayout_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Camera_activity.class);
                startActivity(intent);

            }
        });

        //성분 검색 클릭 -> 해당 페이지로 이동
        FrameLayout frameLayout_search_ing = v.findViewById(R.id.frameLayout_search_ing);
        frameLayout_search_ing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //성분 검색 페이지 요청

                // 작성한 사람이 지워도되는거면 지워줘~
//                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
//                bundle.putString("what_page_is","ingredients");//번들에 넘길 값 저장
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//
//                Search_Fragment fragment2 = new Search_Fragment();//프래그먼트2 선언
//                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
//                transaction.commit();

                viewPager.setCurrentItem(1);
            }
        });

        //제품 검색 클릭 -> 해당 페이지로 이동
        FrameLayout frameLayout_search_prd = v.findViewById(R.id.frameLayout_search_prd);

        frameLayout_search_prd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //성분 검색 페이지로 어떤 버튼인지 정보 주기 , (데이터 안넘어가는중)
                sendData = 1;
                viewPager.setCurrentItem(1);
            }
        });

        //오른쪽 상단 마이페이지 아이콘 클릭
        ImageView goto_mypage = v.findViewById(R.id.goto_mypage);
        goto_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(3);
            }
        });

        return v;
    }

}
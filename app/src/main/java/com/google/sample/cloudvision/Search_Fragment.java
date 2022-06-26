package com.google.sample.cloudvision;

import static android.content.Context.MODE_PRIVATE;

import static com.google.sample.cloudvision.Home_Fragment.sendData;
import static com.google.sample.cloudvision.Home_Fragment.viewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Search_Fragment extends Fragment {
    public static int is_Btn_click = 0;

    //제품, 성분 검색 버튼 누르면 화면 다르게 보여주는 페이지
    public Search_Fragment() {
        // Required empty public constructor
    }

    public static Search_Fragment newInstance() {
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
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        Button button3, button4;
        //성분 검색 버튼
        button3 = (Button) v.findViewById(R.id.button3);
        button3.setOnClickListener(this::onClick);

        //제품 검색 버튼
        button4 = (Button) v.findViewById(R.id.button4);
        button4.setOnClickListener(this::onClick);

        return v;
    }

    //클릭한 버튼에따라 색 변함
    public void onClick(View view) {
        setButtonColor(view.getId());
    }


    // fragmnet 내부의 fragment layout 변경하는 함수
    private void setChildFragment(Fragment child) {
        FragmentTransaction childFt = getChildFragmentManager().beginTransaction();


        if (!child.isAdded()) {
            childFt.replace(R.id.child_fragment_container, child);
            childFt.addToBackStack(null);
            childFt.commit();
        }
    }

    public void setButtonColor(int id) {

        Button button3 = getView().findViewById(R.id.button3);
        Button button4 = getView().findViewById(R.id.button4);

        Fragment fg;
        switch (id) {

            // 성분검색 버튼 클릭시,
            // 성분 검색하는 fragment가 해당 칸에 들어간다.
            // 버튼 색상 변경
            case R.id.button3:
                fg = Search_ing_fragment.newInstance();
                setChildFragment(fg);  //버튼에 따른 '검색창','recyclerview' 부분 fragment 새로 붙이기

                button3.setTextColor(Color.parseColor("#1EE4A5"));
                button3.setBackgroundResource(R.drawable.left_btn_search);

                button4.setTextColor(Color.parseColor("#ffffff"));
                button4.setBackgroundResource(R.drawable.right_btn_search_no);

                break;

            // 제품검색 버튼 클릭시,
            // 제품 검색하는 fragment가 해당 칸에 들어간다.
            // 버튼 색상 변경
            case R.id.button4:
                //fg: child Fragment로 위에서 비워놓은 특정 레이아웃에 들어가게 할 자식 fragment
                fg = Search_prd_fragment.newInstance();
                setChildFragment(fg);

                // 성분검색 버튼은 회색으로 만든다.
                button3.setTextColor(Color.parseColor("#ffffff"));
                button3.setBackgroundResource(R.drawable.left_btn_search_no);

                button4.setTextColor(Color.parseColor("#1EE4A5"));
                button4.setBackgroundResource(R.drawable.right_btn_search);

                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // Home_fragment 버튼에 따른 내부 프라그먼트 보여주기
        if (sendData == 1) {
            Log.d("send", String.valueOf(sendData));

            Fragment choose_fg = Search_prd_fragment.newInstance();
            setChildFragment(choose_fg);
        } else {
            Log.d("send", String.valueOf(sendData));

            Fragment start_fg = Search_ing_fragment.newInstance();
            setChildFragment(start_fg);
        }

    }
}


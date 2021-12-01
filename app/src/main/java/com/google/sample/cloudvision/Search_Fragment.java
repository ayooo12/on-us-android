package com.google.sample.cloudvision;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
        View v = inflater.inflate(R.layout.fragment_search, container, false);


        Button button3, button4;
        button3 = (Button) v.findViewById(R.id.button3);
        button3.setOnClickListener(this::onClick);

        button4 = (Button) v.findViewById(R.id.button4);
        button4.setOnClickListener(this::onClick);

        return v;
    }


        public void onClick(View view){

            Button button3 = getView().findViewById(R.id.button3);
            Button button4 = getView().findViewById(R.id.button4);

            Fragment fg;
            switch (view.getId()) {

                // 성분검색 버튼 클릭시,
                case R.id.button3:
                    fg = Search_ing_fragment.newInstance();
                    setChildFragment(fg);

                    button3.setTextColor(Color.parseColor("#1EE4A5"));
                    button3.setBackgroundResource(R.drawable.left_btn_search);

                    button4.setTextColor(Color.parseColor("#ffffff"));
                    button4.setBackgroundResource(R.drawable.right_btn_search_no);


                    break;

                // 제품검색 버튼 클릭시,
                case R.id.button4:
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


        // fragmnet 내부의 fragment layout 변경
        private void setChildFragment(Fragment child) {
            FragmentTransaction childFt = getChildFragmentManager().beginTransaction();

            if (!child.isAdded()) {
                childFt.replace(R.id.child_fragment_container, child);
                childFt.addToBackStack(null);
                childFt.commit();
            }
        }

    }


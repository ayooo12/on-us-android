package com.google.sample.cloudvision;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyPage_Fragment extends Fragment {
    static ViewPager viewPager;
    ImageView imageStar;
    ImageView imagemyyoso;

    public MyPage_Fragment() {
    }

    public static MyPage_Fragment newInstance(String param1, String param2) {
        MyPage_Fragment fragment = new MyPage_Fragment();

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
        View v = inflater.inflate(R.layout.fragment_my_page, container, false);

        imageStar = v.findViewById(R.id.image_star);
        imageStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Boomarkintent = new Intent(getActivity(), Bookmark_activity.class);
                startActivity(Boomarkintent);
            }
        });

        imagemyyoso = v.findViewById(R.id.image_myyoso);
        imagemyyoso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MyyosoIntent = new Intent(getActivity(), MyYoso.class);
                startActivity(MyyosoIntent);
            }
        });

        return v;
    }
}
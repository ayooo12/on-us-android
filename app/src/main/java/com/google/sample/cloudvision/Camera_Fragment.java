package com.google.sample.cloudvision;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// 여기에 카메라 기능 화면 넣기! (성분 카메라로 찍고 인식하는 화면 !)
public class Camera_Fragment extends Fragment {

    public Camera_Fragment() {
        // Required empty public constructor
    }

    public static Camera_Fragment newInstance(String param1, String param2) {
        Camera_Fragment fragment = new Camera_Fragment();

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
        return inflater.inflate(R.layout.fragment_camera_, container, false);
    }
}
package com.google.sample.cloudvision;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Search_prd_fragment extends Fragment {

    public Search_prd_fragment() {
        // Required empty public constructor
    }

    public static Search_prd_fragment newInstance() {
        Search_prd_fragment fragment = new Search_prd_fragment();
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
        View v = inflater.inflate(R.layout.fragment_search_prd, container, false);


        return v;
    }
}
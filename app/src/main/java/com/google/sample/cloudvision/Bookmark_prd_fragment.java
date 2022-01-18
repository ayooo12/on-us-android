package com.google.sample.cloudvision;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Bookmark_prd_fragment extends Fragment {


    ArrayList<BookmarkPrdData> prddata;
    ListView mListView;
    private static Bookmark_prd_adapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.bookmark_ing, container, false);

        prddata = new ArrayList<>();
        prddata.add(new BookmarkPrdData(R.drawable.prd1, "드래곤펩타이드 앰플세럼",R.drawable.button_x, R.drawable.bad_water_1));
        prddata.add(new BookmarkPrdData(R.drawable.search_prd_img, "어린 쑥 수분진정 토너",R.drawable.button_x, R.drawable.bad_water_3));
        mListView = (ListView) rootView.findViewById(R.id.listview_ing);
        mAdapter = new Bookmark_prd_adapter(getContext(), prddata);
        mListView.setAdapter(mAdapter);

        return rootView;
    }
}






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

public class Bookmark_ing_fragment extends Fragment {


    ArrayList<BookmarkData> ingdata;
    ListView mListView;
    private static Bookmark_ing_adapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.bookmark_ing, container, false);

        ingdata = new ArrayList<>();
        ingdata.add(new BookmarkData(R.drawable.water1, "리모넨", R.drawable.button_x));
        ingdata.add(new BookmarkData(R.drawable.water1, "멘톨", R.drawable.button_x));
        ingdata.add(new BookmarkData(R.drawable.water2, "에틸벤젠", R.drawable.button_x));
        mListView = (ListView) rootView.findViewById(R.id.listview_ing);
        mAdapter = new Bookmark_ing_adapter(getContext(), ingdata);
        mListView.setAdapter(mAdapter);

        return rootView;
    }
}






package com.google.sample.cloudvision;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ComponentDlg_Fragment extends DialogFragment {

    public ComponentDlg_Fragment() {}

    public static ComponentDlg_Fragment newInstance(String param1, String param2) {
        ComponentDlg_Fragment cptDlg = new ComponentDlg_Fragment();
        return cptDlg;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_component_dlg_, container, false);
        return v;
    }

    @Override
    public void onResume() {
        int width=getResources().getDimensionPixelSize(R.dimen.cptDlg_width);
        int height=getResources().getDimensionPixelSize(R.dimen.cptDlg_height);
        getDialog().getWindow().setLayout(width, height);

        //다이얼로그 외에 배경 투명하게하기
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        super.onResume();
    }
}
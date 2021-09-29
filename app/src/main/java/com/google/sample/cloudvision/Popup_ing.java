package com.google.sample.cloudvision;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


public class Popup_ing extends DialogFragment {
    public static final String TAG_EVENT_DIALOG = "dialog_event";
    public Popup_ing() {}
    public static Popup_ing getInstance(){
    Popup_ing p = new Popup_ing();
    return p;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_component_dlg_, container);
        return v;
    }
}

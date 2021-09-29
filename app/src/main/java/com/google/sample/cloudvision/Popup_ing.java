package com.google.sample.cloudvision;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class Popup_ing extends Activity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_component_dlg_);
    }
}

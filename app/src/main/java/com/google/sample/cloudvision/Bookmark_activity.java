package com.google.sample.cloudvision;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Bookmark_activity extends AppCompatActivity {
    private final int Fragment_1 = 1;
    private final int Fragment_2 = 2;
    private final int Fragment_3 = 3;
    Button button1;
    Button button2;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        button1 = (Button) findViewById(R.id.ing_button3);
        button2 = (Button) findViewById(R.id.prd_button4);
        backButton = (ImageView) findViewById(R.id.mypage_back);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentView(Fragment_1);
                button1.setTextColor(Color.parseColor("#1EE4A5"));
                button1.setBackgroundResource(R.drawable.left_btn_search);

                button2.setTextColor(Color.parseColor("#ffffff"));
                button2.setBackgroundResource(R.drawable.right_btn_search_no);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentView(Fragment_2);
                button1.setTextColor(Color.parseColor("#ffffff"));
                button1.setBackgroundResource(R.drawable.left_btn_search_no);

                button2.setTextColor(Color.parseColor("#1EE4A5"));
                button2.setBackgroundResource(R.drawable.right_btn_search);

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        FragmentView(Fragment_1);
    }

    private void FragmentView(int fragment){

        //FragmentTransactiom를 이용해 프래그먼트를 사용합니다.
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (fragment){
            case 1:
                // 첫번 째 프래그먼트 호출
                Bookmark_ing_fragment fragment1 = new Bookmark_ing_fragment();
                transaction.replace(R.id.fragment_container, fragment1);
                transaction.commit();
                break;

            case 2:
                // 두번 째 프래그먼트 호출
                Bookmark_prd_fragment fragment2 = new Bookmark_prd_fragment();
                transaction.replace(R.id.fragment_container, fragment2);
                transaction.commit();
                break;

        }

    }


}



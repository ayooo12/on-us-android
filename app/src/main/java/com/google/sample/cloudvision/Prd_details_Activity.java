package com.google.sample.cloudvision;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;



public class Prd_details_Fragment extends AppCompatActivity {
    private final int Fragment_1 = 1;
    private final int Fragment_2 = 2;
    Button button1;
    Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_prd_details);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentView(Fragment_1);
                button1.setTextColor(Color.parseColor("#616161"));
                button1.setBackgroundResource(R.drawable.border_bottom);

                button2.setTextColor(Color.parseColor("#DADADA"));
                button2.setBackgroundResource(R.drawable.border_bottom_no);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentView(Fragment_2);
                button1.setTextColor(Color.parseColor("#DADADA"));
                button1.setBackgroundResource(R.drawable.border_bottom_no);

                button2.setTextColor(Color.parseColor("#616161"));
                button2.setBackgroundResource(R.drawable.border_bottom);


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
                Prd_details_info_Fragment fragment1 = new Prd_details_info_Fragment();
                transaction.replace(R.id.fragment_container, fragment1);
                transaction.commit();
                break;

            case 2:
                // 두번 째 프래그먼트 호출
                Prd_details_review_Fragment fragment2 = new Prd_details_review_Fragment();
                transaction.replace(R.id.fragment_container, fragment2);
                transaction.commit();
                break;
        }

    }


}
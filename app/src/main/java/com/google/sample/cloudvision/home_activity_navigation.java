package com.google.sample.cloudvision;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class home_activity_navigation extends AppCompatActivity {
    ViewPager viewPager;
    Menu menu;
    MainPagerAdapter mpadapter = new MainPagerAdapter(getSupportFragmentManager());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_navigation);

        viewPager = findViewById(R.id.main_viewPager);

        //캐싱을 해놓을 프래그먼트 개수
        viewPager.setOffscreenPageLimit(4);

        //뷰 페이저의 1번째 페이지='홈'
        Home_Fragment home_fragment = new Home_Fragment();
        mpadapter.addItem(home_fragment);

        //뷰 페이저의 2번째 페이지='제품/성분 검색'
        //페이지 프라그먼트로 바꿔야 붙이기 가능
        Search_Fragment search_fragment = new Search_Fragment();
        mpadapter.addItem(search_fragment);

        //뷰 페이저의 3번째 페이지='카메라 촬영'
        Camera_Fragment camera_fragment2 = new Camera_Fragment();
        mpadapter.addItem(camera_fragment2);

        //뷰 페이저의 3번째 페이지='카메라 촬영'
        Camera_Fragment camera_fragment = new Camera_Fragment();
        mpadapter.addItem(camera_fragment);

        //뷰 페이저의 4번째 페이지='마이페이지'
        MyPage_Fragment myPage_fragment = new MyPage_Fragment();
        mpadapter.addItem(myPage_fragment);

        viewPager.setAdapter(mpadapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        menu = bottomNavigationView.getMenu();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab1:
                        viewPager.setCurrentItem(0);
                        return true;

                    case R.id.tab2:
                        viewPager.setCurrentItem(1);
                        return true;

                    case R.id.tab3:
                        viewPager.setCurrentItem(2);
                        return true;

                    case R.id.tab4:
                        viewPager.setCurrentItem(3);
                        return true;
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            //현재 페이지가 몇번째인지 확인해준다.
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).isChecked();

            }

            @Override
            public void onPageScrollStateChanged(int position) {

            }
        });

    }

    //어댑터 안에서 각각의 아이템(프라그먼트 페이지들)을 데이터로서 관리한다
    class MainPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public MainPagerAdapter(FragmentManager fm){
            super(fm);
        }

        public void addItem(Fragment item) {
            items.add(item);
        }



        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }

//        @NonNull
//        @Override
//        public Fragment createFragment(int position) {
//            return null;
//        }
//
//        @Override
//        public int getItemCount() {
//            return 0;
//        }
    }
}
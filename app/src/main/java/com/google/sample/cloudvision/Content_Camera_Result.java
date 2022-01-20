package com.google.sample.cloudvision;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.AlertDialog;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public class Content_Camera_Result extends AppCompatActivity {
    TextView ing[] = new TextView[20];

    String ingfull="";


    TextView chem_item_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_result);

        Intent intent = getIntent();
        ingfull = intent.getStringExtra("resulttext");
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        // 동적으로 textview 설정
        for(int j=0; j<ing.length; j++){
            int getID = getResources().getIdentifier("chem_item_"+(j+1),"id",getPackageName());
            ing[j] = (TextView)findViewById(getID);
            ing[j].setText(String.valueOf(j));
        }

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://3.34.218.223:8080");
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        retrofitAPI.getPosts(ingfull).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(@NonNull Call<List<Post>> call,
                                   @NonNull Response<List<Post>> response) {
                if(response.isSuccessful()) {

                    List<Post> data = response.body();
                    Log.d("TEST","성공성공");
                    //Log.d("TEST", data.get(0).getName());


                    for(int i=0;i<data.size();i++){
                        // 각 테이블레이아웃 textView에 성분명 넣기
                        ing[i].setText(data.get(i).getName());
                        ing[i].setVisibility(View.VISIBLE);

                        // 성분명
                        String finalJ = data.get(i).getName();
                        // 해당 성분명 인덱스   -> 왜 따로 변수 지정해서 썼어??
                        int finalI = i;

                        //각 성분 클릭 이벤트
                        ing[i].setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View view) {
                                //custom dialog 띄우는 방식
                                //dialog 띄울때 성분명 같이 보냄
                                //유해성,기능성에 따른 각각의 다이얼로그 띄우기
                                    if (data.get(finalI).getType().equals("H")) {
                                        ComponentDlg_Fragment cptDlg = new ComponentDlg_Fragment(finalJ);
                                        cptDlg.show(getSupportFragmentManager(), "show");
                                    } else if(data.get(finalI).getType().equals("F")){ ComponentDlg2_Fragment cptDlg2 = new ComponentDlg2_Fragment(finalJ);
                                        cptDlg2.show(getSupportFragmentManager(), "show");
                                    }else{

                                    }
                                }
                        });
                    }

                    // 유해성 기능성에 따른 성분명 배경색 지정
                    for(int i=0;i<data.size();i++){
                        if(data.get(i).getType().equals("H")){
                            ing[i].setBackgroundColor(Color.parseColor("#FF7979"));
                        }else ing[i].setBackgroundColor(Color.parseColor("#3CB9FF"));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                t.printStackTrace();
                Log.d("TEST","실패");;
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                Intent intent = new Intent(this, home_activity_navigation.class); //지금 액티비티에서 다른 액티비티로 이동하는 인텐트 설정
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //인텐트 플래그 설정
                startActivity(intent);  //인텐트 이동
                finish();   //현재 액티비티 종료
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, home_activity_navigation.class); //지금 액티비티에서 다른 액티비티로 이동하는 인텐트 설정
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //인텐트 플래그 설정
        startActivity(intent);  //인텐트 이동
        finish();   //현재 액티비티 종료
    }

    public interface RetrofitAPI{
        @GET("/IngreTest?")
        Call<List<Post>> getPosts(@Query("nameTest") String name);

        @FormUrlEncoded
        @POST("/posts")
        Call<Post> postData(@FieldMap HashMap<String, Object> param);
    }
}


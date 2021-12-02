package com.google.sample.cloudvision;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
    TextView ing[] = new TextView[4];

    String ingfull = "트리클로산,리날룰";

    TextView chem_item_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_result);

        chem_item_1=findViewById(R.id.chem_item_1);
        chem_item_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //성분명 변수로 보내야함.
                ComponentDlg_Fragment cptDlg_fragment = new ComponentDlg_Fragment("리모넨");
                cptDlg_fragment.show(getSupportFragmentManager(),"show");
            }
        });


        for(int j=0; j<ing.length; j++){
            int getID = getResources().getIdentifier("chem_item_"+(j+1),"id",getPackageName());
            ing[j] = (TextView)findViewById(getID);
            ing[j].setText(String.valueOf(j));
            //버튼 클릭
        }


        /*
        ing[0] = (TextView)findViewById(R.id.ing0);
        ing[1] = (TextView)findViewById(R.id.ing1);
        ing[2] = (TextView)findViewById(R.id.ing1);*/

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://3.36.163.80:8080");
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
                    Log.d("TEST", data.get(0).getName());


                    for(int i=0;i<data.size();i++){
                        ing[i].setText(data.get(i).getName());
                        ing[i].setVisibility(View.VISIBLE);
                        String finalJ = data.get(i).getName();
                        ing[i].setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View view) {
                                //custom dialog 띄우는 방식
                                //dialog 띄울때 성분명 같이 보내기
                                ComponentDlg_Fragment cptDlg = new ComponentDlg_Fragment(finalJ);
                                cptDlg.show(getSupportFragmentManager(),"show");
                            }
                        });
                    }


                    for(int i=0;i<data.size();i++){
                        if(data.get(i).getType().equals("H")){
                            ing[i].setBackgroundColor(Color.RED);
                        }else ing[i].setBackgroundColor(Color.GREEN);

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

    public interface RetrofitAPI{
        @GET("/IngreTest?")
        Call<List<Post>> getPosts(@Query("nameTest") String name);

        @FormUrlEncoded
        @POST("/posts")
        Call<Post> postData(@FieldMap HashMap<String, Object> param);
    }
}


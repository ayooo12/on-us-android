package com.google.sample.cloudvision;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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
import retrofit2.http.Query;

public class MainActivity3 extends AppCompatActivity {

    TextView tv[] = new TextView[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tv[0] = (TextView)findViewById(R.id.tv1);
        tv[1] = (TextView)findViewById(R.id.tv2);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://3.36.163.80:8080");
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        String[] name1 = {"리날룰"};

        retrofitAPI.getData(name1[0]).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(@NonNull Call<List<Post>> call,
                                   @NonNull Response<List<Post>> response) {
                if(response.isSuccessful()) {

                    List<Post> data = response.body();
                    Log.d("TEST", "성공성공");
                    Log.d("TEST", data.get(0).getName());


                    tv[0].setText(data.get(0).getName());
                    tv[1].setText(data.get(1).getName());

                    for(int i=0;i<data.size();i++){
                        if(data.get(i).getType().equals("H")){
                            tv[i].setBackgroundColor(Color.RED);
                        }else tv[i].setBackgroundColor(Color.GREEN);

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
        @GET("/IngreTest?nameTest=트리클로산,리날룰")
        Call<List<Post>> getData(@Query("name") String name);

        @FormUrlEncoded
        @POST("/posts")
        Call<Post> postData(@FieldMap HashMap<String, Object> param);
    }
}
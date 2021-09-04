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
import retrofit2.http.Body;


public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        String testString="리날룰,가나다,라마바,사아자,차카타";

//        String[] name1 = {"리날룰"};

    }

    private  void postData(String testString){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://3.36.163.80:8080");
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder
                .build();

        MainActivity4.RetrofitAPI retrofitAPI = retrofit.create(MainActivity4.RetrofitAPI.class);


    }



    public interface RetrofitAPI{

        @POST("components")

        Call<StringPost> createPost(@Body StringPost stringPost);

//        @GET("/IngreTest?nameTest=트리클로산,리날룰")
//        Call<List<Post>> getData(@Query("name") String name);
//
//        @FormUrlEncoded
//        @POST("/posts")
//        Call<Post> postData(@FieldMap HashMap<String, Object> param);
    }
}
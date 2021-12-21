package com.google.sample.cloudvision;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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


public class Search_ing_fragment extends Fragment {


    public Search_ing_fragment() {
        // Required empty public constructor
    }

    public static Search_ing_fragment newInstance() {
        Search_ing_fragment fragment = new Search_ing_fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://3.34.218.223:8080");
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder
                .build();

        Search_ing_fragment.RetrofitAPI retrofitAPI = retrofit.create(Search_ing_fragment.RetrofitAPI.class);

        retrofitAPI.getName("리").enqueue(new Callback<List<Search_ingredients>>() {
            @Override
            public void onResponse(Call<List<Search_ingredients>> call, Response<List<Search_ingredients>> response) {
                if(response.isSuccessful()){
                    List<Search_ingredients> data = response.body();
                    Log.d("성분검색기능","구현 완료");
                    Log.d("성분검색기능",data.get(0).getName());
                }
            }

            @Override
            public void onFailure(Call<List<Search_ingredients>> call, Throwable t) {

                t.printStackTrace();
                Log.d("성분검색기능","실패");

            }
        });

    }

    public interface RetrofitAPI {
        // 검색한 단어로 요청을 하면 그 단어를 포함한 성분들이 응답됩니다.
        @GET("/search-ingre?")
        Call<List<Search_ingredients>> getId(@Query("ingredient") String name);

        @GET("/search-ingre?")
        Call<List<Search_ingredients>> getName(@Query("ingredient") String name);

        @FormUrlEncoded
        @POST("/posts")
        Call<Func> postData(@FieldMap HashMap<String, Object> param);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_search_ing, container, false);

        return v;
    }
}
package com.google.sample.cloudvision;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

public class Search_prd_fragment extends Fragment {

    public Search_prd_fragment() {
        // Required empty public constructor
    }

    public static Search_prd_fragment newInstance() {
        Search_prd_fragment fragment = new Search_prd_fragment();
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

        Search_prd_fragment.RetrofitAPI retrofitAPI = retrofit.create(Search_prd_fragment.RetrofitAPI.class);

        retrofitAPI.getName("샴푸A").enqueue(new Callback<List<Search_product>>() {
            @Override
            public void onResponse(Call<List<Search_product>> call, Response<List<Search_product>> response) {
                if(response.isSuccessful()){
                    List<Search_product> data = response.body();
                    Log.d("제품검색기능","구현 완료");
                    Log.d("제검색기능",data.get(0).getName());
                }
            }

            @Override
            public void onFailure(Call<List<Search_product>> call, Throwable t) {
                t.printStackTrace();
                Log.d("제품검색기능","실패");
            }
        });
    }

    public interface RetrofitAPI {
        @GET("/search-product?")
        Call<List<Search_product>> getId(@Query("product") String name);

        @GET("/search-product?")
        Call<List<Search_product>> getName(@Query("product") String name);

        @FormUrlEncoded
        @POST("/posts")
        Call<Func> postData(@FieldMap HashMap<String, Object> param);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search_prd, container, false);

        EditText editText1 = v.findViewById(R.id.editText1);
        String productname = String.valueOf(editText1.getText());


        return v;
    }
}
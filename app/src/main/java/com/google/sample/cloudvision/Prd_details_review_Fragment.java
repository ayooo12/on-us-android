package com.google.sample.cloudvision;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
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


public class Prd_details_review_Fragment extends Fragment {
    public static Prd_details_review_adapter adapter = new Prd_details_review_adapter();


    public Prd_details_review_Fragment() {
        // Required empty public constructor
    }

    public static Prd_details_review_Fragment newInstance() {
        Prd_details_review_Fragment fragment = new Prd_details_review_Fragment();
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

        Prd_details_review_Fragment.RetrofitAPI retrofitAPI = retrofit.create(Prd_details_review_Fragment.RetrofitAPI.class);

        retrofitAPI.getKeyword("rinse").enqueue(new Callback<List<Prd_details_review_data>>() {
            @Override
            public void onResponse(Call<List<Prd_details_review_data>> call,
                                   Response<List<Prd_details_review_data>> response) {
                if(response.isSuccessful()){
                    List<Prd_details_review_data> data = response.body();

                    if (data.isEmpty()){
                        Log.d("????????????","?????? ??????");
                    }

                    Log.d("????????????","?????? ??????");
                    Log.d("????????????",data.get(0).getTitle());

                    //?????? ?????? ???????????? ????????????????????? ????????????, ????????? ????????? ????????????.
                    for (int i =0; i<data.size(); i++){
                        String date=data.get(i).getDate();
                        String writer=data.get(i).getWriter();
                        String review=data.get(i).getTitle();
                        // ????????? ????????? ?????? ????????? ???????????????.
                        Prd_details_review_recyclerview item3 = new Prd_details_review_recyclerview(date,"11??????", writer, review, "4", R.drawable.review_11, R.drawable.prd_details_review_star);
                        adapter.addItem(item3);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Prd_details_review_data>> call, Throwable t) {

                t.printStackTrace();
                Log.d("??????????????????","??????");

            }
        });

    }

    public interface RetrofitAPI {
        // ????????? ????????? ????????? ?????? ??? ????????? ????????? ???????????? ???????????????.
        @GET("/findReviews?")
        Call<List<Prd_details_review_data>> getKeyword(@Query("keyword") String name);

        @FormUrlEncoded
        @POST("/posts")
        Call<Func> postData(@FieldMap HashMap<String, Object> param);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.prd_details_review_fragment, container, false);
        RecyclerView recyclerView=v.findViewById(R.id.review_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        return v;
    }
}
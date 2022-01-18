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

        retrofitAPI.getKeyword("shampoo").enqueue(new Callback<List<Prd_details_review_data>>() {
            @Override
            public void onResponse(Call<List<Prd_details_review_data>> call,
                                   Response<List<Prd_details_review_data>> response) {
                if(response.isSuccessful()){
                    List<Prd_details_review_data> data = response.body();

                    if (data.isEmpty()){
                        Log.d("리뷰기능","리뷰 없음");
                    }

                    Log.d("리뷰기능","구현 완료");
                    Log.d("리뷰기능",data.get(0).getTitle());

                    //모든 이름 받아와서 리싸이클러뷰에 추가하고, 리스트 형태로 보여준다.
                    for (int i =0; i<data.size(); i++){
                        String date=data.get(i).getDate();
                        String writer=data.get(i).getWriter();
                        String review=data.get(i).getTitle();
                        // 안맞아 제품은 따로 이미지 변경해야함.
                        Prd_details_review_recyclerview item3 = new Prd_details_review_recyclerview(date,"11번가", writer, review, "4", R.drawable.review_11, R.drawable.prd_details_review_star);
                        adapter.addItem(item3);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Prd_details_review_data>> call, Throwable t) {

                t.printStackTrace();
                Log.d("제품리뷰기능","실패");

            }
        });

    }

    public interface RetrofitAPI {
        // 검색한 단어로 요청을 하면 그 단어를 포함한 성분들이 응답됩니다.
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
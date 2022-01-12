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
    TextView date[] = new TextView[4];
    TextView writer[] = new TextView[4];
    TextView review[] = new TextView[4];
    TextView productRating[] = new TextView[4];


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
                    Log.d("제품리뷰기능","구현 완료");
                    Log.d("제품리뷰기능",data.get(0).getTitle());

                    //모든 이름 받아와서 리싸이클러뷰에 추가하고, 리스트 형태로 보여준다.
                    for (int i =0; i<1; i++){
                        date[i].setText(data.get(i).getDate());
                        date[i].setVisibility(View.VISIBLE);
                        writer[i].setText(data.get(i).getWriter());
                        writer[i].setVisibility(View.VISIBLE);
                        review[i].setText(data.get(i).getTitle());
                        review[i].setVisibility(View.VISIBLE);
                        productRating[i].setText(data.get(i).getProductRating());
                        productRating[i].setVisibility(View.VISIBLE);
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
        date[0] = v.findViewById(R.id.date1);
        writer[0] = v.findViewById(R.id.writer1);
        review[0] = v.findViewById(R.id.review1);
        productRating[0] = v.findViewById(R.id.prdScore1);

        return v;
    }
}
package com.google.sample.cloudvision;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

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


public class Prd_details_info_Fragment extends Fragment {

    TextView all_Ing;
    StringBuilder aIng = new StringBuilder( "" );

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://3.36.163.80:8080");
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder
                .build();

        Prd_details_info_Fragment.RetrofitAPI retrofitAPI = retrofit.create(Prd_details_info_Fragment.RetrofitAPI.class);

        retrofitAPI.getaIngre("샴푸A").enqueue(new Callback<List<aIngre>>() {
            @Override
            public void onResponse(@NonNull Call<List<aIngre>> call,
                                   @NonNull Response<List<aIngre>> response) {
                if(response.isSuccessful()) {

                    List<aIngre> data = response.body();
                    Log.d("TEST","성공성공");
                    Log.d("TEST", data.get(0).getName());

                    //기능성 성분 이름이랑 등급 변경
                    for(int i=0;i<data.size();i++){
                        aIng.append(data.get(i).getName());
                        all_Ing.setText(aIng);
                        aIng.append(", ");
                    }
                }
            }
            @Override
            public void onFailure(Call<List<aIngre>> call, Throwable t) {
                t.printStackTrace();
                Log.d("TEST", "실패");
            }
        });
    }

    public interface RetrofitAPI{
        @GET("/findIngreListTest?")
        Call<List<aIngre>> getaIngre(@Query("productName") String name);

        @FormUrlEncoded
        @POST("/posts")
        Call<Func> postData(@FieldMap HashMap<String, Object> param);
    }


    public Prd_details_info_Fragment() {
        // Required empty public constructor
    }


    public static Prd_details_info_Fragment newInstance(String param1, String param2) {
        Prd_details_info_Fragment fragment = new Prd_details_info_Fragment();

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_prd_details_info, container, false);
        all_Ing = v.findViewById(R.id.all_Ing);

        return v;
    }
}

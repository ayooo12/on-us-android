package com.google.sample.cloudvision;

import static com.google.sample.cloudvision.Search_Fragment.is_Btn_click;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    // 제품 검색 페이지

    // adapter 정의
    public static search_prd_adapter adapter=new search_prd_adapter();

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

        retrofitAPI.getName("샴푸").enqueue(new Callback<List<search_prd_retrofit_Class>>() {
            @Override
            public void onResponse(Call<List<search_prd_retrofit_Class>> call, Response<List<search_prd_retrofit_Class>> response) {
                if(response.isSuccessful()){
                    List<search_prd_retrofit_Class> data = response.body();
                    Log.d("제품검색기능","구현 완료");
//                    Log.d("제품검색기능",data.get(0).getName());

                    if (data.isEmpty()){
                        Log.d("제품검색기능","검색 결과 없음");
                    }else{
                        //데이터가 있어야만 adpater 에 item 추가 -> null data로 인한 오류 방지
                        //모든 이름 받아와서 리싸이클러뷰에 추가하고, 리스트 형태로 보여준다.

                        //모든 이름 받아와서 리싸이클러뷰에 추가하고, 리스트 형태로 보여준다.
                        for (int i =0; i<data.size(); i++){
                            String name=data.get(i).getName();

                            //어댑터에 (제품)아이템 하나씩 올리기
                            adapter.addItem(new search_prd_recyclerview(name));
                            adapter.notifyDataSetChanged();

                            Log.d("제품검색나열",name);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<search_prd_retrofit_Class>> call, Throwable t) {
                t.printStackTrace();
                Log.d("제품검색기능","실패");
            }
        });

    }


    public interface RetrofitAPI {
        @GET("/search-product?")
        Call<List<search_prd_retrofit_Class>> getId(@Query("product") String name);

        @GET("/search-product?")
        Call<List<search_prd_retrofit_Class>> getName(@Query("product") String name);

        @FormUrlEncoded
        @POST("/posts")
        Call<Func> postData(@FieldMap HashMap<String, Object> param);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search_prd, container, false);

        // 검색 창에서 text 뽑아옴
        EditText search_prd_editText = v.findViewById(R.id.search_prd_editText);
        String search_prd_editText_str = String.valueOf(search_prd_editText.getText());

        Log.d("dd",search_prd_editText_str);

        RecyclerView recyclerView=v.findViewById(R.id.pdt_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        return v;
    }
}
package com.google.sample.cloudvision;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


public class Search_ing_fragment extends Fragment {

    public static search_ing_adapter adapter=new search_ing_adapter();

    // 성분 검색 페이지

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

        // 돋보기 모양 검색 버튼 클릭시 작동 코드
        Button search_ing_btn = getView().findViewById(R.id.search_ing_btn);
        search_ing_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 검색창에 입력한 내용 문자열로 받아놓기
                // 검색한 내용 없으면 오류남.
                EditText search_ing_editText = getActivity().findViewById(R.id.search_ing_editText);
                String editText1_str = String.valueOf(search_ing_editText.getText());

                // getName('리') 부분에 검색창에 작성한 editText 내용 들어가야함
                // 검색 내용 없음에 대한 예외처리 필요
                retrofitAPI.getName("리").enqueue(new Callback<List<Search_ingredients>>() {
                    @Override
                    public void onResponse(Call<List<Search_ingredients>> call, Response<List<Search_ingredients>> response) {
                        if(response.isSuccessful()){
                            List<Search_ingredients> data = response.body();

                            // 검색 내용 없음에 대한 처리
                            if (data.isEmpty()){
                                Log.d("성분검색기능","검색결과 없음");
                            }

                            Log.d("성분검색기능","구현 완료");
                            Log.d("성분검색기능",data.get(0).getName());

                            //모든 이름 받아와서 리싸이클러뷰에 추가하고, 리스트 형태로 보여준다.
                            for (int i =0; i<data.size(); i++){
                                String name=data.get(i).getName();
                                // 안맞아 제품은 따로 이미지 변경해야함.
                                search_ing_recyvlerview item3 = new search_ing_recyvlerview(name,R.drawable.right_ing);
                                adapter.addItem(item3);
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Search_ingredients>> call, Throwable t) {
                        t.printStackTrace();
                        Log.d("성분검색기능","실패");
                    }
                });
            }
        });


//        // 임시로 성분 리스트 만들어봄
//        search_ing_recyvlerview item1 = new search_ing_recyvlerview("리날룰",R.drawable.right_ing);
//        search_ing_recyvlerview item2 = new search_ing_recyvlerview("트리머시기",R.drawable.wrong_ing);
//
//        //임실 데이터 추가해봄
//        adapter.addItem(item1);
//        adapter.addItem(item2);


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

        RecyclerView recyclerView=v.findViewById(R.id.ing_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        return v;
    }
}
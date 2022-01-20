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
import android.widget.ImageButton;
import android.widget.ImageView;

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

    public static search_ing_adapter ing_adapter=new search_ing_adapter();

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

        // 검색 창에서 text 뽑아옴 -> search_ing_editText
        EditText search_ing_editText = v.findViewById(R.id.search_ing_editText);

        // 돋보기 모양 검색 버튼 클릭 이벤트
        ImageView search_ing_btn = v.findViewById(R.id.search_ing_btn);
        search_ing_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //recyclerview에 쌓인 아이템 배열 한번 전체 삭제 후 검색내용 reload
                ing_adapter.clearAll();


                // 검색 창에서 text 뽑아옴 -> search_prd_editText_str
                String search_ing_editText_str = String.valueOf(search_ing_editText.getText());
                Log.d("dd", search_ing_editText_str);

                Retrofit.Builder builder = new Retrofit.Builder();
                builder.baseUrl("http://3.34.218.223:8080");
                builder.addConverterFactory(GsonConverterFactory.create());
                Retrofit retrofit = builder
                        .build();

                Search_ing_fragment.RetrofitAPI retrofitAPI = retrofit.create(Search_ing_fragment.RetrofitAPI.class);

                // 검색창에 작성한 내용 없으면 동작X
                if (search_ing_editText_str.isEmpty() || search_ing_editText_str == null) {
                    Log.d("search_ing_editText_str", "검색창에 작성한 내용 없음");

                } else {
                    // 검색할 내용 있을시 레트로핏 서버 연결 시작
                    retrofitAPI.getName(search_ing_editText_str).enqueue(new Callback<List<Search_ingredients>>() {
                        @Override
                        public void onResponse(Call<List<Search_ingredients>> call, Response<List<Search_ingredients>> response) {
                            if (response.isSuccessful()) {
                                List<Search_ingredients> data = response.body();

                                Log.d("성분검색기능", "구현 완료");

                                // 검색 내용 없음에 대한 처리
                                if (data.isEmpty() || data == null) {
                                    Log.d("성분검색기능", "검색결과 없음");
                                } else {
                                    //데이터가 있어야만 adpater 에 item 추가 -> null data로 인한 오류 방지
                                    //모든 이름 받아와서 리싸이클러뷰에 추가하고, 리스트 형태로 보여준다.
                                    for (int i = 0; i < data.size(); i++) {
                                        String name = data.get(i).getName();

                                        // 안맞아 제품은 따로 이미지 변경해야함.
                                        search_ing_recyvlerview item3 = new search_ing_recyvlerview(name, R.drawable.right_ing);
                                        ing_adapter.addItem(item3);
                                        ing_adapter.notifyDataSetChanged();

                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Search_ingredients>> call, Throwable t) {
                            t.printStackTrace();
                            Log.d("성분검색기능", "실패");
                        }
                    });
                }
            }
        });


        RecyclerView recyclerView=v.findViewById(R.id.ing_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        ing_adapter.notifyDataSetChanged();
        recyclerView.setAdapter(ing_adapter);

        return v;
    }
}
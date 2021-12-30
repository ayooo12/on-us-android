package com.google.sample.cloudvision;

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

public class ComponentDlg2_Fragment extends DialogFragment {


    TextView componetName; //제일 상단의 '성분명' TextView
    ImageView questionMark; //'호흡기 과민성분' TextView 옆에 있는 물음표 아이콘
    ImageView back; //X 버튼(뒤로가기 버튼)
    static String componentNameData; //이전 페이지에서 성분명 받아오는 변수

    String IG="";

    TextView ingreName[] = new TextView[4]; //팝업창 기능성이름 텍스트뷰
    TextView ingreGrade[] = new TextView[4]; //팝업창 기능성등급 텍스트뷰
    TextView Tv[] = new TextView[4]; //팝업창 유발수준 글씨
    ImageView gradeCheck[] = new ImageView[4]; //성분등급 이미지지

    public ComponentDlg2_Fragment(String componentNameD) {

        //성분명 클릭시 이름 받아오기
        componentNameData = componentNameD;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        //물음표 버튼 누르면 '성분 분류 구분표' 팝
//        questionMark = getDialog().findViewById(R.id.questionMark);
//        questionMark.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                cpt_classification_dlg cptDialog = new cpt_classification_dlg();
//                cptDialog.show(getParentFragmentManager(),"show");
//            }
//        });


        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://3.34.218.223:8080");
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        retrofitAPI.getfrags(componentNameData).enqueue(new Callback<List<Func>>() {
            @Override
            public void onResponse(@NonNull Call<List<Func>> call,
                                   @NonNull Response<List<Func>> response) {
                if(response.isSuccessful()) {

                    List<Func> data = response.body();
                    Log.d("TEST","성공성공");
                    //Log.d("TEST", data.get(0).getElement());

                    //기능성 성분 이름이랑 등급 변경
                    for(int i=0;i<data.size();i++){
                        ingreName[i].setText(data.get(i).getElement());
                        ingreName[i].setVisibility(View.VISIBLE);
                        String b = data.get(i).getElement();
                        gradeCheck[i].setImageResource(R.drawable.ic_func);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Func>> call, Throwable t) {
                t.printStackTrace();
                Log.d("TEST", "실패");
            }
        });
    }

    public interface RetrofitAPI{
        @GET("/findFuncTest?")
        Call<List<Func>> getfrags(@Query("ingreName") String name);

        @FormUrlEncoded
        @POST("/posts")
        Call<Func> postData(@FieldMap HashMap<String, Object> param);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_component_dlg2_, container, false);

        //성분명 가져와서 TextView 변경하기
        componetName= v.findViewById(R.id.componetName);
        componetName.setText(componentNameData);

        //기능성 성분 연결
        ingreName[0] = v.findViewById(R.id.ingreName0);
        ingreName[1] = v.findViewById(R.id.ingreName1);
        ingreName[2] = v.findViewById(R.id.ingreName2);
        ingreName[3] = v.findViewById(R.id.ingreName3);
/*
        ingreGrade[0] = v.findViewById(R.id.ingreGrade0);
        ingreGrade[1] = v.findViewById(R.id.ingreGrade1);
        ingreGrade[2] = v.findViewById(R.id.ingreGrade2);
        ingreGrade[3] = v.findViewById(R.id.ingreGrade3);
        Tv[0] = v.findViewById(R.id.Tv0);
        Tv[1] = v.findViewById(R.id.Tv1);
        Tv[2] = v.findViewById(R.id.Tv2);
        Tv[3] = v.findViewById(R.id.Tv3);*/
        gradeCheck[0] = v.findViewById(R.id.gradeCheck0);
        gradeCheck[1] = v.findViewById(R.id.gradeCheck1);
        gradeCheck[2] = v.findViewById(R.id.gradeCheck2);
        gradeCheck[3] = v.findViewById(R.id.gradeCheck3);

        //x 버튼 누르면 사라지
        back = v.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        int width=getResources().getDimensionPixelSize(R.dimen.cptDlg_width);
        int height=getResources().getDimensionPixelSize(R.dimen.cptDlg_height);
        getDialog().getWindow().setLayout(width, height);

        //다이얼로그 외에 배경 투명하게하기
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        super.onResume();
    }

    }

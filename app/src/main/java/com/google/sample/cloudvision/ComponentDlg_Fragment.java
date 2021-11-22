package com.google.sample.cloudvision;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

public class ComponentDlg_Fragment extends DialogFragment {


    TextView componetName; //제일 상단의 '성분명' TextView
    ImageView questionMark; //'호흡기 과민성분' TextView 옆에 있는 물음표 아이콘
    ImageView back; //X 버튼(뒤로가기 버튼)
    static String componentNameData; //이전 페이지에서 성분명 받아오는 변수

    String IG="";

    TextView ingreName[] = new TextView[4]; //팝업창 기능성이름 텍스트뷰
    TextView ingreGrade[] = new TextView[4]; //팝업창 기능성등급 텍스트뷰
    TextView Tv[] = new TextView[4]; //팝업창 유발수준 글씨
    ImageView gradeCheck[] = new ImageView[4]; //성분등급 이미지지

    public ComponentDlg_Fragment(String componentNameD) {

        //성분명 클릭시 이름 받아오기
        componentNameData = componentNameD;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://3.36.163.80:8080");
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        retrofitAPI.getfrags(componentNameData).enqueue(new Callback<List<frag>>() {
            @Override
            public void onResponse(@NonNull Call<List<frag>> call,
                                   @NonNull Response<List<frag>> response) {
                if(response.isSuccessful()) {

                    List<frag> data = response.body();
                    Log.d("TEST","성공성공");
                    Log.d("TEST", data.get(0).getghsClass());

                    //기능성 성분 이름이랑 등급 변경
                    for(int i=0;i<data.size();i++){
                        ingreName[i].setText(data.get(i).getcategory());
                        ingreName[i].setVisibility(View.VISIBLE);
                        String a = data.get(i).getcategory();
                        String b = data.get(i).getghsClass();
                        ingreGrade[i].setText(data.get(i).getghsClass());
                        ingreGrade[i].setVisibility(View.VISIBLE);
                        Tv[i].setVisibility(View.VISIBLE);
                        SelectPic(a,b,gradeCheck[i]);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<frag>> call, Throwable t) {
                t.printStackTrace();
                Log.d("TEST", "실패");
            }
        });
    }

    public interface RetrofitAPI{
        @GET("/findGhsTest?")
        Call<List<frag>> getfrags(@Query("ingreName") String name);

        @FormUrlEncoded
        @POST("/posts")
        Call<frag> postData(@FieldMap HashMap<String, Object> param);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_component_dlg_, container, false);

        //성분명 가져와서 TextView 변경하기
        componetName= v.findViewById(R.id.componetName);
        componetName.setText(componentNameData);

        //기능성 성분 연결
        ingreName[0] = v.findViewById(R.id.ingreName0);
        ingreName[1] = v.findViewById(R.id.ingreName1);
        ingreName[2] = v.findViewById(R.id.ingreName2);
        ingreName[3] = v.findViewById(R.id.ingreName3);
        ingreGrade[0] = v.findViewById(R.id.ingreGrade0);
        ingreGrade[1] = v.findViewById(R.id.ingreGrade1);
        ingreGrade[2] = v.findViewById(R.id.ingreGrade2);
        ingreGrade[3] = v.findViewById(R.id.ingreGrade3);
        Tv[0] = v.findViewById(R.id.Tv0);
        Tv[1] = v.findViewById(R.id.Tv1);
        Tv[2] = v.findViewById(R.id.Tv2);
        Tv[3] = v.findViewById(R.id.Tv3);
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


        //물음표 버튼 누르면 '성분 분류 구분표' 팝업
        questionMark = v.findViewById(R.id.questionMark);
        questionMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                component_classification_dlg cptDialog = new component_classification_dlg();
                cptDialog.show(getParentFragmentManager(),"show");
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

    public void SelectPic(String IN, String IG,ImageView IV) { //IN = 기능성 이름 IG = 등급
        switch (IN) {
            case "급성독성":
                if (IG.equals("1")) IV.setImageResource(R.drawable.ic_4_1);
                else if (IG.equals("2")) IV.setImageResource(R.drawable.ic_4_2_orange);
                else if (IG.equals("3")) IV.setImageResource(R.drawable.ic_4_3);
                else if (IG.equals("4")) IV.setImageResource(R.drawable.ic_4_4);

            case "피부부식성":
                if (IG.equals("1A")) IV.setImageResource(R.drawable.ic_3_1a);
                else if (IG.equals("1B")) IV.setImageResource(R.drawable.ic_3_1b);
                else if (IG.equals("1C")) IV.setImageResource(R.drawable.ic_3_1c);

            case "피부자극성":
                if (IG.equals("2")) IV.setImageResource(R.drawable.ic_0_2);

            case "특정표적 장기독성 (1회)":
                if (IG.equals("1")) IV.setImageResource(R.drawable.ic_3_1);
                else if (IG.equals("2")) IV.setImageResource(R.drawable.ic_3_2_orange);
                else if (IG.equals("3")) IV.setImageResource(R.drawable.ic_3_3);

            case "특정표적 장기독성 (반복)":
                if (IG.equals("1")) IV.setImageResource(R.drawable.ic_2_1);
                else if (IG.equals("2")) IV.setImageResource(R.drawable.ic_2_2);

            case "흡인 유해성":
                if (IG.equals("1")) IV.setImageResource(R.drawable.ic_2_1);
                else if (IG.equals("2")) IV.setImageResource(R.drawable.ic_2_2);

            case "심한 눈 손상성":
                if (IG.equals("1")) IV.setImageResource(R.drawable.ic_0_1);

            case "눈 자극성":
                if (IG.equals("2A")) IV.setImageResource(R.drawable.ic_2_2a);
                else if (IG.equals("1B")) IV.setImageResource(R.drawable.ic_2_1b);

            case "호흡기 과민성":
                if (IG.equals("1A")) IV.setImageResource(R.drawable.ic_2_1a);
                else if (IG.equals("1B")) IV.setImageResource(R.drawable.ic_2_1b);

            case "피부 과민성":
                if (IG.equals("1A")) IV.setImageResource(R.drawable.ic_2_1a);
                else if (IG.equals("1B")) IV.setImageResource(R.drawable.ic_2_1b);

            case "생식세포 변이원성":
                if (IG.equals("1A")) IV.setImageResource(R.drawable.ic_3_1a);
                else if (IG.equals("1B")) IV.setImageResource(R.drawable.ic_3_1b);
                else if (IG.equals("2")) IV.setImageResource(R.drawable.ic_3_2_yellow);

            case "발암성":
                if (IG.equals("1A")) IV.setImageResource(R.drawable.ic_3_1a);
                else if (IG.equals("1B")) IV.setImageResource(R.drawable.ic_3_1b);
                else if (IG.equals("2")) IV.setImageResource(R.drawable.ic_3_2_yellow);

            case "생식독성":
                if (IG.equals("1A")) IV.setImageResource(R.drawable.ic_4_1a);
                else if (IG.equals("1B")) IV.setImageResource(R.drawable.ic_4_1b);
                else if (IG.equals("2")) IV.setImageResource(R.drawable.ic_2_2);
                else if (IG.equals("수유독성")) IV.setImageResource(R.drawable.ic_suyu);

        }
    }
}
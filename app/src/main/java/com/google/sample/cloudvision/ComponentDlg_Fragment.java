package com.google.sample.cloudvision;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
    String componame;

    public ComponentDlg_Fragment(String componentNameD) {

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
        builder.baseUrl("http://3.36.163.80:8080");
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);



        retrofitAPI.getfrags("트리클로산").enqueue(new Callback<List<frag>>() {
            @Override
            public void onResponse(@NonNull Call<List<frag>> call,
                                   @NonNull Response<List<frag>> response) {
                if(response.isSuccessful()) {

                    List<frag> data = response.body();
                    Log.d("TEST","성공성공");
                    Log.d("TEST", data.get(0).getghsClass());


                    componentNameData = data.get(0).getcategory();
                    componetName.setText(componentNameData);

                }


            }

            @Override
            public void onFailure(Call<List<frag>> call, Throwable t) {
                t.printStackTrace();
                Log.d("TEST","실패");;
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
package com.google.sample.cloudvision;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BookmarkPrdActivity extends AppCompatActivity {
    ArrayList<BookmarkPrdData> prdDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmark_prd);

        this.InitializeMovieData();

        ListView listView = (ListView)findViewById(R.id.listView_prd);
        final BookmarkPrdActivity.MyAdapter myAdapter = new BookmarkPrdActivity.MyAdapter(this,prdDataList);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(),
                        myAdapter.getItem(position).getPrdName(),
                        Toast.LENGTH_LONG).show();
            }

        });
    }

    public class MyAdapter extends BaseAdapter {
        Context mContext = null;
        LayoutInflater mLayoutInflater = null;
        ArrayList<BookmarkPrdData> sample;

        public MyAdapter(Context context, ArrayList<BookmarkPrdData> data) {
            mContext = context;
            sample = data;
            mLayoutInflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return sample.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public BookmarkPrdData getItem(int position) {
            return sample.get(position);
        }

        //즐겨찾기_성분
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = mLayoutInflater.inflate(R.layout.bookmark_prd_item, null);

            ImageView prd = (ImageView)view.findViewById(R.id.prd);
            TextView prdName = (TextView)view.findViewById(R.id.prdName);
            ImageView delete = (ImageView)view.findViewById(R.id.button_x);

            prd.setImageResource(sample.get(position).getPrd());
            prdName.setText(sample.get(position).getPrdName());
            delete.setImageResource(sample.get(position).getDelete());

            return view;
        }
    }

    public void InitializeMovieData()
    {
        prdDataList = new ArrayList<BookmarkPrdData>();

        prdDataList.add(new BookmarkPrdData(R.drawable.prd1, "드래곤펩타이드 앰플세럼", R.drawable.button_x));
        prdDataList.add(new BookmarkPrdData(R.drawable.prd1, "드래곤펩타이드 앰플세럼", R.drawable.button_x));
        prdDataList.add(new BookmarkPrdData(R.drawable.prd1, "드래곤펩타이드 앰플세럼", R.drawable.button_x));
    }
}


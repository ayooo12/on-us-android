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

public class BookmarkActivity extends AppCompatActivity {

    ArrayList<BookmarkData> ingDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmark_ing);

        this.InitializeMovieData();

        ListView listView = (ListView)findViewById(R.id.listView_ing);
        final MyAdapter myAdapter = new MyAdapter(this,ingDataList);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(),
                        myAdapter.getItem(position).getIngName(),
                        Toast.LENGTH_SHORT).show();
            }

        });
    }

    public class MyAdapter extends BaseAdapter {
        Context mContext = null;
        LayoutInflater mLayoutInflater = null;
        ArrayList<BookmarkData> sample;

        public MyAdapter(Context context, ArrayList<BookmarkData> data) {
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
        public BookmarkData getItem(int position) {
            return sample.get(position);
        }

        //즐겨찾기_성분
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = mLayoutInflater.inflate(R.layout.bookmark_ing_item, null);

            ImageView water = (ImageView)view.findViewById(R.id.water);
            TextView ingName = (TextView)view.findViewById(R.id.ingName);
            ImageView delete = (ImageView)view.findViewById(R.id.button_x);

            water.setImageResource(sample.get(position).getWater());
            ingName.setText(sample.get(position).getIngName());
            delete.setImageResource(sample.get(position).getDelete());

            return view;
        }
    }

    public void InitializeMovieData()
    {
        ingDataList = new ArrayList<BookmarkData>();

        ingDataList.add(new BookmarkData(R.drawable.water1, "리모넨", R.drawable.button_x));
        ingDataList.add(new BookmarkData(R.drawable.water1, "멘톨", R.drawable.button_x));
        ingDataList.add(new BookmarkData(R.drawable.water2, "에틸벤젠", R.drawable.button_x));
    }
}



package com.google.sample.cloudvision;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.sample.cloudvision.BookmarkData;
import com.google.sample.cloudvision.R;

import java.util.ArrayList;
import java.util.List;

public class Bookmark_ing_adapter extends ArrayAdapter implements AdapterView.OnItemClickListener {

    private Context context;
    private List list;

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
    }

    class ViewHolder {
        public ImageView water;
        public TextView ingName;
        public ImageView delete;
    }

    public Bookmark_ing_adapter(Context context, ArrayList list){
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.bookmark_ing_item, parent, false);
        }

        viewHolder = new ViewHolder();
        viewHolder.water = (ImageView) convertView.findViewById(R.id.water);
        viewHolder.ingName = (TextView) convertView.findViewById(R.id.ingName);
        viewHolder.delete = (ImageView) convertView.findViewById(R.id.button_x);

        final BookmarkData data = (BookmarkData) list.get(position);
        viewHolder.water.setImageResource(data.getWater());
        viewHolder.ingName.setText(data.getIngName());
        viewHolder.delete.setImageResource(data.getDelete());


//        //아이템 클릭 방법2 - 클릭시 아이템 반전 효과가 안 먹힘
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, " " + actor.getName(), Toast.LENGTH_SHORT).show();
//            }
//        });

        //Return the completed view to render on screen
        return convertView;
    }
}
package com.google.sample.cloudvision;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class search_prd_adapter extends RecyclerView.Adapter<search_prd_adapter.ViewHolder> {
Context context;
    static ArrayList<search_prd_recyclerview> prdData = new ArrayList<search_prd_recyclerview>();

    //배열 리스트 items에 새로운 item 객체 추가하기기
    public final void addItem(search_prd_recyclerview item) {
        prdData.add(item);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.search_prd_recyclerview, parent, false);
        context = parent.getContext();
        return new ViewHolder(itemView);

    }
    

    @Override
    public void onBindViewHolder(@NonNull search_prd_adapter.ViewHolder holder, int position) {
        search_prd_recyclerview item = prdData.get(position);
        ViewHolder.setItem(item);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String name = holder.prd_name.getText().toString();
                String name = item.getPrd_name();
                Toast.makeText(v.getContext(), name, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, Prd_details_Activity.class);
                intent.putExtra("name",name);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return prdData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public static TextView prd_name;

        public ViewHolder(final View itemView) {
            super(itemView);
            prd_name = itemView.findViewById(R.id.prd_name);
        }

        public static void setItem(search_prd_recyclerview item){
            prd_name.setText(item.getPrd_name());
        }
    }

    public void clearAll(){
        prdData.clear();
    }
}

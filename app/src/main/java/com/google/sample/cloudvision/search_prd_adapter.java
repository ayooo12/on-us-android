package com.google.sample.cloudvision;

import android.view.LayoutInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class search_prd_adapter extends RecyclerView.Adapter<search_prd_adapter.ViewHolder> {

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

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull search_prd_adapter.ViewHolder holder, int position) {
        search_prd_recyclerview item = prdData.get(position);
        ViewHolder.setItem(item);
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

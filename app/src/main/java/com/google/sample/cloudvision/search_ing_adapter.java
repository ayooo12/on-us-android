package com.google.sample.cloudvision;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class search_ing_adapter extends RecyclerView.Adapter<search_ing_adapter.ViewHolder> {
    Context context;
    static ArrayList<search_ing_recyvlerview> ingData = new ArrayList<search_ing_recyvlerview>();

    public interface OnItemClickListener{
        void onItemClick(View v, int pos);
    }
    //배열 리스트 items에 새로운 item 객체 추가하기기
    public final void addItem(search_ing_recyvlerview item) {
        ingData.add(item);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.search_ing_recyclerview, parent, false);
        context = parent.getContext();
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull search_ing_adapter.ViewHolder holder, int position) {
        search_ing_recyvlerview item = ingData.get(position);
        ViewHolder.setItem(item);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String name = holder.prd_name.getText().toString();
                String name = item.getIng_name();
                //Toast.makeText(v.getContext(), name, Toast.LENGTH_SHORT).show();
                ComponentDlg_Fragment cptDlg = new ComponentDlg_Fragment(name);
                cptDlg.show(((AppCompatActivity) context).getSupportFragmentManager(),"show");
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        static TextView ing_name;
        static ImageView rightOrwrong;

        public ViewHolder(final View itemView) {
            super(itemView);
            ing_name = itemView.findViewById(R.id.ing_name);
            rightOrwrong = itemView.findViewById(R.id.rightOrwrong);
        }

        public static void setItem(search_ing_recyvlerview item){
            ing_name.setText(item.getIng_name());
            rightOrwrong.setImageResource(item.getRightOrwrong());
        }
    }

    public void clearAll() {ingData.clear();}
}

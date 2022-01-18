package com.google.sample.cloudvision;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Prd_details_review_adapter extends RecyclerView.Adapter<Prd_details_review_adapter.ViewHolder> {

    static ArrayList<Prd_details_review_recyclerview> reviewData = new ArrayList<Prd_details_review_recyclerview>();

    //배열 리스트 items에 새로운 item 객체 추가하기기
    public final void addItem(Prd_details_review_recyclerview item) {
        reviewData.add(item);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.prd_details_review_item, parent, false);

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull Prd_details_review_adapter.ViewHolder holder, int position) {
            Prd_details_review_recyclerview item = reviewData.get(position);
        ViewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return reviewData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        static TextView cmp_name;
        static TextView writer;
        static TextView date;
        static TextView review;
        static TextView num;
        static ImageView cmp_img;
        static ImageView star;

        public ViewHolder(final View itemView) {
            super(itemView);
            cmp_name = itemView.findViewById(R.id.review_cmp_name);
            writer = itemView.findViewById(R.id.reviewer);
            date = itemView.findViewById(R.id.date);
            review = itemView.findViewById(R.id.review);
            num = itemView.findViewById(R.id.prdScore);
            cmp_img = itemView.findViewById(R.id.img_cmp);
            star = itemView.findViewById(R.id.review_star);
        }

        public static void setItem(Prd_details_review_recyclerview item){
            cmp_name.setText(item.getCmp_name());
            writer.setText(item.getWriter());
            date.setText(item.getDate());
            review.setText(item.getReview());
            num.setText(item.getNum());
            cmp_img.setImageResource(item.getImg_cmp());
            star.setImageResource(item.getStar());
        }
    }
}

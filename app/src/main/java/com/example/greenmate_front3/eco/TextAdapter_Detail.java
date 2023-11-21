package com.example.greenmate_front3.eco;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenmate_front3.R;

import java.util.ArrayList;
import java.util.List;

public class TextAdapter_Detail extends RecyclerView.Adapter<TextAdapter_Detail.ViewHolder>{
    private ArrayList<Items_Detail> items = null;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView text_title;
        TextView text_category;
        TextView text_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text_title = itemView.findViewById(R.id.text_title);
            text_category = itemView.findViewById(R.id.text_category);
            text_content = itemView.findViewById(R.id.text_content);
        }
    }

    public TextAdapter_Detail(ArrayList<Items_Detail> itemList) {
        this.items = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.items_ecodetail, parent, false);
        TextAdapter_Detail.ViewHolder vh = new TextAdapter_Detail.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String rec_title = items.get(position).rec_title;
        String rec_category = items.get(position).rec_category;
        String rec_content = items.get(position).rec_content;

        holder.text_title.setText(rec_title);
        holder.text_category.setText(rec_category);
        holder.text_content.setText(rec_content);

    }

    // 전체 데이터 개수 리턴
    @Override
    public int getItemCount() {
        return items.size();
    }

    // 어댑터 아이템 수정
    public void setItems(ArrayList<Items_Detail> list){
        items = list;
        notifyDataSetChanged();
    }
}

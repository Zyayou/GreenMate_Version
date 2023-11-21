package com.example.greenmate_front3.eco;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenmate_front3.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;

public class TextAdapter_Detail extends RecyclerView.Adapter<TextAdapter_Detail.ViewHolder>{
    private ArrayList<Items_Detail> items = null;
    private Context context;

    // 선택된 카테고리를 저장하는 변수
    private Set<String> selectedCategories = new HashSet<>();

    // 적용된 카테고리를 설정하는 메서드
    public void setSelectedCategories(Set<String> selectedCategories) {
        this.selectedCategories = selectedCategories;
        notifyDataSetChanged(); // 데이터가 변경되었음을 어댑터에 알림
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView text_title;
        TextView text_category;
        TextView text_content;
        Button detailBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text_title = itemView.findViewById(R.id.text_title);
            text_category = itemView.findViewById(R.id.text_category);
            text_content = itemView.findViewById(R.id.text_content);
            detailBtn = itemView.findViewById(R.id.detailBtn);

            // 버튼 클릭 이벤트 처리
            detailBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Items_Detail clickedItem = items.get(position);

                        // EcoDetailViewFragment를 띄우기 위해 Activity의 FragmentManager를 사용
                        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                        EcoDetailViewFragment ecoDetailViewFragment = new EcoDetailViewFragment();

                        // Bundle을 사용하여 데이터 전달
                        Bundle bundle = new Bundle();
                        bundle.putString("title", clickedItem.rec_title);
                        bundle.putString("category", clickedItem.rec_category);
                        bundle.putString("content", clickedItem.rec_content);
                        ecoDetailViewFragment.setArguments(bundle);

                        // EcoDetailViewFragment를 화면에 띄움
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, ecoDetailViewFragment)
                                .addToBackStack(null)
                                .commit();
                    }
                }
            });
        }
    }

    public TextAdapter_Detail(Context context, ArrayList<Items_Detail> itemList) {
        this.context = context;
        this.items = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.items_ecodetail, parent, false);
        //TextAdapter_Detail.ViewHolder vh = new TextAdapter_Detail.ViewHolder(view);
        ViewHolder vh = new ViewHolder(view);

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

        // 선택된 카테고리에 해당하는 아이템만 보여주기
        if (selectedCategories.isEmpty() || selectedCategories.contains(rec_category)) {
            // 높이 설정
            holder.itemView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;

            // 여백 설정
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 15); // 여백 추가
            holder.itemView.setLayoutParams(layoutParams);

            holder.itemView.setVisibility(View.VISIBLE);
        } else {
            // 선택되지 않은 카테고리에 해당하는 아이템은 숨김

            // 높이 설정
            holder.itemView.getLayoutParams().height = 0;

            // 여백 설정
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0); // 여백을 0으로 설정
            holder.itemView.setLayoutParams(layoutParams);

            holder.itemView.setVisibility(View.GONE);
        }
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

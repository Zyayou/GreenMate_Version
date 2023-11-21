package com.example.greenmate_front3.eco;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.greenmate_front3.R;

public class EcoDetailViewFragment extends Fragment {
    TextView text_title_D, text_category_D, text_content_D;
    public EcoDetailViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_ecodetail_view, container, false);

        text_title_D = (TextView) view.findViewById(R.id.text_title_D);
        text_category_D = (TextView) view.findViewById(R.id.text_category_D);
        text_content_D = (TextView) view.findViewById(R.id.text_content_D);

        // Bundle에서 데이터를 가져옴
        Bundle bundle = getArguments();
        if (bundle != null) {
            String title = bundle.getString("title", "");
            String category = bundle.getString("category", "");
            String content = bundle.getString("content", "");

            // 가져온 데이터를 화면에 표시하거나 필요한 작업 수행
            // 예: TextView에 데이터를 설정
            text_title_D.setText(title);
            text_category_D.setText(category);
            text_content_D.setText(content);
        }

        return view;
    }
}

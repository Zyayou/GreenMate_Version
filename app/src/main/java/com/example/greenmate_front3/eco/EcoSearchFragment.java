package com.example.greenmate_front3.eco;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.greenmate_front3.R;

public class EcoSearchFragment extends Fragment{

    private TextView categoryView;
    private CheckBox ch1, ch2, ch3;
    private Button catagoryBtn, detailBtn1, detailBtn2;
    EcoSearchViewFragment ecoSearchViewFragment;

    public EcoSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_ecosearch, container, false);

        categoryView = (TextView) view.findViewById(R.id.categoryView);
        ch1 = (CheckBox) view.findViewById(R.id.checkBox1);
        ch2 = (CheckBox) view.findViewById(R.id.checkBox2);
        ch3 = (CheckBox) view.findViewById(R.id.checkBox3);

        catagoryBtn = (Button) view.findViewById(R.id.catagoryBtn);
        catagoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "";
                if(ch1.isChecked()) result += ch1.getText()+", ";
                if(ch2.isChecked()) result += ch2.getText()+", ";
                if(ch3.isChecked()) result += ch3.getText()+" ";
                if(result == "") result = "적용된 카테고리가 없습니다.";

                categoryView.setText(result);
                Toast.makeText(getActivity(),"카테고리를 적용합니다.",Toast.LENGTH_SHORT).show();
            }
        });

        ecoSearchViewFragment = new EcoSearchViewFragment();
        detailBtn1 = (Button) view.findViewById(R.id.detailBtn1);
        detailBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, ecoSearchViewFragment).addToBackStack(null).commit();
                Toast.makeText(getActivity(),"첫번째 결과물 더보기",Toast.LENGTH_SHORT).show();
            }
        });

        detailBtn2 = (Button) view.findViewById(R.id.detailBtn2);
        detailBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, ecoSearchViewFragment).addToBackStack(null).commit();
                Toast.makeText(getActivity(),"두번째 결과물 더보기",Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}

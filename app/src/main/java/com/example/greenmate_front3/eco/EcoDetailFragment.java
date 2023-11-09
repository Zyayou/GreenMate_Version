package com.example.greenmate_front3.eco;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.greenmate_front3.R;
import com.example.greenmate_front3.activity.EditActivity;
import com.ramotion.foldingcell.FoldingCell;

import org.w3c.dom.Text;

public class EcoDetailFragment extends Fragment {
    private FoldingCell foldingCell;
    private TextView categoryView;
    private CheckBox ch1, ch2, ch3, ch4, ch5, ch6, ch7, ch8, ch9, ch10, ch11, ch12, ch13, ch14, ch15, ch16;
    private Button catagoryBtn, closeBtn, detailBtn1, detailBtn2;
    private LinearLayout cellTitleView;
    EcoDetailViewFragment ecoDetailViewFragment;
    public EcoDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_ecodetail, container, false);

        foldingCell = (FoldingCell) view.findViewById(R.id.foldingCell);
        cellTitleView = view.findViewById(R.id.cellTitleView);
        cellTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foldingCell.toggle(false);
                Toast.makeText(getActivity(),"카테고리 열기",Toast.LENGTH_SHORT).show();
            }
        });

        categoryView = (TextView) view.findViewById(R.id.categoryView);
        ch1 = (CheckBox) view.findViewById(R.id.checkBox1);
        ch2 = (CheckBox) view.findViewById(R.id.checkBox2);
        ch3 = (CheckBox) view.findViewById(R.id.checkBox3);
        ch4 = (CheckBox) view.findViewById(R.id.checkBox4);
        ch5 = (CheckBox) view.findViewById(R.id.checkBox5);
        ch6 = (CheckBox) view.findViewById(R.id.checkBox6);
        ch7 = (CheckBox) view.findViewById(R.id.checkBox7);
        ch8 = (CheckBox) view.findViewById(R.id.checkBox8);
        ch9 = (CheckBox) view.findViewById(R.id.checkBox9);
        ch10 = (CheckBox) view.findViewById(R.id.checkBox10);
        ch11 = (CheckBox) view.findViewById(R.id.checkBox11);
        ch12 = (CheckBox) view.findViewById(R.id.checkBox12);
        ch13 = (CheckBox) view.findViewById(R.id.checkBox13);
        ch14 = (CheckBox) view.findViewById(R.id.checkBox14);
        ch15 = (CheckBox) view.findViewById(R.id.checkBox15);
        ch16 = (CheckBox) view.findViewById(R.id.checkBox16);
        catagoryBtn = (Button) view.findViewById(R.id.catagoryBtn);
        catagoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "";
                if(ch1.isChecked()) result += ch1.getText()+", ";
                if(ch2.isChecked()) result += ch2.getText()+", ";
                if(ch3.isChecked()) result += ch3.getText()+", ";
                if(ch4.isChecked()) result += ch4.getText()+", ";
                if(ch5.isChecked()) result += ch5.getText()+", ";
                if(ch6.isChecked()) result += ch6.getText()+", ";
                if(ch7.isChecked()) result += ch7.getText()+", ";
                if(ch8.isChecked()) result += ch8.getText()+", ";
                if(ch9.isChecked()) result += ch9.getText()+", ";
                if(ch10.isChecked()) result += ch10.getText()+", ";
                if(ch11.isChecked()) result += ch11.getText()+", ";
                if(ch12.isChecked()) result += ch12.getText()+", ";
                if(ch13.isChecked()) result += ch13.getText()+", ";
                if(ch14.isChecked()) result += ch14.getText()+", ";
                if(ch15.isChecked()) result += ch15.getText()+", ";
                if(ch16.isChecked()) result += ch16.getText()+" ";
                if(result == "") result = "적용된 카테고리가 없습니다.";

                categoryView.setText(result);
                Toast.makeText(getActivity(),"카테고리를 적용합니다.",Toast.LENGTH_SHORT).show();
            }
        });

        closeBtn = (Button) view.findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foldingCell.toggle(false);
                Toast.makeText(getActivity(),"카테고리 닫기",Toast.LENGTH_SHORT).show();
            }
        });

        ecoDetailViewFragment = new EcoDetailViewFragment();
        detailBtn1 = (Button) view.findViewById(R.id.detailBtn1);
        detailBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, ecoDetailViewFragment).addToBackStack(null).commit();
                Toast.makeText(getActivity(),"첫번째 결과물 더보기",Toast.LENGTH_SHORT).show();
            }
        });

        detailBtn2 = (Button) view.findViewById(R.id.detailBtn2);
        detailBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, ecoDetailViewFragment).addToBackStack(null).commit();
                Toast.makeText(getActivity(),"두번째 결과물 더보기",Toast.LENGTH_SHORT).show();
            }
        });
        
        return view;
    }
}

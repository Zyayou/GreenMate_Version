package com.example.greenmate_front3.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.greenmate_front3.R;
import com.example.greenmate_front3.activity.EditActivity;
import com.example.greenmate_front3.activity.LoginActivity;
import com.example.greenmate_front3.activity.WithdrawalActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyFragment extends Fragment{
    private LinearLayout editprofileLayout, reviewLayout, withdrawalLayout, logoutLayout;
    private CircleImageView profileImg;
    private TextView profileName, profileRpt;
    private ProgressBar profileGrade;
    public MyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.fragment_my, container, false);

        //아이디값 찾아주기
        profileImg = view.findViewById(R.id.userImg);
        profileName = view.findViewById(R.id.userName);
        profileRpt = view.findViewById(R.id.userCount);
        profileGrade = view.findViewById(R.id.userLevel);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String userName = preferences.getString("m_name", "");
        String userRpt = preferences.getString("m_rpt", "");
        String userGrade = preferences.getString("m_grade", "");

        profileName.setText(userName);
        profileRpt.setText(userRpt);
        if(Integer.parseInt(userGrade)<100){
            profileGrade.setProgress(Integer.parseInt(userGrade));
        }else{
            profileGrade.setProgress(100);
        }

        //정보 수정
        editprofileLayout = view.findViewById(R.id.editprofileLayout);
        editprofileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditActivity.class);
                startActivity(intent);
            }
        });

        //리뷰관리
        reviewLayout = view.findViewById(R.id.reviewLayout);
        reviewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"준비중",Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getActivity(), ReviewActivity.class);
                //startActivity(intent);
            }
        });

        //회원탈퇴
        withdrawalLayout = view.findViewById(R.id.withdrawalLayout);
        withdrawalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WithdrawalActivity.class);
                startActivity(intent);
            }
        });

        //로그아웃
        logoutLayout = view.findViewById(R.id.logoutLayout);
        logoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("m_id");
                editor.remove("m_pw");
                editor.remove("m_name");
                editor.remove("m_email");
                editor.remove("m_birth");
                editor.remove("m_phone");
                editor.remove("m_rpt");
                editor.remove("m_img");
                editor.remove("m_grade");
                editor.remove("q_state");
                editor.remove("q_rand");
                editor.putBoolean("isLoggedIn", false);  // 로그인 상태를 저장
                editor.apply();

                Toast.makeText(getActivity(),"로그아웃되었습니다.",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
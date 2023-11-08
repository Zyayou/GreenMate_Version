package com.example.greenmate_front3;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class HomeFragment extends Fragment {
    private LinearLayout ecodetailLayout, ecosearchLayout, ecoplaceLayout;
    private ViewPager2 mPagerBanner, mPagerNews;
    private SliderAdapter pagerAdapterBanner, pagerAdapterNews;
    private CircleIndicator3 mIndicator1;
    private int num_page = 4;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.fragment_home, container, false);

        // ecodetailLayout 레이아웃 (분리수거방법)
        ecodetailLayout = view.findViewById(R.id.ecodetailLayout);
        ecodetailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"분리수거방법",Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getActivity(), LoginActivity.class);
                //startActivity(intent);
            }
        });

        // ecosearchLayout 레이아웃 (친환경제품)
        ecosearchLayout = view.findViewById(R.id.ecosearchLayout);
        ecosearchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"친환경제품",Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getActivity(), LoginActivity.class);
                //startActivity(intent);
            }
        });

        // ecoplaceLayout 레이아웃 (지역별배출요령)
        ecoplaceLayout = view.findViewById(R.id.ecoplaceLayout);
        ecoplaceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"지역별배출요령",Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getActivity(), LoginActivity.class);
                //startActivity(intent);
            }
        });


        // 슬라이드 배너
        // ViewPager2 Setting
        mPagerBanner = view.findViewById(R.id.sliderViewPagerBanner);

        // Adapter Setting
        pagerAdapterBanner = new SliderAdapter(requireActivity(), num_page, 1);
        mPagerBanner.setAdapter(pagerAdapterBanner);

        // Indicator Setting
        mIndicator1 = view.findViewById(R.id.indicatorBanner);
        mIndicator1.setViewPager(mPagerBanner);
        mIndicator1.createIndicators(num_page,0);

        // ViewPager Setting
        mPagerBanner.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        mPagerBanner.setCurrentItem(1000); // 시작 지점
        mPagerBanner.setOffscreenPageLimit(4); // 최대 이미지 수
        mPagerBanner.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mPagerBanner.setCurrentItem(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mIndicator1.animatePageSelected(position % num_page);
            }
        });


        // 슬라이드 배너 뉴스
        // ViewPager2 Setting
        mPagerNews = view.findViewById(R.id.sliderViewPagerNews);

        // Adapter Setting
        pagerAdapterNews = new SliderAdapter(requireActivity(), num_page, 2);
        mPagerNews.setAdapter(pagerAdapterNews);

        // ViewPager Setting
        mPagerNews.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        // ViewPager2 커스텀 설정
        mPagerNews.setClipToPadding(false);
        mPagerNews.setClipChildren(false);
        mPagerNews.setOffscreenPageLimit(1); // 최대 이미지 수

        return view;
    }
}
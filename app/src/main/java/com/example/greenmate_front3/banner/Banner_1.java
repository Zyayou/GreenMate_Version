package com.example.greenmate_front3.banner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.greenmate_front3.R;

public class Banner_1 extends Fragment {
    private ImageView imgBanner1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.banner_1, container, false);

        imgBanner1 = rootView.findViewById(R.id.imgBanner1);
        imgBanner1.setClipToOutline(true);

        return rootView;
    }
}
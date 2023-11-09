package com.example.greenmate_front3.banner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.greenmate_front3.R;

public class Banner_4 extends Fragment {
    private ImageView imgBanner4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.banner_4, container, false);

        imgBanner4 = rootView.findViewById(R.id.imgBanner4);
        imgBanner4.setClipToOutline(true);

        return rootView;
    }
}
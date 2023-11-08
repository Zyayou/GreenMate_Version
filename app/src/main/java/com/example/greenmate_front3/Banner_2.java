package com.example.greenmate_front3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class Banner_2 extends Fragment {
    private ImageView imgBanner2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.banner_2, container, false);

        imgBanner2 = rootView.findViewById(R.id.imgBanner2);
        imgBanner2.setClipToOutline(true);

        return rootView;

    }
}
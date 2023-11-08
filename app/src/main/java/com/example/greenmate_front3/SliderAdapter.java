package com.example.greenmate_front3;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.fragment.app.Fragment;

public class SliderAdapter extends FragmentStateAdapter {
    public int mCount, mVer;

    public SliderAdapter(FragmentActivity fa, int count, int ver) {
        super(fa);
        mCount = count;
        mVer = ver;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);

        if(mVer ==1){
            if(index==0) return new Banner_1();
            else if(index==1) return new Banner_2();
            else if(index==2) return new Banner_3();
            else return new Banner_4();
        }
        else if (mVer ==2) {
            if(index==0) return new News_1();
            else if(index==1) return new News_2();
            else if(index==2) return new News_3();
            else return new News_4();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        if(mVer ==1) return 2000;
        else if (mVer ==2) return mCount;
        return mCount;
    }

    public int getRealPosition(int position) { return position % mCount; }

}

/* 예전 버전1

package com.example.greenmate;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.fragment.app.Fragment;

public class SliderAdapter extends FragmentStateAdapter {

    public int mCount;

    public SliderAdapter(FragmentActivity fa, int count) {
        super(fa);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);

        if(index==0) return new Banner_1();
        else if(index==1) return new Banner_2();
        else if(index==2) return new Banner_3();
        else return new Banner_4();
    }

    @Override
    public int getItemCount() {
        return 2000;
    }

    public int getRealPosition(int position) { return position % mCount; }

}

*/
/* 버전2 - 성공은 했음 다만 자동이 아닐뿐임
package com.example.greenmate;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.fragment.app.Fragment;

public class SliderAdapter extends FragmentStateAdapter {

    public int mCount;
    private Context context;

    public SliderAdapter(FragmentActivity fa, int count) {
        super(fa);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);

        if(index==0) return new Banner_1();
        else if(index==1) return new Banner_2();
        else if(index==2) return new Banner_3();
        else return new Banner_4();
    }

    @Override
    public int getItemCount() {
        return 2000;
    }

    public int getRealPosition(int position) { return position % mCount; }

}
*/
/* 버전3
package com.example.greenmate;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.fragment.app.Fragment;

public class SliderAdapter extends FragmentStateAdapter {
    public int mCount, mVer;

    public SliderAdapter(FragmentActivity fa, int count, int ver) {
        super(fa);
        mCount = count;
        mVer = ver;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);

        if(mVer ==1){
            if(index==0) return new Banner_1();
            else if(index==1) return new Banner_2();
            else if(index==2) return new Banner_3();
            else return new Banner_4();
        }
        else if (mVer ==2) {
            if(index==0) return new News_1();
            else if(index==1) return new News_2();
            else if(index==2) return new News_3();
            else return new News_4();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        if(mVer ==1) return 2000;
        else if (mVer ==2) return mCount;
        return mCount;
    }

    public int getRealPosition(int position) { return position % mCount; }

}
*/
package com.example.lee.lumusic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;


/**
 * Created by lee on 17-6-3.
 */

public abstract class BaseFragment extends Fragment {
    private Context mContext;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this.getActivity();

    }
}

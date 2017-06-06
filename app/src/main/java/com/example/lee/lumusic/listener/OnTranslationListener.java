package com.example.lee.lumusic.listener;

import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;

import com.example.lee.lumusic.musicbean.ArtistInfo;

import java.util.ArrayList;

/**
 * Created by lee on 17-6-5.
 */

public interface  OnTranslationListener {
    void loadSuccess(String url);

    void loadFailed(Object object);
}

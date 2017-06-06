package com.example.lee.lumusic.rx;

import com.example.lee.lumusic.listener.OnTranslationListener;
import com.example.lee.lumusic.musicbean.ArtistBean;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by lee on 17-6-5.
 */

public class RxgetData<T> {

    private OnTranslationListener listener;

    public static final int music_type=001;


    public RxgetData(OnTranslationListener listener) {
        this.listener = listener;
    }

    public void getDataByType(int type){

    }
}

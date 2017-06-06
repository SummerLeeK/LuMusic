package com.example.lee.lumusic.viewmodel;

import android.util.Log;

import com.example.lee.lumusic.listener.OnTranslationListener;
import com.example.lee.lumusic.listener.RequestSingerImg;
import com.example.lee.lumusic.musicbean.ArtistInfo;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lee on 17-6-6.
 */

public class ArtistModel {


    public ArtistModel(final OnTranslationListener listener, String name) {

        Retrofit.Builder builder = new Retrofit.Builder();
        final Retrofit retrofit = builder.
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                addConverterFactory(GsonConverterFactory.create())
                .baseUrl(RequestSingerImg.BASE_URL).build();
        RequestSingerImg requestSingerImg = retrofit.create(RequestSingerImg.class);
        requestSingerImg.getArtist("artist.getinfo", "fdb3a51437d4281d4d64964d333531d4", "json", name)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ArtistInfo>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ArtistInfo artistInfo) {

                if (artistInfo.mArtist != null && artistInfo.mArtist.mArtwork != null) {
                    listener.loadSuccess(artistInfo.mArtist.mArtwork.get(2).mUrl);
                }

            }

            @Override
            public void onError(@NonNull Throwable e) {
                listener.loadFailed(e);
            }

            @Override
            public void onComplete() {

            }
        });

    }
}

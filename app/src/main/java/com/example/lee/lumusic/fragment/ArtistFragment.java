package com.example.lee.lumusic.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lee.lumusic.R;
import com.example.lee.lumusic.adapter.ArtistAdapter;
import com.example.lee.lumusic.musicbean.ArtistBean;
import com.example.lee.lumusic.utils.AlbumListLoader;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lee on 17-6-3.
 */

public class ArtistFragment extends BaseFragment {

    @Bind(R.id.artist)
    RecyclerView artist;

    private Context mContext;

    private ArtistAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_artist, null);
        ButterKnife.bind(this, root);

        mContext = getActivity();

        Observable<ArrayList<ArtistBean>> observable = Observable.create(new ObservableOnSubscribe<ArrayList<ArtistBean>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<ArrayList<ArtistBean>> e) throws Exception {
                e.onNext(AlbumListLoader.getArtistAll(mContext));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<ArrayList<ArtistBean>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ArrayList<ArtistBean> artistBeen) {
                adapter = new ArtistAdapter(artistBeen, mContext);

                GridLayoutManager gridLayoutManager=new GridLayoutManager(mContext,2);
                artist.setLayoutManager(gridLayoutManager);

                artist.addItemDecoration(new RecyclerView.ItemDecoration() {
                    @Override
                    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                        super.onDraw(c, parent, state);
                    }

                    @Override
                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

                        outRect.bottom=4;
                        outRect.top=4;
                        outRect.left=4;
                        outRect.right=4;
                    }
                });
                artist.setAdapter(adapter);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


        return root;
    }

}

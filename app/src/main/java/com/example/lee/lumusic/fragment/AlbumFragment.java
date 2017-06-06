package com.example.lee.lumusic.fragment;

import android.content.Context;
import android.databinding.ViewDataBinding;
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
import com.example.lee.lumusic.adapter.AlbumAdapter;
import com.example.lee.lumusic.listener.RequestSingerImg;
import com.example.lee.lumusic.musicbean.AlbumBean;
import com.example.lee.lumusic.musicbean.ArtistInfo;
import com.example.lee.lumusic.utils.AlbumListLoader;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

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
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lee on 17-6-3.
 */

public class AlbumFragment extends BaseFragment {
    private ViewDataBinding dataBinding;

    @Bind(R.id.album_list)
    RecyclerView albun_list;
    private Context mContext;
    private AlbumAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_album,null);
        ButterKnife.bind(this,root);

        mContext=getActivity();


        Observable<ArrayList<AlbumBean>> observable=Observable.create(new ObservableOnSubscribe<ArrayList<AlbumBean>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<ArrayList<AlbumBean>> e) throws Exception {
                ArrayList<AlbumBean> albumBeen= AlbumListLoader.getAllAlbums(mContext);
                e.onNext(albumBeen);
                e.onComplete();

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<ArrayList<AlbumBean>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ArrayList<AlbumBean> albumBeen) {


                adapter=new AlbumAdapter(albumBeen,mContext);

                GridLayoutManager gridLayoutManager=new GridLayoutManager(mContext,2);
                albun_list.setLayoutManager(gridLayoutManager);

                albun_list.addItemDecoration(new RecyclerView.ItemDecoration() {
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
                albun_list.setAdapter(adapter);
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

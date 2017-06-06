package com.example.lee.lumusic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lee.lumusic.R;
import com.example.lee.lumusic.adapter.MuscicListAdapter;
import com.example.lee.lumusic.musicbean.MusicBean;
import com.example.lee.lumusic.utils.PlaylistLoader;

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

public class MusicFragment extends BaseFragment {
    @Bind(R.id.list_item)
    ListView listView;

    private MuscicListAdapter adapter;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.music_fragment, null);
        ButterKnife.bind(this,root);

        mContext=this.getActivity();

        Observable<ArrayList<MusicBean>> observable=Observable.create(new ObservableOnSubscribe<ArrayList<MusicBean>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<ArrayList<MusicBean>> e) throws Exception {
                ArrayList<MusicBean> aa=PlaylistLoader.getPlaylists(mContext);

                e.onNext(aa);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<ArrayList<MusicBean>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ArrayList<MusicBean> musicBeen) {

                adapter = new MuscicListAdapter(musicBeen, mContext);

                listView.setAdapter(adapter);
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

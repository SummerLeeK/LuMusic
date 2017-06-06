package com.example.lee.lumusic.adapter;

import android.content.ContentUris;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lee.lumusic.BR;
import com.example.lee.lumusic.R;
import com.example.lee.lumusic.musicbean.MusicBean;

import java.io.File;
import java.util.ArrayList;


/**
 * Created by lee on 17-6-3.
 */

public class MuscicListAdapter extends BaseAdapter {

    private ArrayList<MusicBean> songs;
    private LayoutInflater layoutInflater;
    private Context mContext;
    private ImageView image;

    private ViewDataBinding dataBinding;


    public MuscicListAdapter(ArrayList<MusicBean> songs, Context context) {
        this.songs = songs;
        this.mContext = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int position) {
        return songs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.music_list_item, null);
        }


        Uri uri = ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"),
                songs.get(position).album_id);
        image = (ImageView) convertView.findViewById(R.id.image);
        Glide.with(mContext).load(uri).into(image);

        dataBinding = DataBindingUtil.bind(convertView);

        dataBinding.setVariable(BR.music, songs.get(position));

        return convertView;
    }
}

package com.example.lee.lumusic.adapter;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.lee.lumusic.R;
import com.example.lee.lumusic.listener.RequestSingerImg;
import com.example.lee.lumusic.musicbean.AlbumBean;
import com.example.lee.lumusic.musicbean.ArtistInfo;
import com.example.lee.lumusic.viewholder.AlbumViewHolder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lee on 17-6-5.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder> {
    private ArrayList<AlbumBean> albums;
    ArrayList<ArtistInfo> artistInfos = new ArrayList<ArtistInfo>();

    private Context mContext;

    public AlbumAdapter(ArrayList<AlbumBean> albums, Context mContext) {
        this.albums = albums;
        this.mContext = mContext;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AlbumViewHolder viewHolder = new AlbumViewHolder(LayoutInflater.from(mContext).inflate(R.layout.album_list_item, null));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AlbumViewHolder holder, int position) {





        Uri uri = ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), albums.get(position)._id);

        Bitmap bitmap = BitmapFactory.decodeFile(getImagePath(uri, null));

        Palette.Builder builder = Palette.from(bitmap);

        builder.generate(new Palette.PaletteAsyncListener() {

            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch swatch = palette.getVibrantSwatch();
                if (swatch != null) {

                    int color = swatch.getRgb();
                    holder.linearLayout.setBackgroundColor(color);
                    int textColor = swatch.getTitleTextColor();
                    holder.singer.setTextColor(textColor);
                    holder.album_name.setTextColor(textColor);
                } else {
                    Palette.Swatch swatch2 = palette.getMutedSwatch();
                    if (swatch2 != null) {
                        int color = swatch2.getRgb();
                        int textColor = swatch2.getTitleTextColor();
                        holder.linearLayout.setBackgroundColor(color);
                        holder.singer.setTextColor(textColor);
                        holder.album_name.setTextColor(textColor);
                    }


                }
            }
        });


       Glide.with(mContext).load(uri).into(holder.album);
        holder.singer.setText(albums.get(position).artist);
        holder.album_name.setText(albums.get(position).album);


    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = mContext.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }

            cursor.close();
        }
        return path;
    }
}

package com.example.lee.lumusic.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.lee.lumusic.R;
import com.example.lee.lumusic.listener.OnTranslationListener;
import com.example.lee.lumusic.musicbean.ArtistBean;
import com.example.lee.lumusic.viewholder.AlbumViewHolder;
import com.example.lee.lumusic.viewmodel.ArtistModel;
import java.util.ArrayList;



/**
 * Created by lee on 17-6-5.
 */

public class ArtistAdapter extends RecyclerView.Adapter<AlbumViewHolder> {

    private ArrayList<ArtistBean> artists;

    private Context mContext;

    public ArtistAdapter(ArrayList<ArtistBean> artists, Context mContext) {
        this.artists = artists;
        this.mContext = mContext;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        AlbumViewHolder viewHolder = new AlbumViewHolder(LayoutInflater.from(mContext).inflate(R.layout.album_list_item, null));


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AlbumViewHolder holder, final int position) {


        holder.album_name.setText(artists.get(position).artist);
        holder.singer.setText(artists.get(position).number_of_albums + "张专辑 | " + artists.get(position).number_of_tracks + "首歌");

        final ArtistModel model = new ArtistModel(new OnTranslationListener() {
            @Override
            public void loadSuccess(String url) {


                Glide.with(mContext).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                        holder.album.setImageBitmap(resource);
                        Palette.Builder palette_build = Palette.from(resource);
                        palette_build.generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette palette) {
                                Palette.Swatch swatch = palette.getDarkVibrantSwatch();
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
                    }


                });
            }

            @Override
            public void loadFailed(Object object) {

                Log.i("info", object.toString());

            }
        }, artists.get(position).artist);

    }

    @Override
    public int getItemCount() {
        return artists.size();
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

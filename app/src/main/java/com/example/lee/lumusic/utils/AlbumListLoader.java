package com.example.lee.lumusic.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.lee.lumusic.musicbean.AlbumBean;
import com.example.lee.lumusic.musicbean.ArtistBean;
import com.example.lee.lumusic.musicbean.MusicBean;

import java.util.ArrayList;

/**
 * Created by lee on 17-6-5.
 */

public class AlbumListLoader {
    static ArrayList<AlbumBean> albun_list;
    private static Cursor mCursor;

    static ArrayList<ArtistBean> artistBeen;

    public static ArrayList<AlbumBean> getAllAlbums(Context context) {
        mCursor = getAlbunCursor(context);
        albun_list = new ArrayList<>();

        if (mCursor != null && mCursor.moveToFirst()) {
            do {

                AlbumBean albumBean = new AlbumBean(
                        mCursor.getLong(0),
                        mCursor.getString(1),
                        mCursor.getString(2),
                        mCursor.getLong(3),
                        mCursor.getInt(4),
                        mCursor.getInt(5)
                      );


                albun_list.add(albumBean);
            } while (mCursor.moveToNext());
        }
        if (mCursor != null) {
            mCursor.close();
            mCursor = null;
        }
        return albun_list;
    }


    public static Cursor getAlbunCursor(Context context) {

        return context.getContentResolver().query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, new String[]{
                "_id", "album", "artist", "artist_id", "numsongs", "minyear"
        }, null, null, MediaStore.Audio.Albums.DEFAULT_SORT_ORDER);
    }



    public static Cursor getArtistCursor(Context context){
        return context.getContentResolver().query(MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,new String[]{
                "_id", "artist", "number_of_albums", "number_of_tracks"
        },null,null,MediaStore.Audio.Artists.DEFAULT_SORT_ORDER);
    }


    public static ArrayList<ArtistBean> getArtistAll(Context context){

        mCursor=getArtistCursor(context);
        artistBeen=new ArrayList<>();


        if (mCursor != null && mCursor.moveToFirst()) {
            do {

                ArtistBean artistBean = new ArtistBean(
                        mCursor.getLong(0),
                        mCursor.getString(1),
                        mCursor.getInt(2),
                        mCursor.getInt(3)
                );


                artistBeen.add(artistBean);
            } while (mCursor.moveToNext());
        }
        if (mCursor != null) {
            mCursor.close();
            mCursor = null;
        }
        return artistBeen;

    }
}

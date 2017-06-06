package com.example.lee.lumusic.utils;/*
 * Copyright (C) 2015 Naman Dwivedi
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.lee.lumusic.musicbean.MusicBean;

import java.util.ArrayList;


public class PlaylistLoader {

    static ArrayList<MusicBean> mPlaylistList;
    private static Cursor mCursor;

    public static ArrayList<MusicBean> getPlaylists(Context context) {

        mPlaylistList = new ArrayList<>();

        mCursor = makePlaylistCursor(context);

        if (mCursor != null && mCursor.moveToFirst()) {
            do {

                MusicBean musicBean = new MusicBean(
                        mCursor.getLong(0),
                        mCursor.getString(1),
                        mCursor.getString(2),
                        mCursor.getString(3),
                        mCursor.getString(4),
                        mCursor.getString(5),
                        mCursor.getLong(6),
                        mCursor.getLong(7));


                mPlaylistList.add(musicBean);
            } while (mCursor.moveToNext());
        }
        if (mCursor != null) {
            mCursor.close();
            mCursor = null;
        }
        return mPlaylistList;
    }

    private static void makeDefaultPlaylists(Context context) {

    }


    public static final Cursor makePlaylistCursor(final Context context) {
        return context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{
                        "_id", "title", "artist", "album", "duration", "track", "artist_id", "album_id"
                }, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
    }

    public static void deletePlaylists(Context context, long playlistId) {
        Uri localUri = MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("_id IN (");
        localStringBuilder.append((playlistId));
        localStringBuilder.append(")");
        context.getContentResolver().delete(localUri, localStringBuilder.toString(), null);
    }
}

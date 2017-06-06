package com.example.lee.lumusic.musicbean;

/**
 * Created by lee on 17-6-5.
 */

public class AlbumBean {
    public long _id;
    public String album;
    public String artist;
    public long artist_id;
    public int numsongs;
    public int minyear;

    public AlbumBean(long _id, String album, String artist, long artist_id, int numsongs, int minyear) {
        this._id = _id;
        this.album = album;
        this.artist = artist;
        this.artist_id = artist_id;
        this.numsongs = numsongs;
        this.minyear = minyear;
    }
}

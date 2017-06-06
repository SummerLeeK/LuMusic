package com.example.lee.lumusic.musicbean;

/**
 * Created by lee on 17-6-3.
 */

public class MusicBean {
// "_id", "title", "artist", "album", "duration", "track", "artist_id", "album_id"

    public long id;
    public String title;
    public String artist;
    public String album;
    public String duration;
    public String track;
    public long artist_id;
    public long album_id;


    public MusicBean(long id, String title, String artist, String album, String duration, String track, long artist_id, long album_id) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.track = track;
        this.artist_id = artist_id;
        this.album_id = album_id;
    }
}

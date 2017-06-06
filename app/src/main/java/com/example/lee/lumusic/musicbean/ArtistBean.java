package com.example.lee.lumusic.musicbean;

/**
 * Created by lee on 17-6-6.
 */

public class ArtistBean {
//      "_id", "artist", "number_of_albums", "number_of_tracks"
    public long id;
    public String artist;
    public int number_of_albums;
    public int number_of_tracks;

    public ArtistBean(long id, String artist, int number_of_albums, int number_of_tracks) {
        this.id = id;
        this.artist = artist;
        this.number_of_albums = number_of_albums;
        this.number_of_tracks = number_of_tracks;
    }
}

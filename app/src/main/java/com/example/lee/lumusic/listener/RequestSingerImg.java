package com.example.lee.lumusic.listener;

import com.example.lee.lumusic.musicbean.ArtistInfo;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;



/**
 * Created by lee on 17-6-6.
 */

public interface RequestSingerImg {


    String BASE_URL = "https://ws.audioscrobbler.com";
    String BASE_URL_ARTIST = "?method=artist.getinfo&api_key=fdb3a51437d4281d4d64964d333531d4&format=json";

    @Headers("Cache-Control: public")
    @GET("/2.0/")
    Observable<ArtistInfo> getArtist(@Query("method") String method,@Query("api_key") String api_key,@Query("format") String format,@Query("artist") String artist);

}

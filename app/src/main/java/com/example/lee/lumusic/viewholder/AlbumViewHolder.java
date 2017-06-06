package com.example.lee.lumusic.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lee.lumusic.R;

/**
 * Created by lee on 17-6-5.
 */

public class AlbumViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout linearLayout;
    public ImageView album;
    public TextView album_name;
    public TextView singer;
    public AlbumViewHolder(View itemView) {
        super(itemView);

        linearLayout= (LinearLayout) itemView.findViewById(R.id.linear);
        album= (ImageView) itemView.findViewById(R.id.album_img);
        album_name= (TextView) itemView.findViewById(R.id.album_name);
        singer= (TextView) itemView.findViewById(R.id.singer);
    }

}

package com.example.lee.lumusic;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lee.lumusic.adapter.ViewPagerAdapter;
import com.example.lee.lumusic.fragment.AlbumFragment;
import com.example.lee.lumusic.fragment.ArtistFragment;
import com.example.lee.lumusic.fragment.BottomFragment;
import com.example.lee.lumusic.fragment.MusicFragment;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, SlidingUpPanelLayout.PanelSlideListener {
    @Bind(R.id.slideing)
    SlidingUpPanelLayout slidingUpPanelLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.drawer)
    DrawerLayout drawerLayout;
    @Bind(R.id.viewpager)
    ViewPager pager;
    private ActionBarDrawerToggle toggle;

    @Bind(R.id.tab)
    TabLayout tabLayout;
    private TabLayout.Tab music;
    private TabLayout.Tab artist;
    private TabLayout.Tab album;

    private ArrayList<Fragment> fragments;

    private ViewPagerAdapter adapter;
    private BottomFragment bottomFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);



        slidingUpPanelLayout.addPanelSlideListener(this);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);

        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();


        music = tabLayout.newTab().setText("单曲");
        artist = tabLayout.newTab().setText("艺术家");
        album = tabLayout.newTab().setText("专辑");

        tabLayout.addTab(music);
        tabLayout.addTab(album);
        tabLayout.addTab(artist);

        tabLayout.setOnTabSelectedListener(this);
//
        fragments = new ArrayList<>();
        MusicFragment musicFragment = new MusicFragment();
        AlbumFragment albumFragment = new AlbumFragment();
        ArtistFragment artistFragment = new ArtistFragment();

        fragments.add(musicFragment);
        fragments.add(albumFragment);
        fragments.add(artistFragment);

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

        bottomFragment=new BottomFragment();
        manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.quickcontrols_container,bottomFragment);
        transaction.commit();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            },002);
        }

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab == tabLayout.getTabAt(0)) {
            pager.setCurrentItem(0);
        } else if (tab == tabLayout.getTabAt(1)) {
            pager.setCurrentItem(1);
        } else if (tab == tabLayout.getTabAt(2)) {
            pager.setCurrentItem(2);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 002:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "请求成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "请求失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onPanelSlide(View panel, float slideOffset) {

    }

    @Override
    public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

        }

        return super.onOptionsItemSelected(item);
    }
}

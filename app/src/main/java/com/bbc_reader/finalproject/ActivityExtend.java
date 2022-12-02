package com.bbc_reader.finalproject;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bbc_reader.finalproject.dialog.DialogHelp;
import com.google.android.material.navigation.NavigationView;

public class ActivityExtend extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private ActivityExtend current;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    public void setCurrent(ActivityExtend current) {
        this.current = current;
    }

    public void initToolbar(String titlePage){
        this.title = titlePage;
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(titlePage);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeButtonEnabled(true);
    }


    public void initDrawerLayout(){
        mDrawerLayout = findViewById(R.id.dl_main);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.nav_open, R.string.nav_close){
            public void onDrawerClosed(View drawer){
                super.onDrawerClosed(drawer);
                getSupportActionBar().setTitle(title);
                invalidateOptionsMenu();
            }
            public void onDrawerOpened(View drawer){
                super.onDrawerOpened(drawer);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.addDrawerListener(toggle);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_menu_version:
                getSupportActionBar().setTitle(navigationView.getMenu().findItem(R.id.nav_menu_version).getTitle());
                break;
            case R.id.nav_menu_home:
                if(!(this.current instanceof MainActivity)) startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            case R.id.nav_menu_favorite:
                if(!(this.current instanceof FavoriteActivity)) startActivity(new Intent(getApplicationContext(), FavoriteActivity.class));
                break;
            case R.id.nav_add_feed:
                if(!(this.current instanceof FeedAddActivity)) startActivityForResult(new Intent(getApplicationContext(), FeedAddActivity.class), 100);
                break;
            case R.id.nav_menu_sign_in:
                startActivity(new Intent(getApplicationContext(), AuthActivity.class));
                break;
            default:
                finishAffinity();
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_home:
                if(!(this.current instanceof MainActivity)) startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            case R.id.btn_favorite:
                if(!(this.current instanceof FavoriteActivity)) startActivity(new Intent(getApplicationContext(), FavoriteActivity.class));
                break;
            case R.id.btn_help:
                Integer res = R.drawable.help_home;
                if(this.current instanceof FavoriteActivity) res =  R.drawable.help_favorite;
                else if (this.current instanceof FeedDetailActivity) res = R.drawable.help_detail;
                else if (this.current instanceof FeedAddActivity) res = R.drawable.help_add_source;
                new DialogHelp(this, res).show();
                break;
            default:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }
}

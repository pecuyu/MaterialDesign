package com.yu.materialdesign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.id_tool_bar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.id_layout_drawer);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        }

        FloatingActionButton floatButton = (FloatingActionButton) findViewById(R.id.id_floating_btn);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "FloatingActionButton",Toast.LENGTH_SHORT).show();
                Snackbar.make(v, "data delete", Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "data restored", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });

        NavigationView nv = (NavigationView) findViewById(R.id.id_nav_view);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.id_nav_friends:
                        Toast.makeText(MainActivity.this, "nav_friends", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.id_nav_call:
                        Toast.makeText(MainActivity.this, "nav_call", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.id_nav_location:
                        Toast.makeText(MainActivity.this, "nav_location", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.id_nav_mail:
                        Toast.makeText(MainActivity.this, "nav_mail", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.id_nav_task:
                        Toast.makeText(MainActivity.this, "nav_task", Toast.LENGTH_SHORT).show();
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_menu:
                Toast.makeText(this, "Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_delete:
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_comment:
                Toast.makeText(this, "Comment", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                break;
        }

        return super.onOptionsItemSelected(item);

    }
}

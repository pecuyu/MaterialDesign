package com.yu.materialdesign;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawer;
    RecyclerView recyclerView;
    SearchView searchView;
    FloatingActionButton floatButton;
    NavigationView nv;
    SwipeRefreshLayout refresh;
    private Fruit[] fruits = {new Fruit("Apple", R.mipmap.apple), new Fruit("Banana", R.mipmap.banana),
            new Fruit("Orange", R.mipmap.orange), new Fruit("Watermelon", R.mipmap.watermelon),
            new Fruit("Pear", R.mipmap.pear), new Fruit("Grape", R.mipmap.grape),
            new Fruit("Pineapple", R.mipmap.pineapple), new Fruit("Strawberry", R.mipmap.strawberry),
            new Fruit("Cherry", R.mipmap.cherry), new Fruit("Mango", R.mipmap.mango)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initEvents();
    }

    private void initEvents() {
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

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {  /*主线程*/
                refreshLayout(refresh);
            }
        });

    }

    private void initViews() {
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.id_tool_bar);
        drawer = (DrawerLayout) findViewById(R.id.id_layout_drawer);
        recyclerView = (RecyclerView) findViewById(R.id.id_rv_list);
        nv = (NavigationView) findViewById(R.id.id_nav_view);
        floatButton = (FloatingActionButton) findViewById(R.id.id_floating_btn);
        refresh = (SwipeRefreshLayout) findViewById(R.id.id_srl_list);
        refresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);

        initSupportActionBar();

        initRecyclerView();
    }

    private void initRecyclerView() {
        CardViewAdapter adapter = new CardViewAdapter(fruits, this);
//        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(adapter);
    }


    private void initSupportActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        }
    }

    public void refreshLayout(final SwipeRefreshLayout refresh) {
        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(5000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refresh.setRefreshing(false);
                    }
                });
                super.run();
            }
        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        setMenuIconEnable(menu,true);
        MenuItem searchItem = menu.findItem(R.id.id_search_view);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getApplicationContext(), "text=" + newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
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



    /**
     * 设置条目图标显示
     * @param menu
     * @param enable
     */
    protected void setMenuIconEnable(Menu menu, boolean enable) {
        try {
            Class<?> clazz = Class.forName("android.support.v7.view.menu.MenuBuilder");
            Method m = clazz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            m.setAccessible(true);

            // MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)
            m.invoke(menu, enable);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

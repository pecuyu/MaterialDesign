package com.yu.materialdesign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.id_tool_bar);
        setSupportActionBar(toolbar);

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
                Toast.makeText(this,"Menu",Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_delete:
                Toast.makeText(this,"Delete",Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_comment:
                Toast.makeText(this,"Comment",Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);

    }
}

package com.mas.rssreader;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private SiteDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new SiteDBHandler(this);

        RecyclerView mainFeed = findViewById(R.id.feedContainer);
        mainFeed.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mainFeed.setAdapter(new FeedAdapter(this, dbHandler));

        Log.d("DatabaseLog", dbHandler.getAllSites().toString());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener( v -> {
            Intent i = new Intent(MainActivity.this, FeedAdd.class);
            startActivityForResult(i, 5);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
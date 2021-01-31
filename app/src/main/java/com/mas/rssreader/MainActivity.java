package com.mas.rssreader;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private SiteDBHandler dbHandler;
    private FeedAdapter adapter;
    public final static String DBLOG = "DatabaseLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new SiteDBHandler(this);

        RecyclerView mainFeed = findViewById(R.id.feedContainer);
        mainFeed.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new FeedAdapter(this, dbHandler);
        mainFeed.setAdapter(adapter);

        Log.d(DBLOG, dbHandler.getAllSites().toString());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener( v -> {
            Intent i = new Intent(MainActivity.this, FeedAdd.class);
            startActivityForResult(i, 5);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 5) {
            assert data != null;
            Site s = new Site(data.getStringExtra(SiteDBHandler.KEY_NAME), data.getStringExtra(SiteDBHandler.KEY_URL));
            dbHandler.addSite(s);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Site added", Toast.LENGTH_SHORT).show();
        }
    }
}
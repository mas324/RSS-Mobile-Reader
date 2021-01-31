package com.mas.rssreader;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FeedAdd extends AppCompatActivity {

    EditText name;
    EditText url;
    Button save;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_add);

        name = findViewById(R.id.newSiteName);
        url = findViewById(R.id.newSiteUrl);
        save = findViewById(R.id.add);

        save.setOnClickListener(v -> {
            Intent i = new Intent();
            i.putExtra(SiteDBHandler.KEY_NAME, name.getText().toString());
            i.putExtra(SiteDBHandler.KEY_URL, url.getText().toString());
            setResult(RESULT_OK, i);
            finish();
        });
    }
}

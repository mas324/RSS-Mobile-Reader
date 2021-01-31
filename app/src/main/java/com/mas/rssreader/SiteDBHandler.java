package com.mas.rssreader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class SiteDBHandler extends SQLiteOpenHelper {

    private static final int DB_V = 3;
    private static final String DB_NAME = "sitesManager";
    private static final String TABLE_SITES = "sites";
    private static final String KEY_ID = "id";
    private static final String KEY_URL = "url";
    private static final String KEY_NAME = "name";

    public SiteDBHandler(Context context) {
        super(context, DB_NAME, null, DB_V);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = String.format("CREATE TABLE %S (%S INTEGER PRIMARY KEY, %S TEXT, %S TEXT)", TABLE_SITES, KEY_ID, KEY_NAME, KEY_URL);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SITES);
        onCreate(db);
    }

    public void addSite(@NonNull Site site) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_URL, site.getUrl());
        values.put(KEY_NAME, site.getName());

        db.insert(TABLE_SITES, null, values);
        db.close();
    }

    public Site getSite(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Site s = null;

        try (Cursor cursor = db.query(TABLE_SITES, new String[]{KEY_ID,
                        KEY_NAME, KEY_URL}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null)) {

            assert cursor != null;
            cursor.moveToFirst();
            s = new Site(cursor.getString(1), cursor.getString(2), cursor.getInt(0));
        } catch (CursorIndexOutOfBoundsException e) {
            s = new Site("Error", "Error");
        }
        return s;
    }

    public List<Site> getAllSites() {
        List<Site> sites = new ArrayList<>();

        String select = "SELECT * FROM " + TABLE_SITES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(select, null);
        if (c.moveToFirst()) {
            do {
                Site s = new Site(c.getString(1), c.getString(2), c.getInt(0));
                sites.add(s);
            } while (c.moveToNext());
        }
        c.close();
        return sites;
    }

    public int updateSite(@NonNull Site site) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, site.getName());
        values.put(KEY_URL, site.getUrl());

        return db.update(TABLE_SITES, values, KEY_ID + " =?", new String[]{String.valueOf(site.getId())});
    }

    public void deleteSite(Site site) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SITES, KEY_ID + " =?", new String[]{String.valueOf(site.getId())});
        db.close();
    }

    public int getSitesCount() {
        String count = "SELECT * FROM " + TABLE_SITES;
        Cursor c = this.getReadableDatabase().rawQuery(count, null);
        final int x = c.getCount();
        c.close();
        return x;
    }
}

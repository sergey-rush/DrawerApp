package com.asna.rush.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.asna.rush.core.Region;

import java.util.ArrayList;
import java.util.List;

public class RegionProvider {

    SQLiteDatabase db;
    public RegionProvider(SQLiteDatabase database) {
        db = database;
    }

    public List<Region> getRegions() {

        Cursor cursor = db.rawQuery("SELECT Id, Name FROM Regions", null);
        cursor.moveToFirst();
        List<Region> Regions = new ArrayList<Region>();

        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex("Id"));
            String name = cursor.getString(cursor.getColumnIndex("Name"));
            Region region = new Region(id, name);
            Regions.add(region);
            cursor.moveToNext();
        }
        return Regions;
    }

    public long insertRegion(Region region) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", region.Id);
        contentValues.put("Name", region.Name);
        return db.insert("Regions", null, contentValues);
    }

    public boolean deleteRegion(int id) {
        db.delete("Regions", "Id = ?", new String[]{id + ""});
        return true;
    }
}

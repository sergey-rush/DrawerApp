package com.asna.rush.drawerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DataManager  extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PILLDB.db";


    public DataManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Sections(Id INTEGER PRIMARY KEY AUTOINCREMENT, ParentId INTEGER, Name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Sections");
        onCreate(db);
    }

    public List<Section> getSections(int parentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT Id, ParentId, Name FROM Sections WHERE ParentId = ?", new String[]{parentId + ""});
        cursor.moveToFirst();
        List<Section> sections  = new ArrayList<Section>();

        while ( !cursor.isAfterLast()) {
            int id= cursor.getInt(cursor.getColumnIndex("Id"));
            int parent= cursor.getInt(cursor.getColumnIndex("ParentId"));
            String name= cursor.getString(cursor.getColumnIndex("Name"));
            Section section = new Section(id,parent, name);
            sections.add(section);
            cursor.moveToNext();
        }
        return sections;
    }

    public long insertSection(int parentId, String name) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("ParentId", parentId);
        contentValues.put("Name", name);

        SQLiteDatabase db = this.getWritableDatabase();
        return db.insert("Sections", null, contentValues);
    }

    public boolean deleteSection(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Sections", "Id = ?", new String[]{id + ""});
        return true;
    }

    public void createDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
    }
}

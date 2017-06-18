package com.asna.rush.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.asna.rush.core.Section;

import java.util.ArrayList;
import java.util.List;

public class SectionProvider {

    SQLiteDatabase db;
    public SectionProvider(SQLiteDatabase database) {
        db = database;
    }

    public List<Section> getSections(int parentId) {
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

    public long insertSection(Section section) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", section.Id);
        contentValues.put("ParentId", section.ParentId);
        contentValues.put("Name", section.Name);
        long ret = db.insert("Sections", null, contentValues);
        return ret;
    }

    public boolean deleteSection(int id) {

        db.delete("Sections", "Id = ?", new String[]{id + ""});
        return true;
    }
}

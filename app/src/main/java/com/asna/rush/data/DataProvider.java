package com.asna.rush.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.asna.rush.core.Region;
import com.asna.rush.core.Section;

import java.util.List;

public class DataProvider {

    Context context;
    SQLiteDatabase db;

    public DataProvider(Context context, SQLiteDatabase database) {
        this.context = context;
        this.db = database;
    }

    public void onCreateDatabaseStructure() {
        db.execSQL("DROP TABLE IF EXISTS Sections");
        db.execSQL("DROP TABLE IF EXISTS Regions");
        Log.d("Drop", "Tables were dropped");
        db.execSQL("CREATE TABLE Sections(Id INTEGER PRIMARY KEY, ParentId INTEGER, Name TEXT)");
        db.execSQL("CREATE TABLE Regions(Id INTEGER PRIMARY KEY, ParentId INTEGER, Name TEXT)");
        Log.d("Drop", "Tables were created");
        onPopulate();
        Log.d("Drop", "Tables were populated");
    }

    private void onPopulate() {

        DataContext dataContext = new DataContext(context);

        List<Region> regions = dataContext.ReadRegions();

        RegionProvider regionProvider = new RegionProvider(db);
        for (Region region:regions) {
            regionProvider.insertRegion(region);
        }

        SectionProvider sectionProvider = new SectionProvider(db);
        List<Section> sections = dataContext.ReadSections();
        for (Section section:sections) {
            long ret = sectionProvider.insertSection(section);
        }
    }
}

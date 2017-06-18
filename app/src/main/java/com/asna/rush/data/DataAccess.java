package com.asna.rush.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataAccess extends SQLiteOpenHelper {

    public static final String DatabaseName = "PILLDB.db";
    public static final int version = 1;
    Context context;

    private DataAccess(Context context) {
        super(context, DatabaseName, null, version);
        this.context = context;
    }

    private static DataAccess _instance = null;
    public static DataAccess getInstance(Context context)
    {
        if(_instance == null)
        {
            _instance = new DataAccess(context);
        }
        return _instance;
    }

    public static SectionProvider Sections()
    {
        return new SectionProvider(_instance.getWritableDatabase());
    }

    public void createDatabase() {

        SQLiteDatabase db = this.getWritableDatabase();
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        DataProvider dataProvider = new DataProvider(context, db);
        dataProvider.onCreateDatabaseStructure();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Sections");
        db.execSQL("DROP TABLE IF EXISTS Regions");
        onCreate(db);
    }
}
package com.hb.bestyoutubeapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Helper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "video.db";

    public static final String VIDEO_KEY = "id";
    public static final String VIDEO_TITLE = "title";
    public static final String VIDEO_DESCRIPTION = "description";
    public static final String VIDEO_URL = "url";
    public static final String VIDEO_CATEGORY = "category";

    public static final String VIDEO_TABLE_NAME = "video";

    public static final int VIDEO_KEY_COLUMN_INDEX = 0;
    public static final int VIDEO_TITLE_COLUMN_INDEX = 1;
    public static final int VIDEO_DESCRIPTION_COLUMN_INDEX = 2;
    public static final int VIDEO_URL_COLUMN_INDEX = 3;
    public static final int VIDEO_CATEGORY_COLUMN_INDEX = 4;

    private static final String VIDEO_TABLE_CREATE =
            "CREATE TABLE " + VIDEO_TABLE_NAME + " ("
                    + VIDEO_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + VIDEO_TITLE + " TEXT NOT NULL, "
                    + VIDEO_DESCRIPTION + " TEXT, "
                    + VIDEO_URL + " TEXT NOT NULL, "
                    + VIDEO_CATEGORY + " TEXT);"
            ;

    private static final String VIDEO_TABLE_DROP =
            "DROP TABLE " + VIDEO_TABLE_NAME + ";";


    public DB_Helper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(VIDEO_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(VIDEO_TABLE_DROP);
        onCreate(sqLiteDatabase);
    }
}

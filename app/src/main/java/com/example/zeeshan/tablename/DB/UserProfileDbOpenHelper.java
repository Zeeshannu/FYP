package com.example.zeeshan.tablename.DB;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by Zeeshan on 11/20/2017.
 */

public class UserProfileDbOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "UserDB";

    private static final String DATABASE_NAME = "Pocketi2Diet.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USER = "userProfile";
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_EMAIL = "title";
    public static final String COLUMN_PASSWORD = "description";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_USER + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT " +
                    ")";





    public UserProfileDbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "UserProfileDbOpenHelper: Database is created ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);
        Log.d(TAG, "onCreate: table is created ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
    }
}

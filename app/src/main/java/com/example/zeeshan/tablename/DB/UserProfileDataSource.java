package com.example.zeeshan.tablename.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Zeeshan on 11/20/2017.
 */

public class UserProfileDataSource {

    private static final String TAG = "UserDB";
    SQLiteOpenHelper sqLiteOpenHelper;
    SQLiteDatabase sqLiteDatabase;

    public UserProfileDataSource(Context context){

        sqLiteOpenHelper=new UserProfileDbOpenHelper(context);
        Log.d(TAG, "UserProfileDataSource: Constructor is calling");

    }
    public void Open(){
        Log.d(TAG, "Open: database is opened");
        sqLiteDatabase=sqLiteOpenHelper.getWritableDatabase();
    }
    public void Close(){
        Log.d(TAG, "Close: Databse is closed");
        sqLiteOpenHelper.close();
    }


}

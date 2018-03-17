package com.yamini.training.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sravan on 13/03/18.
 */

/*

SQLiteOpenHelper : TO create Database -> ( Multiple table -> rows and columns)
SQLiteDatabase : To create table and perform some operation

 */
public class MyDatabase extends SQLiteOpenHelper {


    private SQLiteDatabase mDatabase;
    private static final String TAG = "MyDatabase";




    public MyDatabase(Context ctx) {
        super(ctx, DbUtils.DB_NAME, null, DbUtils.DB_VERSION); // Create Db

        mDatabase= getWritableDatabase();
        Log.i(TAG," constructor ");

    }

    public long saveData(String tabName , String colNme , ContentValues contentValues){

       return mDatabase.insert(tabName,colNme,contentValues);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DbUtils.CREATE_TABLE);
        sqLiteDatabase.execSQL(DbUtils.CREATE_TABLE_MOVIE);

        Log.i(TAG," onCreate  ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i(TAG," onUpgrade  ");

    }
}

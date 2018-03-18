package com.yamini.training.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

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

        try {
            return mDatabase.insert(tabName, colNme, contentValues);
        } catch (SQLException sq){
            Log.v(TAG,"saveData "+sq.toString());
        }
        return -1;
    }

    public Cursor getData(String query){


        // mDatabase.rawQuery("select * from "+DbUtils.TAB_MOVIE,null);

        return mDatabase.rawQuery(query,null);

    }

    public  ArrayList<Movie>  getMoviesInfo(String query){

       Cursor cur= getData(query);
        ArrayList<Movie> list = new ArrayList<>();

        if(cur!=null&& cur.getCount()>0){

 while(cur.moveToNext()){
     int moviewId = cur.getInt(cur.getColumnIndex(DbUtils.COL_MOVIE_ID));
     int page = cur.getInt(cur.getColumnIndex(DbUtils.COL_PAGE_ID));
     String backdropPath = cur.getString(cur.getColumnIndex(DbUtils.COL_BACKGROUND_PATH));
     String originalTitle = cur.getString(cur.getColumnIndex(DbUtils.COL_ORIGINL_TITLE));
     String releaseData = cur.getString(cur.getColumnIndex(DbUtils.COL_RELEASE_DATE));
     String overView = cur.getString(cur.getColumnIndex(DbUtils.COL_OVERVIEW));
     Movie movie = new Movie(page,releaseData,overView,backdropPath,originalTitle,moviewId);
     list.add(movie);
 }
       }
       return list;
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

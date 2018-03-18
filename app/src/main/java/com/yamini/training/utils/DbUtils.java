package com.yamini.training.utils;

/**
 * Created by sravan on 13/03/18.
 */

public class DbUtils {

     static final String DB_NAME="androidtraining";
     static final int DB_VERSION=1;

     public static final String TAB_USER ="users";
     public static final String TAB_USER_UNM ="unm";
     public static final String TAB_USER_PWD ="pwd";

     public static final String CREATE_TABLE= "create table if not exists "+TAB_USER+"  (  "+TAB_USER_UNM
             +"  text  , "+TAB_USER_PWD +" text );";




     public static final String TAB_MOVIE="MOVIE";

     /*
      this.mReleaseData = mReleaseData;
        this.mOverView = mOverView;
        this.mBackGroundPath = mBackGroundPath;
        this.mBackGroundPath = mBackGroundPath;
        this.mOriginalDate = mOriginalDate;
        this.mPage = mPage;
      */

     public static final String COL_PAGE_ID="pageid";
     public static final String COL_OVERVIEW="overview";
     public static final String COL_BACKGROUND_PATH="backgroundpath";
     public static final String COL_ORIGINL_TITLE ="originalTitle";
     public static final String COL_RELEASE_DATE="releasedate";
     public static final String COL_MOVIE_ID="movieid";

     public static final String CREATE_TABLE_MOVIE= "create table if not exists "+TAB_MOVIE+"  (  "+COL_MOVIE_ID
     +" Integer primary key ,"+ COL_PAGE_ID
             +"  integer  , "+COL_OVERVIEW +" text ,"+COL_BACKGROUND_PATH +" text ,"+ COL_ORIGINL_TITLE +" text ,"
             +COL_RELEASE_DATE +" text );";






}

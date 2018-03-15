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



}

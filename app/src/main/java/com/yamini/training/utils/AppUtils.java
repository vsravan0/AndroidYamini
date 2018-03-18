package com.yamini.training.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by sravan on 10/03/18.
 */

public class AppUtils {

    private static final String TAG="AppUtils";

    public static boolean isEmpty(EditText editText){
        String data = editText.getText().toString().trim();

       // boolean isvlid = (data!=null&& data.length()>0);

        return TextUtils.isEmpty(data);

    }

    public static  void  checkLogin(Context ctx , EditText etUser, EditText etPwd){
        String username = etUser.getText().toString().trim();
        String pwd= etPwd.getText().toString().trim();


        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(ctx);
        String savedUserName = pref.getString("username","");
        String savedPwd= pref.getString("password","");

        if(savedUserName.equalsIgnoreCase(username)&& savedPwd.equals(pwd)){
            Toast.makeText(ctx," Success",Toast.LENGTH_LONG).show();
            Log.v(TAG," Success");
        }else{
            Toast.makeText(ctx," check unm and pwd ",Toast.LENGTH_LONG).show();
            Log.v(TAG," Error");
        }

    }



// ANR : Activity Not respond


    public static final String URL_PATH="http://api.themoviedb.org/3/movie/popular?api_key=dad78d0a6eee736a777d00c394e6f00e";


    public static InputStream loadData(){

        try {
            URL url = new URL(URL_PATH);
            URLConnection connection = url.openConnection();
            //connection.connect();
           // OutputStream  osr= connection.getOutputStream(); Send some data to server
            InputStream isr =connection.getInputStream(); // Get Data from API
            return  isr;
        }
        catch (MalformedURLException  e){
            Log.v(TAG," Error "+e);
        } catch (IOException e){
            Log.v(TAG," Error "+e);
        }
        return  null;
    }


    public static String getMoviesInfo(){
        StringBuilder data = new StringBuilder();
        try {
            InputStream isr = loadData();
            if(isr== null){
                Log.v(TAG," Error while loading data ");
                return "";
            }
            BufferedReader buf = new BufferedReader(new InputStreamReader(isr));
            // Converst Stream to String
            String response;
            while ( (response=buf.readLine()) != null) {
                data.append(response);
            }
            Log.v(TAG," getMoviesInfo ->"+data.toString());
            return data.toString();

        }catch (IOException e){
            Log.v(TAG," Error getMoviesInfo "+e);

        }
        Log.v(TAG," getMoviesInfo error ->"+data.toString());
        return  data.toString();
    }


    public static boolean isNetWorkAvaliable(Context ctx ){

        ConnectivityManager cm = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return  (networkInfo!=null&& networkInfo.isConnected());




    }

    public static void enableData(Context ctx){


        boolean isWifiSupport =ctx.getPackageManager().hasSystemFeature(PackageManager.FEATURE_WIFI);

        if(isWifiSupport) {
            WifiManager wifiManager = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
            if (wifiManager != null) {
                wifiManager.setWifiEnabled(true);
                Log.v(TAG, " Called enable wifi isWifiSupport:" + isWifiSupport);
            } else {
                Log.v(TAG, " DUT Does not support Wifi");
            }

        }else{
            enableMobileData(ctx);
        }

    }

    public static  void enableMobileData(Context ctx){

        ConnectivityManager cm = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        Method dataMtd = null;
        try {
            dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);
            dataMtd.setAccessible(true);
            try {
                dataMtd.invoke(cm, true);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
                Log.v(TAG,e.toString());
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                Log.v(TAG,e.toString());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            Log.v(TAG,e.toString());
        }



    }



    public static void toast(Context ctx , String msg){
Toast.makeText(ctx,msg,Toast.LENGTH_LONG).show();
    }


    public static ArrayList<Movie> parseData(String response){
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        try {
            JSONObject obj = new JSONObject(response);
            int page = obj.getInt(Keys.KEY_PAGE);
            int toalResult=obj.getInt(Keys.KEY_TOTLA_RESULTS);
            int totalPages= obj.getInt(Keys.KEY_TOTAL_PAGES);
            JSONArray arr = obj.getJSONArray(Keys.KEY_RESULTS);
            for(int i=0;i<arr.length();i++){
                JSONObject ob = arr.getJSONObject(i);
                String  releaseData=ob.getString(Keys.KEY_RELESAE_DATE);
                String overView="";
                if(ob.has(Keys.KEY_RELESAE_DATE)) {
                     overView = ob.getString(Keys.KEY_OVERVIEW);
                }

                String  backdropPath=ob.getString(Keys.KEY_BACKDROP_PATH);
                String  originalTitle=ob.getString(Keys.KEY_ORIGINAL_TITLE);
                int id= ob.getInt(Keys.KEY_MOVIE_ID);
                Movie movie = new Movie(page,releaseData,overView,backdropPath,originalTitle,id);

                Log.v(TAG," Movies iNfo "+movie);
            movieList.add(movie);
            }
        }catch (JSONException js){
            Log.v(TAG," Error parseData "+js);
        }
        return movieList;
    }



    public static ArrayList<Movie> loadMovies(Context ctx){



        if(AppUtils.isNetWorkAvaliable(ctx)) {
            String response = AppUtils.getMoviesInfo(); // Getting response from service and converting into String data
            ArrayList<Movie> list = AppUtils.parseData(response); // Converting from String to Json
            int totalRecods = AppUtils.saveMovies(ctx, list); // Saving those records into Db
            Log.v(TAG," loadMovies saved data no of records :"+totalRecods);
        }

        MyDatabase db = new MyDatabase(ctx);
        ArrayList<Movie> moviesList= db.getMoviesInfo("select * from "+DbUtils.TAB_MOVIE);
        return moviesList;



    }

    public static final int saveMovies(Context ctx ,ArrayList<Movie> list ){

        MyDatabase db = new MyDatabase(ctx);
        ContentValues cv;
        Movie movie;
        int count =0;

        for(int i=0;i<list.size();i++){
            movie = list.get(i);
             cv = new ContentValues();
            cv.put(DbUtils.COL_PAGE_ID,movie.getmPage());
            cv.put(DbUtils.COL_BACKGROUND_PATH,movie.getmBackGroundPath());
            cv.put(DbUtils.COL_ORIGINL_TITLE,movie.getmOriginalDate());
            cv.put(DbUtils.COL_OVERVIEW,movie.getmOverView());
            cv.put(DbUtils.COL_RELEASE_DATE,movie.getmReleaseData());
            cv.put(DbUtils.COL_MOVIE_ID,movie.getmMovieId());

            long rowId=db.saveData(DbUtils.TAB_MOVIE,DbUtils.COL_PAGE_ID,cv);
            Log.v(TAG,"saveMovies  rowId :"+rowId);
            if(rowId!=-1){
                count++;
            }


        }
        return  count;



    }
}

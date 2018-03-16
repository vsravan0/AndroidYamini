package com.yamini.training.utils;

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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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

}

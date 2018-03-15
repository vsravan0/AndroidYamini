package com.yamini.training.utils;

import android.content.Context;
import android.content.SharedPreferences;
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



}

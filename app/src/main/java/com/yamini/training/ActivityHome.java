package com.yamini.training;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yamini.training.utils.AppUtils;
import com.yamini.training.utils.Movie;
import com.yamini.training.utils.MyDatabase;

import java.util.ArrayList;

/**
 * Created by sravan on 15/03/18.
 */

// Using Service , Using Async task , Using Threads
/*
Call Webservice

 */
public class ActivityHome extends Activity {

    TextView mTvData;
    private ProgressBar mProgress;
    private static final String TAG="ActivityHome";
    private Context mCtx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);
        mCtx= getApplicationContext();

       /* if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        } */
        mTvData =(TextView)findViewById(R.id.id_tv_data);
        mProgress=(ProgressBar)findViewById(R.id.id_pb_loading);
        mProgress.setVisibility(ProgressBar.INVISIBLE);

    }


    public  void loadMovies(View v){


        if(AppUtils.isNetWorkAvaliable(mCtx)) {
            MyTask task = new MyTask();
            task.execute();

        } else{
AppUtils.toast(mCtx," Net work not Available");
            AppUtils.enableData(mCtx);
        }

        /*String response = AppUtils.getMoviesInfo(); // 3 sec
        Toast.makeText(getApplicationContext()," Response :"+response,Toast.LENGTH_LONG).show();
        mTvData.setText(response); */

    }


    class MyTask extends AsyncTask <Void,Integer, Integer>{



        @Override
        protected void onPreExecute() { // First
            super.onPreExecute();
            mProgress.setVisibility(ProgressBar.VISIBLE);
        }
        @Override
        protected Integer doInBackground(Void... values) { // Needs to logn running operations

            String response = AppUtils.getMoviesInfo(); // Getting response from service and converting into String data
            ArrayList<Movie> list =AppUtils.parseData(response); // Converting from String to Json
            int totalRecods=AppUtils.saveMovies(mCtx,list); // Saving those records into Db

            return totalRecods;


        }



        @Override
        protected void onPostExecute(Integer response) {
            super.onPostExecute(response);

            mTvData.setText(" Total saved records :"+response);
            Log.v(TAG," Total saved records :"+response);
            mProgress.setVisibility(ProgressBar.INVISIBLE);






        }
    }

}

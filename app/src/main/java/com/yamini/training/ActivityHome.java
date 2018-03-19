package com.yamini.training;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yamini.training.adapter.MoviedAdapter;
import com.yamini.training.utils.AppUtils;
import com.yamini.training.utils.DbUtils;
import com.yamini.training.utils.Keys;
import com.yamini.training.utils.Movie;
import com.yamini.training.utils.MyDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sravan on 15/03/18.
 */

// Using Service , Using Async task , Using Threads
/*
Call Webservice

 */
public class ActivityHome extends Activity implements AdapterView.OnItemClickListener{

    TextView mTvData;
    private ProgressBar mProgress;
    private static final String TAG="ActivityHome";
    private Context mCtx;
    private ListView mLv;
    private ArrayList<Movie> mMoviesLsit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);
        mCtx= getApplicationContext();
        mLv=(ListView)findViewById(R.id.id_lv_movies);
        mLv.setOnItemClickListener(this);

       /* if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        } */
        mTvData =(TextView)findViewById(R.id.id_tv_data);
        mProgress=(ProgressBar)findViewById(R.id.id_pb_loading);
        mProgress.setVisibility(ProgressBar.INVISIBLE);
        MyTask task = new MyTask();
        task.execute();

    }


    public  void loadMovies(View v){
        MyTask task = new MyTask();
        task.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

Log.v(TAG," Clicked Itme Position :"+position);
Intent intent=new Intent(ActivityHome.this,ActivityMoveInfo.class);
Movie movie= mMoviesLsit.get(position);
intent.putExtra(Keys.KEY_MOVIE_ID,movie.getmMovieId());

        intent.putExtra(Keys.KEY_MOVIE,movie);

startActivity(intent);


    }


    class MyTask extends AsyncTask <Void,ArrayList<Movie>, ArrayList<Movie>>{



        @Override
        protected void onPreExecute() { // First
            super.onPreExecute();
            mProgress.setVisibility(ProgressBar.VISIBLE);
        }
        @Override
        protected ArrayList<Movie> doInBackground(Void... values) { // Needs to logn running operations


            return AppUtils.loadMovies(mCtx);
        }



        @Override
        protected void onPostExecute(ArrayList<Movie> responseList) {
            super.onPostExecute(responseList);
            mTvData.setText(" Total saved records :"+responseList.size());
            Log.v(TAG," Total saved records :"+responseList.size());
            mProgress.setVisibility(ProgressBar.INVISIBLE);
            mMoviesLsit= responseList;
            setDapter();
        }
    }


    private void setDapter(){
        MoviedAdapter adapter = new MoviedAdapter(mMoviesLsit,getLayoutInflater());
        mLv.setAdapter(adapter);

    }
}

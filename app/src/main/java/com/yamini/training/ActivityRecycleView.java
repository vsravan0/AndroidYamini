package com.yamini.training;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.yamini.training.adapter.MoviesRecycleAdapter;
import com.yamini.training.services.MyService;
import com.yamini.training.utils.AppUtils;
import com.yamini.training.utils.Movie;

import java.util.ArrayList;

public class ActivityRecycleView extends AppCompatActivity {


    private RecyclerView mMoviesRecyclerView;
    private MoviesRecycleAdapter mMoviewRecycleAdapter;
    private static ArrayList<Movie> mMoviesLsit= new ArrayList<>();
    private static final String TAG="ActivityRecycleView";
    private MyHandler handler;

    private final int DATA_LOADED= 1000;
    private final int DATA_LOAD_ERROR= 1001;


    class  MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.i(TAG," Action :"+action);
            if(action.equals(AppUtils.DATA_LOADED)){
                loadDataInBg();
            }

        }
    }

    private MyReceiver myReceiver;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

// ANR :Activity Not respond

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycleview);
        mMoviesRecyclerView =(RecyclerView)findViewById(R.id.id_movies_recycleview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        handler = new MyHandler();
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter(AppUtils.DATA_LOADED);
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);


        registerReceiver(myReceiver,filter);
        //mMoviesLsit= AppUtils.loadMovies(getApplicationContext());
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext());
        mMoviesRecyclerView.setLayoutManager(linearLayoutManager);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }



    public  void loadDataInBg(){


        Runnable r = new Runnable() {
            @Override
            public void run() {
                mMoviesLsit = AppUtils.getLocalMovies(getApplicationContext());
                Log.v(TAG,"loadDataInBg : "+mMoviesLsit.size());

                if(mMoviesLsit.size()==0) {
                    handler.sendEmptyMessage(DATA_LOAD_ERROR);

                }else{
                    handler.sendEmptyMessage(DATA_LOADED);

                }
                //mMoviesRecyclerView.setAdapter(mMoviewRecycleAdapter); // Set Some thing to UI , Can I do UI opearion in Back Thread
            }
        };

        Thread th = new Thread(r);
        th.start(); // Run
    }

    class MyHandler extends Handler{


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.v(TAG," MyHandler "+msg.what);
            if(msg.what==DATA_LOADED){
                mMoviewRecycleAdapter= new MoviesRecycleAdapter(mMoviesLsit);
                mMoviesRecyclerView.setAdapter(mMoviewRecycleAdapter);
            } else if(msg.what==DATA_LOAD_ERROR){
                Log.v(TAG," Data not loaded ");

            }


        }
    }


    public  void loadFromServer(View view){
        Intent intent = new Intent(ActivityRecycleView.this, MyService.class);
        startService(intent);

    }
    public  void killService(View view){
        Intent intent = new Intent(ActivityRecycleView.this, MyService.class);
        stopService(intent);

    }
    public  void loadFromLocal(View view){
        loadDataInBg();
    }



}

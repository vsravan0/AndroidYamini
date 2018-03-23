package com.yamini.training.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.yamini.training.utils.AppUtils;

import java.util.ArrayList;

public class MyService extends Service {

    private static final String TAG="MYservice";

    public MyService() {
        Log.v(TAG,TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG,"onCreate");


    }

    public  void loadDataInBg(){


        Runnable r = new Runnable() {
            @Override
            public void run() {
                Log.v(TAG," start fetch");
                AppUtils.fetchMoviesFromServer(getApplicationContext());
                Log.v(TAG," end fetch");
               // stopSelf();


            }
        };

        Thread th = new Thread(r);
        th.start(); // Run
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        loadDataInBg();

        Log.v(TAG,"onStartCommand intent:"+intent+" flags :"+flags+" startid:"+startId);


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG,"onBind");

        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(TAG," onDestroy");
    }
}

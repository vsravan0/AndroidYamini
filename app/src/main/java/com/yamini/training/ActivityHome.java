package com.yamini.training;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yamini.training.utils.AppUtils;

/**
 * Created by sravan on 15/03/18.
 */

// Using Service , Using Async task , Using Threads
/*
Call Webservice

 */
public class ActivityHome extends Activity {

    TextView mTvData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        mTvData =(TextView)findViewById(R.id.id_tv_data);

    }


    public  void loadMovies(View v){

        String response = AppUtils.getMoviesInfo(); // 3 sec
        Toast.makeText(getApplicationContext()," Response :"+response,Toast.LENGTH_LONG).show();
        mTvData.setText(response);

    }
}

package com.yamini.training;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.yamini.training.utils.Keys;
import com.yamini.training.utils.Movie;

public class ActivityMoveInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_movie_info);
        TextView tv=(TextView)findViewById(R.id.id_tv_movie_info);
        Intent intent = getIntent();
        int MovieId= intent.getIntExtra(Keys.KEY_MOVIE_ID,0);
        tv.setText("Movied Id "+MovieId);
        Movie movie=(Movie)intent.getSerializableExtra(Keys.KEY_MOVIE);
        tv.append(" \n\n Movie  :"+movie);
    }
}

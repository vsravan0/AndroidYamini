package com.yamini.training;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yamini.training.R;
import com.yamini.training.adapter.MoviesRecycleAdapter;
import com.yamini.training.utils.AppUtils;
import com.yamini.training.utils.Movie;

import java.util.ArrayList;

public class ActivityRecycleView extends AppCompatActivity {


    private RecyclerView mMoviesRecyclerView;
    private MoviesRecycleAdapter mMoviewRecycleAdapter;
    private ArrayList<Movie> mMoviesLsit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycleview);
        mMoviesRecyclerView =(RecyclerView)findViewById(R.id.id_movies_recycleview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mMoviesLsit= AppUtils.loadMovies(getApplicationContext());
        mMoviewRecycleAdapter= new MoviesRecycleAdapter(mMoviesLsit);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext());
        mMoviesRecyclerView.setLayoutManager(linearLayoutManager);
        mMoviesRecyclerView.setAdapter(mMoviewRecycleAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}

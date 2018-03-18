package com.yamini.training.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yamini.training.R;
import com.yamini.training.utils.Movie;

import java.util.ArrayList;

/**
 * Created by sravan on 18/03/18.
 */

public class MoviedAdapter extends BaseAdapter {

    private ArrayList<Movie> mMoviesList;
    private LayoutInflater mInflater;


    public MoviedAdapter(ArrayList<Movie> moviesList, LayoutInflater inflater){
        mMoviesList= moviesList;
        mInflater= inflater;

    }

    @Override
    public int getCount() {
        return mMoviesList.size();
    }

    @Override
    public Object getItem(int i) {
        return mMoviesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int psition, View view, ViewGroup viewGroup) {

        View v= (View)mInflater.inflate(R.layout.layout_movie_row,null);

        TextView tvMovieId=(TextView)v.findViewById(R.id.id_tv_movieid);
        TextView tvOverView=(TextView)v.findViewById(R.id.id_tv_overview);


        Movie movie =(Movie)getItem(psition);

        tvMovieId.setText(" Movie ID :"+movie.getmMovieId());
        tvOverView.setText(movie.getmOverView());



        return v;
    }
}

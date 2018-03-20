package com.yamini.training.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yamini.training.R;
import com.yamini.training.utils.Movie;

import java.util.ArrayList;

/**
 * Created by sravan on 20/03/18.
 */

public class MoviesRecycleAdapter extends RecyclerView.Adapter<MoviesRecycleAdapter.ViewHolder> {

    private ArrayList<Movie> mMoviesList;


    public MoviesRecycleAdapter(ArrayList<Movie> moviesList){
        mMoviesList= moviesList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_movie_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Movie movie =mMoviesList.get(position);

        holder.tvMovieId.setText(" Movie ID :"+movie.getmMovieId());
        holder.tvOverView.setText(movie.getmOverView());




    }

    @Override
    public int getItemCount() {
        return mMoviesList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {

        TextView tvMovieId;
        TextView tvOverView;

        public ViewHolder(View view){
            super(view);
             tvMovieId=(TextView)view.findViewById(R.id.id_tv_movieid);
             tvOverView=(TextView)view.findViewById(R.id.id_tv_overview);

        }

    }
}

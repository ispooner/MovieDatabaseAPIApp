package com.illicitintelligence.moviedatabaseapiapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.illicitintelligence.moviedatabaseapiapp.R;
import com.illicitintelligence.moviedatabaseapiapp.model.search.Result;

import java.util.List;

public class MovieItemAdapter extends RecyclerView.Adapter<MovieItemAdapter.MovieItemViewHolder> {

    List<Result> results;
    Context context;
    MovieDelegate delegate;

    public interface MovieDelegate {
        public void movieCallback(Result result);
    }

    public MovieItemAdapter(List<Result> results, MovieDelegate delegate) {
        this.results = results;
        this.delegate = delegate;
    }

    @NonNull
    @Override
    public MovieItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext().getApplicationContext();
        MovieItemViewHolder holder = new MovieItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_listitem, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieItemViewHolder holder, final int position) {
        holder.movieTitle.setText(results.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.movieCallback(results.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class MovieItemViewHolder extends RecyclerView.ViewHolder {

        TextView movieTitle;

        public MovieItemViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.item_movie_title);
        }
    }

}

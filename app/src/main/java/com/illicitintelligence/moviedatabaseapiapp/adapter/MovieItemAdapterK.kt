package com.illicitintelligence.moviedatabaseapiapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.illicitintelligence.moviedatabaseapiapp.R
import com.illicitintelligence.moviedatabaseapiapp.model.search.Result
import kotlinx.android.synthetic.main.movie_listitem.view.*

class MovieItemAdapterK(val results: List<Result>, val delegate: MovieDelegate) : RecyclerView.Adapter<MovieItemAdapterK.MovieItemViewHolder>() {

    lateinit var context: Context

    interface MovieDelegate {
        fun movieCallback(result: Result)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        context = parent.context.applicationContext
        return MovieItemViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_listitem, parent, false))
    }

    override fun getItemCount(): Int {
         return results.size
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.movieTitle.text = results.get(position).title
        holder.itemView.setOnClickListener {
            delegate.movieCallback(results.get(position))
        }
    }


    class MovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var movieTitle: TextView = itemView.item_movie_title
    }
}
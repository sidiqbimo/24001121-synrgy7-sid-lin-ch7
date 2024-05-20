package com.bimobelajar.mymovie.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bimobelajar.mymovie.R
import com.bimobelajar.mymovie.data.model.Movie
import com.bumptech.glide.Glide

class MovieAdapter(private val movies: List<Movie>, private val clickListener: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val moviePoster: ImageView = itemView.findViewById(R.id.moviePoster)
        private val movieTitle: TextView = itemView.findViewById(R.id.movieTitle)
        private val movieReleaseDate: TextView = itemView.findViewById(R.id.movieReleaseDate)

        fun bind(movie: Movie) {
            movieTitle.text = movie.title
            movieReleaseDate.text = movie.release_date
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
                .into(moviePoster)

            itemView.setOnClickListener { clickListener(movie) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size
}

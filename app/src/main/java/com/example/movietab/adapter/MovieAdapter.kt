package com.example.movietab.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movietab.data.response.MovieResult
import com.example.movietab.databinding.HolderMoviesBinding
import com.squareup.picasso.Picasso

class MovieAdapter(var list: List<MovieResult>) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {
    private val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

    inner class MovieHolder(var binding: HolderMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieResult) {
            binding.tvTitle.text = movie.title
            Picasso.get().load(IMAGE_URL + movie.poster_path).into(binding.ivPicture)
            binding.tvOverview.text = movie.overview
            binding.tvRating.text = "Rating: ${movie.vote_average}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(
            HolderMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        var movie = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = list.size
}
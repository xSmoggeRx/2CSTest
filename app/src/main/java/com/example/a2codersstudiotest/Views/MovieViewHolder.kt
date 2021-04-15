package com.example.a2codersstudiotest.Views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.a2codersstudiotest.Constants.HttpConstants
import com.example.a2codersstudiotest.Models.Movie
import com.example.a2codersstudiotest.databinding.MovieItemBinding
import com.squareup.picasso.Picasso

class MovieViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = MovieItemBinding.bind(view)

    fun bind(movie: Movie){
        binding.movieTitle.text = movie.title
        Picasso.get().load(HttpConstants.imageUrlRoot + movie.poster_path).into(binding.movieImg)
        binding.movieOverview.text = movie.overview
    }
}
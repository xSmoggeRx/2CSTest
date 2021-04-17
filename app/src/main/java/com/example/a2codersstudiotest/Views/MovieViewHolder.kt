package com.example.a2codersstudiotest.Views

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.a2codersstudiotest.Constants.HttpConstants
import com.example.a2codersstudiotest.Controllers.MainActivityController
import com.example.a2codersstudiotest.Models.Movie
import com.example.a2codersstudiotest.databinding.MovieItemBinding
import com.squareup.picasso.Picasso

class MovieViewHolder(view: View, mainActivityController: MainActivityController): RecyclerView.ViewHolder(view) {

    private val binding = MovieItemBinding.bind(view)
    private val mainActivityController = mainActivityController
    fun bind(movie: Movie){
        binding.movieTitle.text = movie.title
        Picasso.get().load(HttpConstants.imageUrlRoot + movie.poster_path).into(binding.movieImg)
        Log.d("testing size", HttpConstants.imageUrlRoot + movie.poster_path)
        binding.movieOverview.text = movie.overview

        binding.cardView.setOnClickListener {
            mainActivityController.openMovieDetails(movie.title, HttpConstants.imageUrlRoot + movie.poster_path, movie.overview)
        }
    }
}
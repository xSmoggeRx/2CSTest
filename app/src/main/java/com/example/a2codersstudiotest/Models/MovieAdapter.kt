package com.example.a2codersstudiotest.Models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a2codersstudiotest.Controllers.MainActivityController
import com.example.a2codersstudiotest.R
import com.example.a2codersstudiotest.Views.MovieViewHolder

class MovieAdapter(private val movieList: List<Movie>, mainActivityController: MainActivityController):RecyclerView.Adapter<MovieViewHolder>() {
    val mainActivityController = mainActivityController
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(layoutInflater.inflate(R.layout.movie_item, parent, false), mainActivityController)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }
}
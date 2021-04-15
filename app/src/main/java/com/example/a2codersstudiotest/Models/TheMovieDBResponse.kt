package com.example.a2codersstudiotest.Models

import com.google.gson.annotations.SerializedName

data class TheMovieDBResponse(
    var page: Int,
    @SerializedName("results") var listOfMovies: List<Movie>,
    var total_pages: Int,
    var total_results: Int
)


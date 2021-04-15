package com.example.a2codersstudiotest.APIConfig

import com.example.a2codersstudiotest.Models.TheMovieDBResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getMovies(@Url url:String): Response<TheMovieDBResponse>
}
package com.example.a2codersstudiotest.Controllers

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.a2codersstudiotest.APIConfig.APIService
import com.example.a2codersstudiotest.Constants.HttpConstants
import com.example.a2codersstudiotest.Constants.StringConstants
import com.example.a2codersstudiotest.Models.Movie
import com.example.a2codersstudiotest.Models.TheMovieDBResponse
import com.example.a2codersstudiotest.Views.DetailsActivity
import com.example.a2codersstudiotest.Views.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainActivityController(viewActivity: MainActivity) {
    var page = 1;
    var mainActivity: MainActivity = viewActivity

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(HttpConstants.urlRoot)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun askForPopularMovies(isFirstTime: Boolean) { // fetch movie data from the api by popular
        if(!isFirstTime) {
            page ++
        }
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = getRetrofit().create(APIService::class.java).getMovies(HttpConstants.popularMoviesURL + HttpConstants.api_key_param + HttpConstants.pageParam + page)
                val theMovieDBResponse: TheMovieDBResponse? = call.body()
                mainActivity.runOnUiThread {
                    if (call.isSuccessful) {
                        val movieListFromResponse: List<Movie> = theMovieDBResponse?.listOfMovies ?: emptyList() // Check if is null, in that case empty list is assign, otherwise we add the new list.
                        mainActivity.movieList.addAll(movieListFromResponse)
                        mainActivity.adapter.notifyDataSetChanged()
                    } else {
                        sendToasFromNoUIThread(StringConstants.error_message_fetching_data + "Call wasn't successful")
                    }
                }
            } catch (error: Exception) {
                sendToasFromNoUIThread(StringConstants.error_message_fetching_data + error.message)
            }
        }
    }

    private fun sendToasFromNoUIThread(mensaje: String) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(mainActivity.applicationContext, mensaje, Toast.LENGTH_LONG).show()
        }
    }

    fun openMovieDetails(title: String, urlImage: String, overview: String) {
        mainActivity.openDetailsActivity(title, urlImage, overview)
    }
}
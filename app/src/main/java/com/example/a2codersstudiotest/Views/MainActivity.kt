package com.example.a2codersstudiotest.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.a2codersstudiotest.APIConfig.APIService
import com.example.a2codersstudiotest.Constants.HttpConstants
import com.example.a2codersstudiotest.Models.TheMovieDBResponse
import com.example.a2codersstudiotest.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    //Se declara la variable binding que se usará para poder acceder a los views de las activities sin estar usando el findElementById tan tedioso.
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonTesting.setOnClickListener{
            askForPopularMovies()
        }
        //binding.appTitle.text = HttpConstants.popularMoviesURL
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(HttpConstants.urlRoot)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun askForPopularMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getMovies(HttpConstants.popularMoviesURL + HttpConstants.api_key_param)
            val theMovieDBResponse: TheMovieDBResponse? = call.body()
            if (call.isSuccessful) {
                theMovieDBResponse?.listOfMovies?.get(0)?.title?.let {
                    Log.d("testingResponse",
                        it
                    )
                }
            } else {
                Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_LONG)
            }
        }
    }
}
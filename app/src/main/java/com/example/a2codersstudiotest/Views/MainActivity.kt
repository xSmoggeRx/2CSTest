package com.example.a2codersstudiotest.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2codersstudiotest.APIConfig.APIService
import com.example.a2codersstudiotest.Constants.HttpConstants
import com.example.a2codersstudiotest.Models.Movie
import com.example.a2codersstudiotest.Models.MovieAdapter
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
    private lateinit var adapter: MovieAdapter
    private val movieList = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = MovieAdapter(movieList)
        binding.recyclerViewMovies.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMovies.adapter = adapter
        askForPopularMovies()
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
            runOnUiThread {
                if (call.isSuccessful) { // TODO: manejar mejor los errores
                    val movieListFromResponse: List<Movie> = theMovieDBResponse?.listOfMovies ?: emptyList() // Checkea si es nulo, de ser así se asigna una lista vacía, de no ser null se añade la nueva lista a la vista
                    movieList.clear()
                    movieList.addAll(movieListFromResponse)
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }
            }
        }
    }

    private fun showError() { // TODO: Manejar mejor los errores
        Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show()
    }
}
package com.example.a2codersstudiotest.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a2codersstudiotest.APIConfig.APIService
import com.example.a2codersstudiotest.Constants.HttpConstants
import com.example.a2codersstudiotest.Controllers.MainActivityController
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

    // Binding is declared to access activity views instead of using findElementById
    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: MovieAdapter
    val movieList = mutableListOf<Movie>()
    lateinit var mainActivityController: MainActivityController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityController =  MainActivityController(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = MovieAdapter(movieList, mainActivityController)
        binding.recyclerViewMovies.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMovies.adapter = adapter
        mainActivityController.askForPopularMovies(true)

        binding.recyclerViewMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() { // Listener is added to trigger a function when the scroll reach the end
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mainActivityController.askForPopularMovies(false)
                }
            }
        })
    }

    fun openDetailsActivity(title: String, urlImage: String, overview: String) {
        val myBundle: Bundle = Bundle()
        myBundle.putString("title", title)
        myBundle.putString("urlImage", urlImage)
        myBundle.putString("overview", overview)
        val myIntent = Intent(this, DetailsActivity::class.java)
        myIntent.putExtras(myBundle)
        startActivity(myIntent)
    }
}
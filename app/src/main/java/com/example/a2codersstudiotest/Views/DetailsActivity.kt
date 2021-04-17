package com.example.a2codersstudiotest.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.a2codersstudiotest.Constants.HttpConstants
import com.example.a2codersstudiotest.R
import com.example.a2codersstudiotest.databinding.ActivityDetailsBinding
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title = intent.extras?.getString("title")
        val urlImage = intent.extras?.getString("urlImage")
        val overview = intent.extras?.getString("overview")

        binding.titleMovie.text = title
        binding.overviewMovie.text = overview
        Picasso.get().load(urlImage).into(binding.detailsImage)

    }
}
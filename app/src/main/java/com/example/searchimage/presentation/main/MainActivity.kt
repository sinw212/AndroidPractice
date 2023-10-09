package com.example.searchimage.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.searchimage.R
import com.example.searchimage.databinding.ActivityMainBinding
import com.example.searchimage.presentation.search.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.frameLayout, SearchFragment.newInstance()).commit()
    }
}
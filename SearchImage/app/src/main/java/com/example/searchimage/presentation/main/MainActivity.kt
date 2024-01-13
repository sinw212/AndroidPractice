package com.example.searchimage.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.searchimage.R
import com.example.searchimage.databinding.ActivityMainBinding
import com.example.searchimage.presentation.search.SearchFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewPager2Adapter by lazy {
        MainViewPagerAdapter(this@MainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() = with(binding) {
        viewPager2.adapter = viewPager2Adapter
        viewPager2.offscreenPageLimit = viewPager2Adapter.itemCount

        TabLayoutMediator(tabLayout, viewPager2) { tab, pos ->
            tab.setText(viewPager2Adapter.getTitle(pos))
        }.attach()
    }
}
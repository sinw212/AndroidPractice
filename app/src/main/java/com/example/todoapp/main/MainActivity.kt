package com.example.todoapp.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todoapp.databinding.ActivityMainBinding
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
        // Toolbar Init
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false) //true로 설정 시 기본 옵션(뒤로가기 버튼) 활성화
        toolBar.title = "Camp"

        // ViewPager2Adapter Init
        viewPager.adapter = viewPager2Adapter

        //TabLayout - ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, pos ->
            tab.text = viewPager2Adapter.getTitle(pos)
        }.attach()

        //Floating Button Click Listener
        floatingBtn.setOnClickListener {

        }
    }

}
package com.example.todoapp.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.example.todoapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewPager2Adapter by lazy {
        MainViewPagerAdapter(this@MainActivity)
    }
    private val pageChangeCallback = object: ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            Log.d("진입", position.toString())
            when(position) {
                0 -> binding.floatingBtn.show()
                1 -> binding.floatingBtn.hide()
            }
        }
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

        viewPager.registerOnPageChangeCallback(pageChangeCallback)

        //Floating Button Click Listener
        floatingBtn.setOnClickListener {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("진입", "onDestory()")
        binding.viewPager.unregisterOnPageChangeCallback(pageChangeCallback)
    }
}
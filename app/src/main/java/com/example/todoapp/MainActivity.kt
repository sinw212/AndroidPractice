package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.example.todoapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val tabList = listOf("Todo", "Bookmark")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar Init
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false) //true로 설정 시 기본 옵션(뒤로가기 버튼) 활성화
        binding.toolBar.title = "Camp"

        // ViewPager2Adapter Init
        binding.viewPager.adapter = ViewPager2Adapter(this).apply {
            addFragment(TodoFragment())
            addFragment(BookmarkFragment())
        }

        // TabLayout - ViewPager 연결
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            tab.text = tabList[pos]
        }.attach()
    }
}
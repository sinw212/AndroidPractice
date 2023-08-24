package com.example.todoapp.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.viewpager2.widget.ViewPager2
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.todo.TodoActivity
import com.example.todoapp.todo.TodoFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
//    companion object {
//        fun newIntent(context: Context, intentData: String, activity: Class<*>): Intent {
//            val intent = Intent(context, activity)
//            intent.putExtra("dataName", intentData)
//            return intent
//        }
//    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    private val viewPager2Adapter by lazy {
        MainViewPagerAdapter(this@MainActivity)
    }

    private val pageChangeCallback = object: ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
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

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == RESULT_OK) {
                val txtTitle = it.data?.getStringExtra("title") ?: " "
                val txtContent = it.data?.getStringExtra("content") ?: " "
                val todoFragment = viewPager2Adapter.getFragment(0) as TodoFragment
                todoFragment.updateList(txtTitle, txtContent)
            }
        }

        //Floating Button Click Listener
        floatingBtn.setOnClickListener {
            val s = TodoActivity::class.java
            activityResultLauncher.launch(Intent(this@MainActivity, TodoActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.viewPager.unregisterOnPageChangeCallback(pageChangeCallback)
    }
}
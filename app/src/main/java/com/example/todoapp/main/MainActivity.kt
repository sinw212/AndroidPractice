package com.example.todoapp.main

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.viewpager2.widget.ViewPager2
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.todo.add.TodoContentActivity
import com.example.todoapp.todo.home.TodoFragment
import com.example.todoapp.todo.home.TodoModel
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewPager2Adapter by lazy {
        MainViewPagerAdapter(this@MainActivity)
    }

    private val addTodoLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK) {
            val todoModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result.data?.getParcelableExtra(TodoContentActivity.EXTRA_MODEL, TodoModel::class.java)
            } else {
                result.data?.getParcelableExtra(TodoContentActivity.EXTRA_MODEL)
            }

            val todoFragment = viewPager2Adapter.getFragment(0) as? TodoFragment
            todoFragment?.addTodoItem(todoModel)
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
        toolBar.title = getString(R.string.main_toolbar)

        // ViewPager2Adapter Init
        viewPager.adapter = viewPager2Adapter

        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if(viewPager2Adapter.getFragment(position) is TodoFragment) {
                    floatingBtn.show()
                } else {
                    floatingBtn.hide()
                }
            }
        })

        //TabLayout - ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, pos ->
            tab.setText(viewPager2Adapter.getTitle(pos))
        }.attach()

        //Floating Button Click Listener
        floatingBtn.setOnClickListener {
            addTodoLauncher.launch(TodoContentActivity.newIntentForAdd(this@MainActivity))
        }
    }
}
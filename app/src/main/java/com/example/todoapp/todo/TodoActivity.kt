package com.example.todoapp.todo

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.databinding.ActivityTodoBinding
import com.example.todoapp.main.MainActivity

class TodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTodoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() = with(binding) {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolBar.title = "할일"

        btnAdd.setOnClickListener {
            val todoIntent = Intent(this@TodoActivity, MainActivity::class.java).apply {
                putExtra("title", edtTitle.text.toString())
                putExtra("content", edtContent.text.toString())
            }
            setResult(RESULT_OK, todoIntent)
            if(!isFinishing) {
                finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                //toolBar 뒤로가기 버튼 클릭 시
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
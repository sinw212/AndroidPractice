package com.example.todoapp.todo.add

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityTodoBinding
import com.example.todoapp.todo.home.TodoModel

class TodoContentActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MODEL = "extra_model"
        fun newIntent(context: Context): Intent = Intent(context, TodoContentActivity::class.java)
    }

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
        toolBar.title = getString(R.string.todo_toolbar)

        btnAdd.setOnClickListener {
            val todoIntent = Intent().apply {
                putExtra(EXTRA_MODEL, TodoModel(edtTitle.text.toString(), edtContent.text.toString()))
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
package com.example.todoapp.todo.add

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityTodoBinding
import com.example.todoapp.todo.home.TodoModel

class TodoContentActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MODEL = "extra_model"
        const val EXTRA_POSITION = "extra_position"
        const val EXTRA_TODO_CONTENT_TYPE = "extra_todo_content_type"
        fun newIntentForAdd(context: Context): Intent =
            Intent(context, TodoContentActivity::class.java).apply {
                putExtra(EXTRA_TODO_CONTENT_TYPE, TodoContentType.ADD.name)
            }

        fun newIntentForEdit(context: Context, position: Int, todoModel: TodoModel): Intent =
            Intent(context, TodoContentActivity::class.java).apply {
                putExtra(EXTRA_MODEL, todoModel)
                putExtra(EXTRA_POSITION, position)
                putExtra(EXTRA_TODO_CONTENT_TYPE, TodoContentType.EDIT.name)
            }
    }

    private lateinit var binding: ActivityTodoBinding

    private val todoModel by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(
                EXTRA_MODEL,
                TodoModel::class.java
            )
        } else {
            intent.getParcelableExtra(EXTRA_MODEL)
        }
    }

    private val position by lazy {
        intent.getIntExtra(EXTRA_POSITION, -1)
    }

    private val todoContentType by lazy {
        TodoContentType.from(intent.getStringExtra(EXTRA_TODO_CONTENT_TYPE))
    }

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

        when(todoContentType) {
            TodoContentType.ADD -> {
                btnSubmit.text = getString(R.string.todo_submit_btn_add)
            }
            else -> {
                btnSubmit.text = getString(R.string.todo_submit_btn_edit)
                btnDelete.visibility = View.VISIBLE
                edtTitle.setText(todoModel?.title)
                edtContent.setText(todoModel?.content)
            }
        }

        btnSubmit.setOnClickListener {
            finishTodoResult(todoContentType)
        }

        btnDelete.setOnClickListener {
            AlertDialog.Builder(this@TodoContentActivity).apply {
                setMessage(getString(R.string.todo_dialog_message))
                setPositiveButton(getString(R.string.todo_dialog_positive)) { _, _ ->
                    finishTodoResult(TodoContentType.REMOVE)
                }
                .setNegativeButton(getString(R.string.todo_dialog_negative)) { _, _ ->
                    create().dismiss()
                }
            }.show()
        }
    }

    private fun finishTodoResult(entryType: TodoContentType?) {
        val todoIntent = Intent().apply {
            putExtra(EXTRA_TODO_CONTENT_TYPE, entryType?.name)
            putExtra(EXTRA_POSITION, position)
            putExtra(
                EXTRA_MODEL,
                TodoModel(binding.edtTitle.text.toString(), binding.edtContent.text.toString())
            )
        }
        setResult(RESULT_OK, todoIntent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
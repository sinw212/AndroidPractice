package com.example.todoapp.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.todoapp.R
import com.example.todoapp.bookmark.BookmarkFragment
import com.example.todoapp.todo.home.TodoFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    private var fragments: ArrayList<MainTabs> = ArrayList()

    init {
        fragments.add(MainTabs(TodoFragment(), R.string.main_tab_todo_title))
        fragments.add(MainTabs(BookmarkFragment(), R.string.main_tab_bookmark_title))
    }

    fun getFragment(position: Int): Fragment {
        return fragments[position].fragment
    }

    fun getTitle(position: Int): Int {
        return fragments[position].title
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position].fragment
    }
}
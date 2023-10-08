package com.example.todoapp.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.todoapp.R
import com.example.todoapp.presentation.bookmark.BookmarkFragment
import com.example.todoapp.presentation.todo.home.TodoFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    private var fragments: ArrayList<MainTabs> = ArrayList()

    init {
        fragments.add(MainTabs(TodoFragment.newInstance(), R.string.main_tab_todo_title))
        fragments.add(MainTabs(BookmarkFragment.newInstance(), R.string.main_tab_bookmark_title))
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
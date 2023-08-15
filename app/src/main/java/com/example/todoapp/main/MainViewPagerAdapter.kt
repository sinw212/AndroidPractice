package com.example.todoapp.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.todoapp.bookmark.BookmarkFragment
import com.example.todoapp.todo.TodoFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    private var fragments: ArrayList<MainTabs> = ArrayList()

    init {
        fragments.add(MainTabs(TodoFragment(), "Todo"))
        fragments.add(MainTabs(BookmarkFragment(), "Bookmark"))
    }

    fun getTitle(position: Int): String {
        return fragments[position].title
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position].fragment
    }
}
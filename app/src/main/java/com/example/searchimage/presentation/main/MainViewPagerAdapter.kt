package com.example.searchimage.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.searchimage.R
import com.example.searchimage.presentation.bookmark.BookmarkFragment
import com.example.searchimage.presentation.search.SearchFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    private var fragments: ArrayList<MainTabs> = ArrayList()

    init {
        fragments.add(MainTabs(SearchFragment(), R.string.main_tab_search_title))
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
package com.example.searchimage.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.searchimage.R
import com.example.searchimage.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels() {
        SearchViewModelFactory(requireContext())
    }

    private val searchListAdapter by lazy {
        SearchListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() = with(binding) {
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerViewSearch.apply {
            layoutManager = staggeredGridLayoutManager
            adapter = searchListAdapter
        }

        imgSearch.setOnClickListener {
            val keyword = edtSearch.text.toString()
            if (keyword.isBlank()) {
                Toast.makeText(requireActivity(), R.string.search_edit_hint, Toast.LENGTH_SHORT).show()
            } else {
                viewModel.searchKeywordInfo(edtSearch.text.toString())
            }
        }
    }

    private fun initViewModel() {
        with(viewModel) {
            searchList.observe(viewLifecycleOwner) {
                searchListAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
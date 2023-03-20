package com.example.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fragmentos.R
import com.example.fragmentos.databinding.FragmentSearchBinding
import com.example.ui.detail.MovieDetailBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: Fragment(), ResultSearchAdapter.ResultSearchListener {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var adapter: ResultSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        setupAdapter()
        setupSearch()
        setupObservers()
    }

    private fun setupAdapter(){
        adapter = ResultSearchAdapter(this)
        binding.topSearchesList.adapter = adapter
    }

    private fun setupObservers(){
        viewModel.getLocalMovies().observe(viewLifecycleOwner){
            adapter.setItems(it)
        }
    }

    private fun setupSearch(){
        binding.clearSearchIcon.setOnClickListener {
            binding.searchTextInput.text = null
        }
        binding.searchTextInput.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun afterTextChanged(p0: Editable?) = Unit

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapter.filter.filter(p0)
            }

        })
    }

    override fun onClickedResult(movieId: Long) {
        findNavController().navigate(R.id.action_searchFragment_to_movieDetailBottomSheet,
            args = bundleOf(MovieDetailBottomSheet.ID to movieId)
            )
    }

}
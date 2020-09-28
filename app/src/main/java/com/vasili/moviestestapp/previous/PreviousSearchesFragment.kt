package com.vasili.moviestestapp.previous

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vasili.moviestestapp.R
import com.vasili.moviestestapp.common.SearchRequest
import com.vasili.moviestestapp.common.observe
import com.vasili.moviestestapp.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_results.*

class PreviousSearchesFragment: Fragment() {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_previous_searches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        observe(viewModel.previousSearches) {
            recyclerView.adapter = SearchAdapter(it) { item -> onItemClick(item) }
        }
        observe(viewModel.searchCommandStream) {
            when (it) {
                MainViewModel.ViewCommand.NavigateToResults -> findNavController().navigate(R.id.fragmentResults)
            }
        }
    }

    private fun onItemClick(searchRequest: SearchRequest) {
        viewModel.onSearchClick(searchRequest)
    }


}